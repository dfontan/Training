<%--

  homepageparent component.

  mapped inside component as super

--%><%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" %><%
%><%
	// TODO add you code here
%>

    <html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
	<cq:includeClientLib categories="train1" />
</head>
<body>
    <cq:include script="header.jsp" />
    <cq:include script="body.jsp" />
    <div id="ajax"></div>
    <cq:include script="footer.jsp" />
</body>
</html>