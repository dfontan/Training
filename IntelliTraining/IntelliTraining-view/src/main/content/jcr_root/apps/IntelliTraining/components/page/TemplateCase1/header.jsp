<%--

  TemplateCase1 component.

  Component Mapped inside Home Page as primary component

--%><%
%><%@include file="/libs/foundation/global.jsp"%>

<%
%><%@page session="false" %><%
%><%
	// TODO add you code here
%>
<%@ page session="false" import="java.util.Iterator,com.day.cq.wcm.api.PageFilter" %>
<%@ page import="com.day.cq.commons.Doctype,
        com.day.cq.wcm.api.PageFilter,
        com.day.cq.wcm.foundation.Navigation,
        com.day.text.Text" %>

  
    <%
     // get starting point of navigation
   /* Page homePage = currentPage.getAbsoluteParent(2);
    System.out.println("Name is :::: "+homePage.getName());
    String home = homePage != null ? homePage.getPath() : Text.getAbsoluteParent(currentPage.getPath(), 2);
    int absParent1 = currentStyle.get("absParent", 2);

    PageFilter filter = new PageFilter(request);
    Navigation nav = new Navigation(currentPage, absParent1, filter, 3);
    String xs = Doctype.isXHTML(request) ? "/" : "";
    String linkCheckerHint = filter.isIncludeInvalid() ? "" : "x-cq-linkchecker=\"valid\"";
    %><li><%
    for (Navigation.Element e: nav) {
        switch (e.getType()) {
           case NODE_OPEN:
                %><ul><%
                break;
            case ITEM_BEGIN:
                %><li <%= e.hasChildren() ? "class=\"noleaf\"" : "" %>><a href="<%= e.getPath() %>.html" <%= linkCheckerHint %>><%= e.getTitle() %></a><%
                break;
            case ITEM_END:
                %></li><%
                break;
            case NODE_CLOSE:
                %></ul><%
                break;
        }
    }
   
    */
%>


 
   