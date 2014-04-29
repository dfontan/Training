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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DisplayArticle extends SimpleTagSupport
{
	
	String path;
	String noarticles;
	

	public String getNoarticles() {
		return noarticles;
	}

	public void setNoarticles(String noarticles) {
		this.noarticles = noarticles;
	}


	List<Article> articleList=new ArrayList<Article>();
    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

	public void doTag() throws JspException 
    {  
		final  Logger logger = LoggerFactory.getLogger(DisplayArticle.class);
		
        try {  
        	
        	PageContext pageContext = (PageContext) getJspContext();
    		JspWriter out = pageContext.getOut();
    		logger.info("::::: Inside do start tag :::::");  
            logger.info("path is ::: "+path);
            logger.info(":::: No of Articles ::: "+noarticles);
         
        	Map map = new HashMap();
        	map.put("path", path);
        	map.put("type","nt:unstructured");
        	map.put("property", "sling:resourceType");
        	map.put("property.value", "cqblueprints-example/components/ArticleImageC"); //cqblueprints-example/components/examplepage  
        	
        	Resource resource = (Resource) getJspContext().getAttribute("resource");
        	Session sess = resource.getResourceResolver().adaptTo(Session.class);
        	QueryBuilder queryBuilder = resource.getResourceResolver().adaptTo(QueryBuilder.class);
        	
        	logger.info("resource"+resource);
        	logger.info("sess"+sess);
        	logger.info("queryBuilder"+queryBuilder);
        	
        	com.day.cq.search.Query query = queryBuilder.createQuery(PredicateGroup.create(map), sess);    
        	query.setHitsPerPage(Integer.parseInt(noarticles));
        	SearchResult result = query.getResult();
        	List<Hit> l=result.getHits();
        	logger.info("No of pages display "+l.size());
        	for(Hit hit : l)
        	{
        		logger.info("title ::: "+hit.getNode().getPath());
        		Node temp=(Node)hit.getNode();
        		
        		logger.info("===========NODE Start=====================");
        		logger.info("text :: "+temp.getProperty("text").getString());
        		logger.info("title :: "+temp.getProperty("title").getString());
        		logger.info("subtitle :: "+temp.getProperty("subtitle").getString());
        		logger.info("align :: "+temp.getProperty("align").getString());
        		
        		
        			Node image=temp.getNode("image");
        			if(image!=null)
        			{
        				logger.info("Image ref :: "+image.getProperty("fileReference").getString());
        			}
        			logger.info("===========NODE End=====================");
        		
        			Article article=new Article();
        			article.setTitle(temp.getProperty("title").getString());
        			article.setSubtitle(temp.getProperty("subtitle").getString());
        			article.setText(temp.getProperty("text").getString());
        			article.setAlign(temp.getProperty("align").getString());
        			article.setImageRef(image.getProperty("fileReference").getString());
        			articleList.add(article);
        			
        			
        	}
        	logger.info("articleList.size-->"+articleList.size());
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