<%--

  Simple Text Component Using CRXDE

  My component

--%><%
%><%
%><%@page session="false" %>
<%@ page import="com.Training.*" %>
<%@include file="/apps/cqblueprints-example/components/global.jspx" %>
<%
%><%
	// TODO add you code here
%>

${properties.text}
<%
    HelloWorld obj = sling.getService(HelloWorld.class);
    out.println("<br>Example to call service inside component <br> "+obj.getName());
%>
