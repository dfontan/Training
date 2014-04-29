<%--

  List Article component.

  Display Article on Page

--%><%
%><%@include file="/apps/IntelliTraining/components/global.jsp"%>
<%@ page import="java.util.*" %>

<%
%><%@page session="false" %><%
%>
    ---- ${properties.type}<br>
   <%
	List AricleList = (ArrayList) pageContext.getAttribute("articleList");
	%>
<c:if test="${properties.type=='Article'}">

	<m:DisplayArticle path="${properties.pagePath}" noarticles="${properties.numarticle}"/>


  <c:forEach var="article" items="${articleList}">
        <div id='title'>${article.title}</div>
        <div class='subtitle'>${article.subtitle}</div>
        
        <c:if test="${article.align=='right' || article.align=='' || article.align==null}">
            <c:set var="ali" scope="page" value="right"/>
        </c:if>
        
        <c:if test="${article.align=='left'}">
            <c:set var="ali" scope="page" value="left"/>
        </c:if>
        <div class= '${ali}'>
           <img src=${article.imageRef} height=100 width=200/>
        </div>
		${article.text}
		<div style="clear:both" ></div>
     </c:forEach>
</c:if>
<!-- Need to create hyperlink using substring from en -->

<c:if test="${properties.type=='Tags'}">
	<m:DisplayTag path="${properties.pagePath}" operation="${properties.op}"/>
		
		 <c:forEach var="article" items="${myMap}">
          Page is :  <a x-cq-linkchecker="valid" href="http://localhost:4502${article.key}.html">${article.value}</a><br>
		</c:forEach> 
</c:if>