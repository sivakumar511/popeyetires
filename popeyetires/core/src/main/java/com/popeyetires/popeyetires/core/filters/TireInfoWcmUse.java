package com.popeyetires.popeyetires.core.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.popeyetires.popeyetires.core.models.TireInfo;
import com.popeyetires.popeyetires.service.TireService;

public class TireInfoWcmUse extends WCMUse {
	private final Logger logger = LoggerFactory.getLogger(TireFeaturesWcmUse.class);
	private TireInfo tireInfo;
	
	@Override
	public void activate() throws Exception {
		String tireName = getCurrentPage().getName();
		logger.info("Tire Name :" + tireName);
		TireService tireService = getSlingScriptHelper().getService(TireService.class);
		this.tireInfo = tireService.getTireInformation(tireName);
		logger.info("/content/dam/popeyetires/" + tireName + ".jpg");
		tireInfo.setTireImage("/content/dam/popeyetires/" + tireName + ".jpg");
		// Get the details from JCR based on the Page Name
	}
	
	public TireInfo getTireInfo() {
		return tireInfo;
	}
}
