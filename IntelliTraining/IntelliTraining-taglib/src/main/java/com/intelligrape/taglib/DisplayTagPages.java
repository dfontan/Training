package com.intelligrape.taglib;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

import org.apache.derby.impl.sql.compile.AlterTableNode;
import org.apache.sling.api.resource.Resource;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DisplayTagPages extends SimpleTagSupport
{
	
	String path;
	String tagname;
	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	
    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	List articleList=new ArrayList();
	public void doTag() throws JspException 
    {  
		
        try {  
        	
        	PageContext pageContext = (PageContext) getJspContext();
    		JspWriter out = pageContext.getOut();
            System.out.println("::::: DisplayTagPages :::::");  
            System.out.println("path is ::: "+path);
            System.out.println("Tagname  is ::: "+tagname);
        	Map map = new HashMap();
        	map.put("path", path);
        	map.put("tagid.property", "jcr:content/cq:tags");
        	map.put("tagid","IntelliTraining:"+tagname); //cqblueprints-example/components/examplepage  
        	
        	Resource resource = (Resource) getJspContext().getAttribute("resource");
        	Session sess = resource.getResourceResolver().adaptTo(Session.class);
        	QueryBuilder queryBuilder = resource.getResourceResolver().adaptTo(QueryBuilder.class);
        	
        	System.out.println("resource"+resource);
        	System.out.println("sess"+sess);
        	System.out.println("queryBuilder"+queryBuilder);
        	
        	com.day.cq.search.Query query = queryBuilder.createQuery(PredicateGroup.create(map), sess);    
        	SearchResult result = query.getResult();
        	List<Hit> l=result.getHits();
        	System.out.println("No of pages display "+l.size());
        	for(Hit hit : l)
        	{
        		System.out.println("title ::: "+hit.getNode().getPath());
        		articleList.add(hit.getNode().getPath());
        	}
        	//System.out.println("articleList.size-->"+articleList.size());
        	pageContext.setAttribute("articleList", articleList);
        	
        	getJspContext().setAttribute("myList", articleList);
        	
        	
        } 
        catch (Exception ioe) 
        {  
            ioe.printStackTrace();  
            throw new JspException("IOException while writing data to page" + ioe.getMessage());  
        }  
        

}
}