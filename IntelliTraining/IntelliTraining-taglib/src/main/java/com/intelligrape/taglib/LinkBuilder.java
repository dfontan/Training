package com.intelligrape.taglib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;  
import javax.servlet.jsp.JspWriter;  
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.designer.Style;
import com.day.cq.wcm.foundation.Navigation;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkBuilder extends SimpleTagSupport
{
	final  Logger logger = LoggerFactory.getLogger(LinkBuilder.class);
	ArrayList<ArrayList<String>> links=new ArrayList<ArrayList<String>>();
	ArrayList <LinkComponent> alTemp=new ArrayList();
	ArrayList<Page> pagesArray=new ArrayList();
	
	public void doTag() throws JspException
	{ 
		
		logger.info("::::::Inside Link Builder :::::::::");
		logger.debug(":::::This is logger:::::");
		logger.info(":::::This is Info:::::");
		
		JspWriter out = getJspContext().getOut();
	    StringBuilder str=new StringBuilder();
	    try{  
	    	
	    	
	    	PageContext pageContext = (PageContext) getJspContext();
	    	
	    	
	    	logger.info("resource OBJ-------" + pageContext.getAttribute("resource"));
	    	
/*
	    	logger.info("request-------" + pageContext.getRequest());
	    	logger.info("page context ----------" + pageContext.getAttribute("pageContext"));*/
	    	Resource resource = (Resource) getJspContext().getAttribute("resource");
	    	String path=resource.getParent().getPath();
	     	logger.info("Resource -------" + resource.getPath());   	
	    	logger.info("currentpage ::: "+resource.getParent().listChildren());	    	
	    //	Iterator itr = resource.getParent().listChildren();
         	   	
         	logger.info("path ::: "+path);
         	PageManager pageManager=resource.getResourceResolver().adaptTo(PageManager.class);
	    	logger.info("pagemanager ::: "+pageManager);
	    	String homePath="/content/IntelliTraining/en";
	    	com.day.cq.wcm.api.Page homePage=pageManager.getPage(homePath);
	    	logger.info("Homepage ::: "+homePage);
	    	logger.info("Homepage ::: "+homePage.getName());
	    	logger.info(" Path ::: "+homePage.getPath());
	    	Iterator<Page> pages=homePage.listChildren();
	    	while(pages.hasNext())
	    	{
	    		
	    		Page pag=pages.next();
	    		LinkComponent comp=getPageInfo(pag);
	    		alTemp.add(comp);
	    		
	    	}
	    		    	
	    	logger.info("altemp ::: "+alTemp);
	    	for(LinkComponent obj:alTemp)
	    	{
	    		logger.info("Object ::: "+obj.getTitle());
	    		logger.info("Object ::: "+obj.getUrl());
	    		
	    		if(obj.getValidChild().size() > 0)
	    		{
	    			for(LinkComponent listCompo : obj.getValidChild()) {
	    				logger.info("SUB CHILD -->"+listCompo.getTitle());
	    				logger.info("SUB CHILD -->"+listCompo.getUrl());
	    				
	    				if(listCompo.getValidChild().size() > 0)
	    	    		{
	    	    			for(LinkComponent subCompo : listCompo.getValidChild()) {
	    	    				logger.info("SUB CHILD -->"+subCompo.getTitle());
	    	    				logger.info("SUB CHILD -->"+subCompo.getUrl());
	    	    				if(subCompo.getValidChild().size() > 0)
	    	    	    		{
	    	    	    			for(LinkComponent subsubCompo : subCompo.getValidChild()) {
	    	    	    				logger.info("SUB SUB  CHILD -->"+subsubCompo.getTitle());
	    	    	    				logger.info("SUB SUB CHILD -->"+subsubCompo.getUrl());
	    	    	    				
	    	    	    				
	    	    	    			}
	    	    	    		}
	    	    				
	    	    				
	    	    			
	    	    		}
	    			}
	    		}
	    		}
	    		
	    		/*for(Object o:al)
	    		{
	    			Page test=(Page)o;
	    			logger.info("Page ::: "+test.getName());
	    		}*/
	    		/*Page p2=(Page)linkComponent.getValidChild().get(1);
	    		logger.info(" Child Page 1 :: "+p1.getName() +" :::: "+p1.getPath());
	    		logger.info(" Child Page 2 :: "+p2.getName() +" :::: "+p2.getPath());*/
	    		
	    	}
	    	
	    	/*
	    	while(itr.hasNext())
	    	{
	    		Resource res = (Resource) itr.next();
	    		logger.info("path--"+res.getPath() +"name --- "+res.getName());
	    		LinkComponent bean=new LinkComponent();
	    		bean.setTitle(res.getName());
	    		bean.setUrl(res.getPath());
	    		Iterator itr1=(Iterator)res.getChildren();
	    		while(itr1.hasNext())
	    		{
	    			Resource res1 = (Resource) itr1.next();
	    			logger.info("path itr1--"+res.getPath() +"name itr1--- "+res.getName());
	    		}
	    	}
	    	*/
//	    	logger.info("currentpage ::: "+currentpage.getPath());
//	    	logger.info("currentPage :: "+currentpage.getPath());
	 //   	LinkComponent page1=new LinkComponent(currentpage);
	  //  	logger.info("Page title ::: "+page1.title);
	  //  	logger.info("Page title ::: "+page1.url);
	     //   Page homePage = currentpage.getAbsoluteParent(2);
	    //    logger.info("Home Page Name is :::: "+homePage.getName());
	         
	     //  String home = homePage != null ? homePage.getPath() : Text.getAbsoluteParent(currentPage.getPath(), 2);
	       /* Style currentStyle=(Style) pageContext.getAttribute("currentStyle");	
	        logger.info("currentStyle :: "+currentStyle);
	        int absParent1 = currentStyle.get("absParent", 2);
	        logger.info("absParent1 :: "+absParent1);
	       
	        PageFilter filter = new PageFilter(pageContext.getRequest());
	    	logger.info("filter :: "+filter);
	        Navigation nav = new Navigation(currentpage, absParent1, filter, 3);
	       
	        String linkCheckerHint = filter.isIncludeInvalid() ? "" : "x-cq-linkchecker=\"valid\"";
	        str.append("<li>");
	        List parent =new ArrayList();
	        HashMap map=new HashMap();
	        
	        for (Navigation.Element e: nav) {
	        	//logger.info("Has Children"+e.hasChildren());
	       
	        	logger.info("e.getType() ::: "+e.getType()); 
	            switch (e.getType()) {
	               case NODE_OPEN:
	                    str.append("<ul>");
	                    logger.info(e.getPath());
	                   
	                    break;
	                case ITEM_BEGIN:
	                	logger.info(e.getPath());
	                	
	                	
	                    str.append("<li><a href="+e.getPath()+".html "+linkCheckerHint+">"+ e.getTitle()+"</a>");
	                    break;
	                case ITEM_END:
	                	 logger.info(e.getPath());
	                	str.append("</li>");
	             
	                    break;
	                case NODE_CLOSE:
	                	 logger.info(e.getPath());
	                   	str.append("</ul>");
	                	
	                    break;
	                    
	            }
	          
	        }
	        logger.info(str.toString());
	        logger.info("parent size ::: "+parent.size()+ "Elements are ::: "+parent);
	        logger.info("Map size ::: "+map.size()+ "Elements are ::: "+map);
	      
	         out.write(str.toString());*/
	        
	        
	    }catch(Exception e)
	    {
	    	
	    	e.printStackTrace();
	    } 
	    
	    getJspContext().setAttribute("list", alTemp);
	}

	private LinkComponent getPageInfo(Page pag) {
		
		    LinkComponent lc=new LinkComponent();
		
			lc.setTitle(pag.getName());
			lc.setUrl(pag.getPath());
			logger.info("Name is "+pag.getName());		
			List<Page> tempChild=new ArrayList();
			LinkComponent component;
			List<LinkComponent> list = new ArrayList();
			Iterator<Page> pageItr=pag.listChildren();						
				while(pageItr.hasNext()) {
					Page child=pageItr.next();
					component = new LinkComponent();
					logger.info(":::  Inside while pageItr  ::: "+child.getName()+" ::: "+child.getPath());
					component.setTitle(child.getName());
					component.setUrl(child.getPath());					
					
					List<LinkComponent> sublist = new ArrayList();
					Iterator<Page> subChildItr = child.listChildren();
					LinkComponent subChildComponent;
					while(subChildItr.hasNext()) {
						Page subChild = subChildItr.next();
						subChildComponent = new LinkComponent();					
						subChildComponent.setTitle(subChild.getTitle());
						subChildComponent.setUrl(subChild.getPath());
						
						List<LinkComponent> subsublist=new ArrayList();
						Iterator<Page> subsubchildItr=subChild.listChildren();
						LinkComponent subsubChildComponent = null;
						while(subsubchildItr.hasNext())
						{
							Page subsubChild = subsubchildItr.next();
							subsubChildComponent = new LinkComponent();
							subsubChildComponent.setTitle(subsubChild.getName());
							subsubChildComponent.setUrl(subsubChild.getPath());
							subsublist.add(subsubChildComponent);
							
						}
						subChildComponent.setValidChild(subsublist);
						sublist.add(subChildComponent);
					}
					component.setValidChild(sublist);
					list.add(component);	
		    					
		    	}				
			lc.setValidChild(list);
				 
				return lc;	
		}
	
}

