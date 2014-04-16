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


public class DisplayArticle extends SimpleTagSupport
{
	
	String path;
	List<Article> articleList=new ArrayList<Article>();
    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void doTag() throws JspException 
    {  
		
        try {  
        	
        	PageContext pageContext = (PageContext) getJspContext();
    		JspWriter out = pageContext.getOut();
            System.out.println("::::: Inside do start tag :::::");  
            System.out.println("path is ::: "+path);
         
        	Map map = new HashMap();
        	map.put("path", path);
        	map.put("type","nt:unstructured");
        	map.put("property", "sling:resourceType");
        	map.put("property.value", "cqblueprints-example/components/ArticleImageC"); //cqblueprints-example/components/examplepage  
        	
        	Resource resource = (Resource) getJspContext().getAttribute("resource");
        	Session sess = resource.getResourceResolver().adaptTo(Session.class);
        	QueryBuilder queryBuilder = resource.getResourceResolver().adaptTo(QueryBuilder.class);
        	
        	System.out.println("resource"+resource);
        	System.out.println("sess"+sess);
        	System.out.println("queryBuilder"+queryBuilder);
        	
        	com.day.cq.search.Query query = queryBuilder.createQuery(PredicateGroup.create(map), sess);    
        	query.setHitsPerPage(2);
        	SearchResult result = query.getResult();
        	List<Hit> l=result.getHits();
        	System.out.println("No of pages display "+l.size());
        	for(Hit hit : l)
        	{
        		System.out.println("title ::: "+hit.getNode().getPath());
        		Node temp=(Node)hit.getNode();
        		
        		System.out.println("===========NODE Start=====================");
        		System.out.println("text :: "+temp.getProperty("text").getString());
        		System.out.println("title :: "+temp.getProperty("title").getString());
        		System.out.println("subtitle :: "+temp.getProperty("subtitle").getString());
        		System.out.println("align :: "+temp.getProperty("align").getString());
        		
        		
        			Node image=temp.getNode("image");
        			if(image!=null)
        			{
        				System.out.println("Image ref :: "+image.getProperty("fileReference").getString());
        			}
        			System.out.println("===========NODE End=====================");
        		
        			Article article=new Article();
        			article.setTitle(temp.getProperty("title").getString());
        			article.setSubtitle(temp.getProperty("subtitle").getString());
        			article.setText(temp.getProperty("text").getString());
        			article.setAlign(temp.getProperty("align").getString());
        			article.setImageRef(image.getProperty("fileReference").getString());
        			articleList.add(article);
        			
        			
        	}
        	System.out.println("articleList.size-->"+articleList.size());
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