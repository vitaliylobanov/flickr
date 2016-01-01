package com.Uiproject.flickr;

 
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.scribejava.core.builder.ServiceBuilder; 
import com.github.scribejava.apis.FlickrApi; 
import com.github.scribejava.core.model.OAuthRequest; 
import com.github.scribejava.core.model.Response; 
import com.github.scribejava.core.model.Token; 
import com.github.scribejava.core.model.Verb; 
import com.github.scribejava.core.model.Verifier; 
import com.github.scribejava.core.oauth.OAuthService; 

public class Get_Access_Token { 

 
    private static final String PROTECTED_RESOURCE_URL = "https://api.flickr.com/services/rest/"; 
 
 
    public static void main(String[] args) { 
        // Replace these with your own api key and secret 
         String apiKey = "86d9143b3942339fba26947499608870"; 
         String apiSecret = "75ed9c6d7095f621"; 
         String permsWrite = "&perms=write";
         String verificationCode;
         OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey(apiKey).apiSecret(apiSecret). 
                 build(); 
         Scanner in = new Scanner(System.in); 
 
 
         System.out.println("=== Flickr's OAuth Workflow ==="); 
         System.out.println(); 
 
 
         // Obtain the Request Token 
         System.out.println("Fetching the Request Token..."); 
         Token requestToken = service.getRequestToken(); 
         System.out.println("Got the Request Token!"); 
         System.out.println(); 
 
 
         System.out.println("Now go and authorize ScribeJava here:"); 
         String authorizationUrl = service.getAuthorizationUrl(requestToken); 
         System.out.println(authorizationUrl + "&perms=write"); 
         System.out.println("And paste the verifier here");
        
//         WebDriver driver;
//         String baseUrl;
//         driver = new FirefoxDriver();
//         baseUrl = authorizationUrl + permsWrite;
//         
//         System.out.println(baseUrl);
//         
//         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
//         driver.get(baseUrl + "/");
//         driver.findElement(By.cssSelector("input.Butt")).click();
//         driver.findElement(By.xpath("//input[@id='yui_3_11_0_1_1451587841573_333']")).click();
//         driver.findElement(By.xpath("//span[@id='yui_3_11_0_1_1451587884161_315']")).click();
//         verificationCode = driver.findElement(By.xpath("//span[@id='yui_3_11_0_1_1451587884161_315']")).getText();
         
//         System.out.println(verificationCode);
         
         
         System.out.print(">>"); 
         Verifier verifier = new Verifier(in.nextLine()); 
         System.out.println(); 
 
 
         // Trade the Request Token and Verfier for the Access Token 
         System.out.println("Trading the Request Token for an Access Token..."); 
         Token accessToken = service.getAccessToken(requestToken, verifier); 
         System.out.println("Got the Access Token!"); 
         System.out.println("(if your curious it looks like this: " + accessToken + " )"); 
         System.out.println(); 
 
        
         
 
         // Now let's go and ask for a protected resource!  
         System.out.println("Now we're going to access a protected resource..."); 
         OAuthRequest request = new OAuthRequest(Verb.POST, PROTECTED_RESOURCE_URL, service); 
         request.addQuerystringParameter("method", "flickr.photos.setMeta");
         request.addQuerystringParameter("photo_id", "23817785921");
         request.addQuerystringParameter("title", "N644AS");
         service.signRequest(accessToken, request); 
         Response response = request.send(); 
         System.out.println("Got it! Lets see what we found..."); 
         System.out.println(); 
         System.out.println(response.getBody()); 
 
 
         System.out.println(); 
         System.out.println("Thats it man! Go and build something awesome with ScribeJava! :)"); 
     } 
 } 
