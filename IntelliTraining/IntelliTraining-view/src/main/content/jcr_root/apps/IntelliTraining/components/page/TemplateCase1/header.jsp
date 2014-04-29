<%--

  TemplateCase1 component.

  Component Mapped inside Home Page as primary component

--%>
<%@include file="/apps/IntelliTraining/components/global.jsp"%>

<%@ page session="false" %>

	<m:linkBuild/>
<ul>	
 <c:forEach var="tags" items="${list}">
 <li><a x-cq-linkchecker="valid"  href="http://localhost:4502/${tags.url}.html">${tags.title}</a></li>
     <ul>
 	<c:forEach var="subtags" items="${tags.validChild}">

 	    <li><a x-cq-linkchecker="valid"  href="http://localhost:4502/${subtags.url}.html">${subtags.title}</a></li>
        <ul>
        <c:forEach var="subsubtags" items="${subtags.validChild}">

 	    	    <li><a x-cq-linkchecker="valid"  href="http://localhost:4502/${subsubtags.url}.html">${subsubtags.title}</a></li>
 	    	    <!-- Code Added -->
				<ul>
			        <c:forEach var="subsubsubtags" items="${subsubtags.validChild}">
			
			 	    	    <li><a x-cq-linkchecker="valid"   href="http://localhost:4502/${subsubsubtags.url}.html">${subsubsubtags.title}</a></li>
			
			 		</c:forEach>
			     </ul>
			     <!-- Code Added -->
 		</c:forEach>
        </ul>

 	</c:forEach>
        </ul>
 </c:forEach>
 </ul>
  
 


 
