package com.Uiproject.flickr;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class getNewTitle {
   public static String newTitle = null;
   public static String newPhotoId = null;
   
   public static void getMetaData () {
      
      int num = rename.count;
      final String str_num = Integer.toString(num);
      
      String flickURLCount = APIprop.flickrurl
            .replaceFirst("PHOTOCOUNT", str_num);
      
		WebDriver driver;
		String baseUrl;
		Client client;
		String title;
		long photoId;
		String airline = null;
		String model = null;
		client = ClientBuilder.newClient();
		//String FlickrUrl = APIprop.flickrurl;
		Invocation.Builder invocationBuilder = client.target(flickURLCount)
				.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		String content = response.readEntity(String.class);
		System.out.println(content);
		JSONObject jsonresponse = XML.toJSONObject(content)
				.getJSONObject("rsp").getJSONObject("photos")
				.getJSONObject("photo");

		title = jsonresponse.getString("title");
		photoId = jsonresponse.getLong("id");

		System.setProperty("webdriver.chrome.driver", "C:/Users/Vitaliy/Dropbox/automation/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://www.airliners.net";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(baseUrl + "/");
		driver.findElement(By.id("q")).click();
		driver.findElement(By.id("q")).clear();
		driver.findElement(By.id("q")).sendKeys(title);
		driver.findElement(By.name("submit")).click();

		try
		{
		new Select(driver.findElement(By.name("sort_order")))
				.selectByVisibleText("Latest Additions First");
		}
		catch(NoSuchElementException e)
		{
		}
		try{
		driver.findElement(By.cssSelector("option[value=\"photo_id desc\"]"))
				.click();
		}
		catch (NoSuchElementException e)
		{
		   
		}
		List<WebElement> resultList = driver
				.findElements(By
						.xpath("//div[@id='content']/div/table[2]/tbody/tr[2]/td[1]/div[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/table/tbody/tr/td/font/a[1]"));
		for (WebElement resultItem : resultList) {
			airline = resultItem.getText();
			System.out.println(airline);

		}
		List<WebElement> resultList1 = driver
				.findElements(By
						.xpath("//div[@id='content']/div/table[2]/tbody/tr[2]/td[1]/div[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/table/tbody/tr/td/font/a[2]"));
		for (WebElement resultItem1 : resultList1) {
			model = resultItem1.getText();
			System.out.println(model);
		}
		driver.quit(); 	
//		System.out.println(airline+ " | "+model + " | "+ title +" "+ photoId);
//		String id = String.valueOf(photoId);
//		airline = airline.replace(' ', '+');
//		model = model.replace(' ', '+');
		newTitle = airline+ " | "+model+" | "+title;
		newPhotoId = Long.toString(photoId);
//		String updateMetaUrl = APIprop.updateMeta.replaceFirst("PHOTO", id).replaceFirst("TITLE", newTitle);
//		System.out.println(newTitle);
		

		
	}
	
    
}

// Done! Here's the API key and secret for your new app:
// my app
//
// Key:
// 86d9143b3942339fba26947499608870
//
// Secret:
// 75ed9c6d7095f621


	
	
	