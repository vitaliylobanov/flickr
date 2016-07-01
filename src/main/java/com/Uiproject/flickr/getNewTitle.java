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
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class getNewTitle {
   public static String newTitle = null;
   public static String newPhotoId = null;
   private static String sortByXpath = "//select[contains(@name, 'photo_search[sortBy]')]";
   private static String chooseSortXpath = "//select[contains(@id, 'photo_search_sortBy')]//option[contains(text(), 'Date Added')]";
   private static String airlineByXpath = "//li[1]//div[contains(@class, 'card-copy-row card-copy-row-airline')]//a[contains(@href,'')]";
   private static String aircraftByXpath = "//li[1]//div[contains(@class, 'card-copy-row card-copy-row-aircraft')]//a[contains(@href,'')]";
   
   
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

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		                "C:/Users/Vitaliy/Desktop/phantom/phantomjs-2.1.1-windows/bin/phantomjs.exe");                  
		driver = new PhantomJSDriver(caps);
//		System.setProperty("webdriver.chrome.driver", "C:/Users/Vitaliy/Dropbox/automation/chromedriver.exe");
//		driver = new ChromeDriver();
		baseUrl = "http://www.airliners.net";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(baseUrl + "/search?keywords=" + title);
		
		 WebElement sortBy = driver.findElement(By.xpath(sortByXpath));
		 sortBy.click();
		 
		 WebElement chooseSort = driver.findElement(By.xpath(chooseSortXpath));
		 chooseSort.click();
		 
		 WebElement airlineName = driver.findElement(By.xpath(airlineByXpath));
		 airline = airlineName.getText();
		       
       WebElement modelName = driver.findElement(By.xpath(aircraftByXpath));
       model = modelName.getText();
		 
		driver.quit(); 	

		newTitle = airline+ " | "+model+" | "+title;
		newPhotoId = Long.toString(photoId);
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


	
	
	