package com.intelligrape.taglib;
import com.day.cq.search.QueryBuilder;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DisplayTagPages extends SimpleTagSupport
{
	String path;
	
	String operation;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	HashMap hashMap=new HashMap();
	public void doTag() throws JspException 
    {  
		final  Logger logger = LoggerFactory.getLogger(DisplayTagPages.class);
        try {  
        	
        	PageContext pageContext = (PageContext) getJspContext();
    		JspWriter out = pageContext.getOut();
            logger.info("::::: DisplayTagPages Demo:::::");  
            logger.info("path is ::: "+path);
            logger.info("Operation ::: "+operation);
            String op;
        	Resource resource = (Resource) getJspContext().getAttribute("resource");
        	StringBuffer params=new StringBuffer();
           	Session sess = resource.getResourceResolver().adaptTo(Session.class);
        	QueryBuilder queryBuilder = resource.getResourceResolver().adaptTo(QueryBuilder.class);
            TagManager tagManager=resource.getResourceResolver().adaptTo(TagManager.class);
        	Tag[] tags = tagManager.getTags(resource);
        	logger.info("Tags length "+tags.length);       	
        	Map map = new HashMap();
        	map.put("path", path);
        	map.put("tagid.property", "jcr:content/cq:tags");
        	if(operation.equals("on"))
        	{
        		logger.info("Inside AND -----------");
        	map.put("tagid.and",true);
        	op="and";
        	}
        	else
        	{
        		logger.info("Inside OR ------------ ");
        		map.put("tagid.or",true);
        		op="or";
        	}
        	int counter=1;
        	for(Tag t:tags)
        	{
        	map.put("tagid."+counter+"_value", t.getTagID());
        	params.append(" "+op+" "+"cq:tags like '%"+t.getTagID()+"%'");
        	counter++;
        	
        	}
        	logger.info("resource"+resource);
        	logger.info("sess"+sess);
        	logger.info("queryBuilder"+queryBuilder);
        	logger.info(map.toString());
        	
        	QueryManager queryManager = sess.getWorkspace().getQueryManager(); 
           	logger.info("queryManager :::: "+queryManager);
        	String quer="select * from cq:PageContent where jcr:path like '"+path+"/%' "+params;
        	logger.info("query :::: "+quer);
        	Query query1=queryManager.createQuery(quer, javax.jcr.query.Query.SQL);
        	logger.info("query1 :::: "+query1);
        	QueryResult result = query1.execute();
        	NodeIterator nodeIter = result.getNodes();
        	if(nodeIter.hasNext())
        	{
        		logger.info("Has Nodes ");
        	}
        	while(nodeIter.hasNext())
        	{
        		logger.info(":::Inside While :::");
        		Node n=(Node) nodeIter.next();
        		logger.info("Node Path ::: "+n.getParent().toString().replaceAll("node ", "").trim());
        		logger.info("Node Path ::: "+n.getProperty("jcr:title").getString());
        		hashMap.put(n.getParent().toString().replaceAll("node ", "").trim(),n.getProperty("jcr:title").getString());      		      		
        	} 	
           	logger.info(hashMap.toString());
        	getJspContext().setAttribute("myMap", hashMap);
        
        	
        } 
        catch (Exception ioe) 
        {  
         ioe.getMessage();
        }  
        

}
}