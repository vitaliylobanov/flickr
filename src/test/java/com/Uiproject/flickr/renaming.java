package com.Uiproject.flickr;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.Uiproject.flickr.APIprop;

public class renaming {
	public static void main(String[] args) {
		WebDriver driver;
		String baseUrl;
		Client client;
		client = ClientBuilder.newClient();
		String FlickrUrl = APIprop.flickrurl;
		Invocation.Builder invocationBuilder = client.target(FlickrUrl)
				.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		String content = response.readEntity(String.class);
		System.out.println(content);
		JSONObject jsonresponse = XML.toJSONObject(content)
				.getJSONObject("rsp").getJSONObject("photos")
				.getJSONObject("photo");

		String title = jsonresponse.getString("title");

		driver = new FirefoxDriver();
		baseUrl = "http://www.airliners.net";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(baseUrl + "/");
		driver.findElement(By.id("q")).click();
		driver.findElement(By.id("q")).clear();
		driver.findElement(By.id("q")).sendKeys(title);
		driver.findElement(By.name("submit")).click();

		new Select(driver.findElement(By.name("sort_order")))
				.selectByVisibleText("Latest Additions First");
		driver.findElement(By.cssSelector("option[value=\"photo_id desc\"]"))
				.click();
		List<WebElement> resultList = driver
				.findElements(By
						.xpath("//div[@id='content']/div/table[2]/tbody/tr[2]/td[1]/div[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/table/tbody/tr/td/font/a[1]"));
		for (WebElement resultItem : resultList) {
			String airline = resultItem.getText();
			System.out.println(airline);

		}
		List<WebElement> resultList1 = driver
				.findElements(By
						.xpath("//div[@id='content']/div/table[2]/tbody/tr[2]/td[1]/div[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/table/tbody/tr/td/font/a[2]"));
		for (WebElement resultItem1 : resultList1) {
			String model = resultItem1.getText();

			System.out.println(model);
		}
		driver.quit();

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

// airline:
// /html/body/div[1]/div[3]/div/table[2]/tbody/tr[2]/td[1]/div[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/table/tbody/tr/td/font/a[1]
// model:
// /html/body/div[1]/div[3]/div/table[2]/tbody/tr[2]/td[1]/div[1]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/table/tbody/tr/td/font/a[2]

