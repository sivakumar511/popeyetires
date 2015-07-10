package com.popeyetires.popeyetires.service;

import java.util.HashMap;
import java.util.List;

import com.popeyetires.popeyetires.core.models.TireInfo;

public interface TireService {
	public TireInfo getTireInformation(String tireName);

	public HashMap<String, String> getTireFeaturesInformation(String tireName);
	
	public List<TireInfo> getRelatedTireInformation();

	public List<TireInfo> getRelatedTireInformation(String tireName);
}
