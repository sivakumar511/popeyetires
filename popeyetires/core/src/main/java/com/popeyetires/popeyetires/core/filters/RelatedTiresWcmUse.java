package com.popeyetires.popeyetires.core.filters;

import java.util.List;

import com.adobe.cq.sightly.WCMUse;
import com.popeyetires.popeyetires.core.models.TireInfo;
import com.popeyetires.popeyetires.service.TireService;

public class RelatedTiresWcmUse extends WCMUse {
	private List<TireInfo> relatedTireInfo;
	
	@Override
	public void activate() throws Exception {
		String tireName = getCurrentPage().getName();
		TireService tireService = getSlingScriptHelper().getService(TireService.class);
		this.relatedTireInfo = tireService.getRelatedTireInformation(tireName);
	}

	public List<TireInfo> getRelateTireInfo() {
		return relatedTireInfo;
	}
}
