<%@include file="global.jspx"%><%
%><%@ page import="com.day.text.Text" %><%

    long absParent = currentStyle.get("absParent", 2L);
    String home = Text.getAbsoluteParent(currentPage.getPath(), (int) absParent);
    Resource image = currentStyle.get("image", Resource.class);
    %><a href="<%= home %>.html"><%
    if (image ==null) {
        %>Home<%
    } else {
        %><img border="0" src="<%= image.getPath() %>" alt="Home" ><%
    }
    %></a>