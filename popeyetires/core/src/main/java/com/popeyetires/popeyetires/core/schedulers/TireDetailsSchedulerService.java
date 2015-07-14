package com.popeyetires.popeyetires.core.schedulers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.sql.DataSource;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.commons.datasource.poolservice.DataSourcePool;

/**
 * @author supvatti
 *
 */
@Component(label = "TireDetails Scheduled Service", description = "TireDetails scheduled service", immediate = true, metatype = true)
@Properties({
	@Property(label = "TireDetails Scheduled Service", description = "Run this service every day @ 12 get all the tire details.", name = "scheduler.expression", value = "0 18 14 * * ?")
})
@Service
public class TireDetailsSchedulerService implements Runnable {
	private final Logger log = LoggerFactory.getLogger(TireDetailsSchedulerService.class);

	@Reference
	SlingRepository repository;
	
	@Reference
	private DataSourcePool source;
	
	public void run() {

		Connection c = null;
		DataSource dataSource = null;
		try {
			log.info("this is my PopeyeTire Scheduled Service");
			dataSource = (DataSource) source.getDataSource("MysqlDataSource");
			c = dataSource.getConnection();
			log.info("connection ::>>>" + c);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("Select * from all_tires");
			Session session = this.repository.login(new SimpleCredentials("admin", "admin".toCharArray()));

			Node root = session.getRootNode();
			Node content = root.getNode("content");
			if (!content.hasNode("PopeyeTires")) {
				content.addNode("PopeyeTires", "sling:OrderedFolder");
				log.info("Created PopeyeTires node ");

			}
			Node tires = content.getNode("PopeyeTires");

			if (!tires.hasNode("jcr:content")) {
				tires.addNode("jcr:content", "nt:unstructured");
				log.info("Created jcr node");

			}

			Node jcrContentNode = tires.getNode("jcr:content");
			while (rs.next()) {
				if (!jcrContentNode.hasNode((rs.getString("Description_EN").toLowerCase().replaceAll("/", "-").replaceAll(" ", "-")))) {
				 Node material = jcrContentNode.addNode(rs.getString("Description_EN").toLowerCase().replaceAll("/", "-").replaceAll(" ", "-"), "nt:unstructured");
				 log.info("Created post node :" + material.getName());
					material.setProperty("material", rs.getString("Material"));
					material.setProperty("descriptionEn", rs.getString("Description_EN"));
					material.setProperty("descriptionFr", rs.getString("Description_FR"));
					material.setProperty("title", rs.getString("Title"));
					material.setProperty("fullSize", rs.getString("Full_Size"));
					material.setProperty("treadDepth",rs.getString("Tread_Depth"));
					material.setProperty("showTire", rs.getString("Snow_Tire"));
					material.setProperty("winterTire",rs.getString("Winter_Tire"));
					material.setProperty("warrantyInKM", rs.getString("Warranty_in_Kilometers"));
					material.setProperty("warrantyInMiles", rs.getString("Warranty_in_Miles"));
				}
				session.save();
				log.info("Properties has been saved");
			}
		} catch (SQLException e) {
			log.error("SQL exception!!!!!!!!!" + e.getMessage(), e);
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("in exception!!!!!!!!!" + e.getMessage(), e);
		} finally{
			try {
				log.info("close the connection");
				c.close();
			} catch (SQLException e) {
				log.error("SQL exception!!!!!!!!!" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
