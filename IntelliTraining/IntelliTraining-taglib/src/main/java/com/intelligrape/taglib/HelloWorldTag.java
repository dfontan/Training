package com.intelligrape.taglib;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.squeakysand.jsp.tagext.EnhancedSimpleTagSupport;
import com.squeakysand.jsp.tagext.annotations.JspTag;

/**
 * @author <a href="http://craigsdickson.com">Craig S. Dickson</a>
 */
@JspTag
public class HelloWorldTag extends EnhancedSimpleTagSupport {

   
   public void doTag() throws JspException, IOException {
	   getJspWriter().write("Hello from tag");
   }
}