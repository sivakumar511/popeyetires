package com.popeyetires.popeyetires.core.filters;

import com.adobe.cq.sightly.WCMUse;
import com.popeyetires.popeyetires.core.models.TireInfo;
import com.popeyetires.popeyetires.service.TireService;

public class TireInfoWcmUse extends WCMUse {
	
	private TireInfo tireInfo;
	
	@Override
	public void activate() throws Exception {
		String tireName = getCurrentPage().getName();
		TireService tireService = getSlingScriptHelper().getService(TireService.class);
		this.tireInfo = tireService.getTireInformation(tireName);
		tireInfo.setTireImage("/content/dam/popeyetires/" + tireName + ".jpg");
		// Get the details from JCR based on the Page Name
	}
	
	public TireInfo getTireInfo() {
		return tireInfo;
	}
}
