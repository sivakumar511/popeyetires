package com.popeyetires.popeyetires.core.filters;

import java.util.HashMap;

import com.adobe.cq.sightly.WCMUse;
import com.popeyetires.popeyetires.service.TireService;

public class TireFeaturesWcmUse extends WCMUse {
	
	private HashMap<String, String> tireFeatures;
	
	@Override
	public void activate() throws Exception {
		String tireName = getCurrentPage().getName();
		TireService tireService = getSlingScriptHelper().getService(TireService.class);
		tireFeatures = tireService.getTireFeaturesInformation(tireName);
	}

	public HashMap<String, String> getTireFeatures() {
		return tireFeatures;
	}

	public void setTireFeatures(HashMap<String, String> tireFeatures) {
		this.tireFeatures = tireFeatures;
	}
}
