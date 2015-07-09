package apps.popeyetires.components.content.sidenav;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;
import java.util.*;

import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

public class SideNavWcmUse extends WCMUse {

 private final Logger logger = LoggerFactory.getLogger(SideNavWcmUse.class);
 Map<String,String> items = null;
 

 @Override
 public void activate() {
  logger.info("in actiave");
  try {
   Property prop = null;
   Node currentNode = getResource().adaptTo(Node.class);
   if (currentNode.hasProperty("map")) {
    logger.info("Node has map");
    prop = currentNode.getProperty("map");
   }
   if (prop != null) {
    JSONObject itemJson = null;
    Value[] values = null;
    if (prop.isMultiple()) {
     values = prop.getValues();
    } else {
     values = new Value[1];
     values[0] = prop.getValue();
    }
     items = new HashMap<String,String>();
      for (Value val : values) {
       logger.info("value :" + val);
       itemJson = new JSONObject(val.getString());
       logger.info("itemJson :" + itemJson.getString("name"));
       items.put("name",itemJson.getString("name"));
       items.put("url",itemJson.getString("url"));
       items.put("imageSource",itemJson.getString("imageSource"));
      }
    logger.info("The elements :" + values.length);

   }
   
   logger.info("End of active");
  } catch (Exception e) {

  }

 }
 
 public Map<String,String> getItems() {
  return items;
 }
}