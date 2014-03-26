<%--

  Simple Text Component Using CRXDE

  My component

--%><%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" %>
<%@ page import="com.Training.*" %>
<%
%><%
	// TODO add you code here
%>

Hello
${properties.myname}
<%
HelloWorld prd = sling.getService(HelloWorld.class);
    out.println("Example to call service inside component "+prd.getName());
%>
