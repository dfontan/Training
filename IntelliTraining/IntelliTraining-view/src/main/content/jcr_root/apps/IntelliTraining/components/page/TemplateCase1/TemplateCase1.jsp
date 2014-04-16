<%--

  TemplateCase1 component.

  Component Mapped inside Home Page as primary component

--%><%
%><%@include file="/libs/foundation/global.jsp"%>
<%@include file="/apps/cqblueprints-example/components/init.jsp"%>
<%
%><%@page session="false" %><%
%><%
	// TODO add you code here
%>
   

<html>
	<head>
		       <cq:includeClientLib categories="trainnew" />
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
