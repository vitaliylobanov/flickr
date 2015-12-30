package com.Uiproject.flickr;

/**
 * Hello world!
 *
 */
public class APIprop {
	public static final String flickrurl = "https://api.flickr.com/services/rest/?"
			+ "&method=flickr.people.getPublicPhotos&api_key=86d9143b3942339fba26947499608870&user_id=62737087@N07&per_page=1&page=2";
	public static final String updateMeta = "https://api.flickr.com/services/rest/?"
			+ "method=flickr.photos.setMeta&api_key=86d9143b3942339fba26947499608870&photo_id=PHOTO&title=TITLE&format=json&nojsoncallback=1"
			+ "&auth_token=72157650006385220-c44d3e1f7e4a8148&api_sig=3732796bda50ff9b2119f625a8508e38";
}
