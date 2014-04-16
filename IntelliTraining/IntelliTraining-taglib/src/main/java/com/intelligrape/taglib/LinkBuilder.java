package com.intelligrape.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;  
import javax.servlet.jsp.JspWriter;  
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.designer.Style;
import com.day.cq.wcm.foundation.Navigation;

public class LinkBuilder extends SimpleTagSupport
{
	public void doTag() throws JspException
	{ 
		System.out.println("::::::Inside Link Builder :::::::::");
		JspWriter out = getJspContext().getOut();
	    StringBuilder str=new StringBuilder();
	    try{  
	    	
	    	
	    	PageContext pageContext = (PageContext) getJspContext();
	    	
	    	
	    	System.out.println("resource OBJ-------" + pageContext.getAttribute("resource"));

	    	System.out.println("request-------" + pageContext.getRequest());
	    	System.out.println("page context ----------" + pageContext.getAttribute("pageContext"));
	    	
	    	Page currentPage=(Page)pageContext.getAttribute("currentPage");
	    	System.out.println("currentPage :: "+currentPage);
	//       Page homePage = currentPage.getAbsoluteParent(2);
	  //     System.out.println("Name is :::: "+homePage.getName());
	    //   String home = homePage != null ? homePage.getPath() : Text.getAbsoluteParent(currentPage.getPath(), 2);
	        Style currentStyle=(Style) pageContext.getAttribute("currentStyle");	
	        System.out.println("currentStyle :: "+currentStyle);
	        int absParent1 = currentStyle.get("absParent", 2);
	        System.out.println("absParent1 :: "+absParent1);
	       
	        PageFilter filter = new PageFilter(pageContext.getRequest());
	    	System.out.println("filter :: "+filter);
	        Navigation nav = new Navigation(currentPage, absParent1, filter, 3);
	       
	        String linkCheckerHint = filter.isIncludeInvalid() ? "" : "x-cq-linkchecker=\"valid\"";
	        str.append("<li>");
	        for (Navigation.Element e: nav) {
	        	//System.out.println("Has Children"+e.hasChildren());
	            switch (e.getType()) {
	               case NODE_OPEN:
	                    str.append("<ul>");
	                    break;
	                case ITEM_BEGIN:
	                	System.out.println(e.getPath());
	                    str.append("<li><a href="+e.getPath()+".html "+linkCheckerHint+">"+ e.getTitle()+"</a>");
	                    break;
	                case ITEM_END:
	                	str.append("</li>");
	                    break;
	                case NODE_CLOSE:
	                	str.append("</ul>");
	                    break;
	            }
	        }
	        System.out.println(str.toString());
	        out.write(str.toString());
	        
	        
	    }catch(Exception e)
	    {
	    	
	    	e.printStackTrace();
	    }  
	    //will not evaluate the body content of the tag  
	}  
} 

