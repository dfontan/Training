<%--

  TemplateCase1 component.

  Component Mapped inside Home Page as primary component

--%><%
%><%@include file="/apps/IntelliTraining/components/global.jsp"%>
<%@include file="/apps/IntelliTraining/components/init.jsp"%>
<%
%><%@page session="false" %><%
%>
   

<html>
	<head>
		       <cq:includeClientLib categories="trainnew" />
		       <meta name="description" content="Template Page">
				<meta name="keywords" content="HTML,CSS,XML,JavaScript">
				<meta name="author" content="Intelligrape">
				<meta charset="UTF-8">
    </head>
    <body>
        <div id="ajax"></div>

        <cq:include script="header.jsp"/>
        <br>
        <cq:include path="parsys" resourceType="foundation/components/parsys" />
        <cq:include script="content.jsp"/>
        <br>
        <cq:include script="footer.jsp"/>
    </body>
</html>
