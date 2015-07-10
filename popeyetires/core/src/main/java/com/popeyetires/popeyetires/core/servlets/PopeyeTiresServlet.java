
package com.popeyetires.popeyetires.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PropertyIterator;
import javax.jcr.Property;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate = true, metatype = true, label = "MMY Data Servlet", description = "Provides access to the MMY data located in the JCR.")
@Service
@Properties({
		@org.apache.felix.scr.annotations.Property(name = "sling.servlet.paths", value = { "/tim/productMap" }, label = "Servlet Path"),
		@org.apache.felix.scr.annotations.Property(name = "sling.servlet.methods", value = "GET", label = "Request Method") })
	
public class PopeyeTiresServlet extends SlingAllMethodsServlet {
	
	private final Logger logger = LoggerFactory.getLogger(PopeyeTiresServlet.class);
	private static final long serialVersionUID = 1L;

	private static final String EMPTY_RESPONSE = "[ { \"value\": \"\", \"text\": \"No data available\" } ]";
	
	Session session;
	@Reference
	SlingRepository repository;
	
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		//String action = request.getParameter("action");
		String  jsonData = EMPTY_RESPONSE;
		//if(action.equals("productMap")){
		
		try {
			Session session = this.repository.login(new SimpleCredentials(
					"admin", "admin".toCharArray()));

			QueryManager queryManager = session.getWorkspace().getQueryManager();

			Query query = null;
			query = queryManager.createQuery("SELECT * FROM [nt:unstructured] AS s WHERE ISDESCENDANTNODE(s,'/content/popeyeDB/tires/jcr:content')",
					Query.JCR_SQL2);
			QueryResult result = query.execute();

			// logger.info("Node Path ::D> " + node.getPath());
			NodeIterator nodes = result.getNodes();
			// logger.info("NOde size : " +result.getRows().getSize());
			
			//This code is used to create tire pages
			Node root = session.getRootNode();
			Node content_popeye = root.getNode("content/popeyetires");

			while (nodes.hasNext()) {
				logger.info("Node Iteration : ");
				
				Node nextNode = nodes.nextNode();
				Node tireNode = null;
				if (!content_popeye.hasNode(nextNode.getName())) {
				tireNode = 	content_popeye.addNode(nextNode.getName(), "cq:Page");
					logger.info("Created cq:page");
					System.out.println("Created jcr node");
				}else{
					tireNode = content_popeye.getNode(nextNode.getName());
				}
				String tireName = tireNode.getName().toUpperCase().replaceAll("-", " ");
				
				
				if (!tireNode.hasNode("jcr:content")) {
					tireNode.addNode("jcr:content", "cq:PageContent");
					
					tireNode.setProperty("jcr:title", tireName);
					tireNode.setProperty("sling:resourceType", "popeyetires/page/tire-details");
					tireNode.setProperty("cq:template", "/apps/popeyetires/templates/tire-details");
					logger.info("Created jcr node");
					System.out.println("Created jcr node");
				}
				
			}
		} catch (Exception e) {
			logger.error("Error :::" + e.getMessage());
		}
		
		
		PrintWriter writer = response.getWriter();
		writer.println(jsonData);
		writer.flush();
	}
}

