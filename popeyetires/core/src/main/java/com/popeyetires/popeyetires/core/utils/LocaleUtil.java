package com.popeyetires.popeyetires.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class LocaleUtil extends WCMUse{

	private final Logger logger = LoggerFactory.getLogger(LocaleUtil.class);
	private Page page;
	private PageManager pageManager;
    private String path;
    private String locale = "";
	@Override
	 public void activate() {
	  logger.info("in actiave");
	  try {
		  page = super.getCurrentPage();
	        pageManager = super.getPageManager();
	        path = page.getPath();
	        logger.info("in path:::::>>>"+ path); 
	        if(path.contains("/en/us/")) {
	            locale = "en-us";
	        }
	        if(path.contains("/fr/ca/")) {
	            locale = "fr-ca";
	        }

	        else{
	        	locale = "en-us";
	        }
          logger.info("in menu:::::>>>"+ locale);
	  }
	  catch(Exception e){
		  logger.error("Error in MenuWcm"+e.getMessage());
	  }
}
	
	public String getLocale(){
		return locale;
	}
}