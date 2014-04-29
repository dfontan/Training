<%--

  RegionInfo component.

  Get Region Info by Pin Code

--%><%
%><%@include file="/apps/IntelliTraining/components/global.jsp"%><%
%><%@page session="false" %><%
%>
<script type="text/javascript">
    $(document).ready(function(){



        $("#btn").click(function(){

             var zip=document.getElementById('zip').value;
            var url = "https://maps.googleapis.com/maps/api/geocode/json?address="+zip+"&sensor=true&key=AIzaSyBK-CYp_jDkTa3AisoT_C6V4Gr1OeR_RiU";
            alert("zip :: "+zip+" URL ::: "+url);
			$.getJSON(url, function(data){
                var result = data['results'];

                var val=result[0].address_components[1].long_name;
                  alert("val ::: "+val);
				document.getElementById('region').innerHTML=val;

        	});
        });

});

</script>
<form action="" method="GET">
	<input type="text" name="zipcode" id="zip"/>
    <input type="button" id="btn" value="Click Me!" />
</form>
<div id="region"></div>

<%
	
%>