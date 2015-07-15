package com.popeyetires.popeyetires.core.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.popeyetires.popeyetires.core.models.TireInfo;
import com.popeyetires.popeyetires.service.TireService;

public class TireInfoWcmUse extends WCMUse {
	private final Logger logger = LoggerFactory.getLogger(TireFeaturesWcmUse.class);
	private TireInfo tireInfo;
	private String locale;
	
	@Override
	public void activate() throws Exception {
		if(getCurrentPage().getPath().contains("/en/")) {
			locale = "en-us";
		} else if(getCurrentPage().getPath().contains("/fr/")) {
			locale = "fr-ca";
		}
		logger.info("Locale :" + locale);
		String tireName = getCurrentPage().getName();
		logger.info("Tire Name :" + tireName);
		TireService tireService = getSlingScriptHelper().getService(TireService.class);
		this.tireInfo = tireService.getTireInformation(tireName);
		logger.info("/content/dam/bst/tires/models/" + tireInfo.getTitle().toLowerCase().replaceAll(" ", "-").replaceAll("/", "-") + "/hero.jpg");
		// Pointing the Image location based on the title of the image
		tireInfo.setTireImage("/content/dam/bst/tires/models/" + tireInfo.getTitle().toLowerCase().replaceAll(" ", "-").replaceAll("/", "-") + "/hero.png");
		// Get the details from JCR based on the Page Name
	}
	
	public TireInfo getTireInfo() {
		return tireInfo;
	}
	
	public String getLocale() {
		return locale;
	}
}
