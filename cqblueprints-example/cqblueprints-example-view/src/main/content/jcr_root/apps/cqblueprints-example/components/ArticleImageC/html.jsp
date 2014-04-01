<%--

  Articleimagec component.

  

--%>
    <%@ page import="com.day.cq.commons.Doctype,
    com.day.cq.wcm.api.WCMMode,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.foundation.Image, com.day.cq.wcm.foundation.Placeholder" %><%
%>
<%
%>
<%@include file="/libs/foundation/global.jsp"%>
<div id='title'>${properties.title}</div>
<div class='subtitle'>${properties.subtitle}</div>
<c:if test="${properties.align=='right' || properties.align=='' || properties.align==null}">
	<c:set var="ali" scope="page" value="right"/>
</c:if>

<c:if test="${properties.align=='left'}">
	<c:set var="ali" scope="page" value="left"/>
</c:if>
<div class= '${ali}'>
    <%
    Image image = new Image(resource, "image");
    image.draw(out); 
	%>
</div>


${properties.text}
<div style="clear:both" ></div>




