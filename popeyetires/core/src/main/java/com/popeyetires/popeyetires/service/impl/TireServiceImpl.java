package com.popeyetires.popeyetires.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.popeyetires.popeyetires.core.models.TireInfo;
import com.popeyetires.popeyetires.service.TireService;

@Service(TireService.class)
@Component(immediate=true)
public class TireServiceImpl implements TireService {
	
	private final Logger logger = LoggerFactory.getLogger(TireServiceImpl.class);
	
	@Reference
	SlingRepository repository;
	
	Session session;

	@Override
	public TireInfo getTireInformation(String tireName) {
		try {
			session = this.repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
			
			QueryManager queryManager = session.getWorkspace().getQueryManager();

			Query query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/PopeyeTires/jcr:content') AND NAME() = '" + tireName + "'", Query.JCR_SQL2);
			
			QueryResult result = query.execute();
			
			NodeIterator nodeIterator = result.getNodes();
			TireInfo tireInfo = null;
			while(nodeIterator.hasNext()) {
				tireInfo = new TireInfo();
				Node tireNode = nodeIterator.nextNode();
				PropertyIterator propIter = tireNode.getProperties();
				while(propIter.hasNext()) {
					Property prop = propIter.nextProperty();
					if(prop.getName().equals("title")) {
						tireInfo.setTitle(prop.getString());
					} else if(prop.getName().equals("description")) {
						tireInfo.setDescription(prop.getString());
					} else if(prop.getName().equals("treadDepth")) {
						tireInfo.setTreadDepth(prop.getString());
					} else if(prop.getName().equals("warrantyInKM")) {
						tireInfo.setWarrantyInKM(prop.getString());
					} else if(prop.getName().equals("warrantyInMiles")) {
						tireInfo.setWarrantyInMiles(prop.getString());
					} else if(prop.getName().equals("price")) {
						tireInfo.setPrice(prop.getString());
					}
				}
			}
			
			logger.info("Tire Information :" + tireInfo);
			//TireInfo tireInfo = new TireInfo();
			//tireInfo.setTitle("POTENZA RE11 205/R17");
			//tireInfo.setDescription("This is a Potenza tire. All season tire.");
			//tireInfo.setTreadDepth("10.3");
			//tireInfo.setWarranty("18000");
			//tireInfo.setPrice("110.2");
			return tireInfo;
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HashMap<String, String> getTireFeaturesInformation(String tireName) {
		return null;
	}

	@Override
	public List<TireInfo> getRelatedTireInformation() {
		return null;
	}

	@Override
	public List<TireInfo> getRelatedTireInformation(String tireName) {
		try {
			session = this.repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
			
			QueryManager queryManager = session.getWorkspace().getQueryManager();

			Query query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/PopeyeTires/jcr:content') AND NAME() = '" + tireName + "'", Query.JCR_SQL2);
			
			QueryResult result = query.execute();
			
			NodeIterator nodeIterator = result.getNodes();
			TireInfo tireInfo = null;
			while(nodeIterator.hasNext()) {
				tireInfo = new TireInfo();
				Node tireNode = nodeIterator.nextNode();
				PropertyIterator propIter = tireNode.getProperties();
				while(propIter.hasNext()) {
					Property prop = propIter.nextProperty();
					if(prop.getName().equals("title")) {
						tireInfo.setTitle(prop.getString());
					} else if(prop.getName().equals("description")) {
						tireInfo.setDescription(prop.getString());
					} else if(prop.getName().equals("treadDepth")) {
						tireInfo.setTreadDepth(prop.getString());
					} else if(prop.getName().equals("warrantyInKM")) {
						tireInfo.setWarrantyInKM(prop.getString());
					} else if(prop.getName().equals("warrantyInMiles")) {
						tireInfo.setWarrantyInMiles(prop.getString());
					} else if(prop.getName().equals("price")) {
						tireInfo.setPrice(prop.getString());
					} else if(prop.getName().equals("fullSize")) {
						
					}
				}
			}
			
			query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/PopeyeTires/jcr:content') AND s.", Query.JCR_SQL2);
			
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}
}
