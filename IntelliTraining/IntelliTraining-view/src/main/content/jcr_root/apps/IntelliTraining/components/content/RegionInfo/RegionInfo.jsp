<%--

  RegionInfo component.

  Get Region Info by Pin Code

--%><%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" %><%
%>
<script type="text/javascript">
function getCountryInfo()
{
	var zip=document.getElementById('zip').value;
	alert("ZIP ::: "+zip)
	$.ajax({
		type: "get",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        url: "https://maps.googleapis.com/maps/api/geocode/json?address=110058&sensor=true&key=AIzaSyBK-CYp_jDkTa3AisoT_C6V4Gr1OeR_RiU",
        success: function (data) { alert(data.d); }
        error: function (request, status, error) {
            alert(request.responseText);
          }
    });

}
</script>
<form>
	<input type="text" name="zipcode" id="zip"/>
	<button onclick="getCountryInfo();">Click me</button> 
</form>

<%
	
%>