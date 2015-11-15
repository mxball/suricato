<%@ attribute name="id" required="true" %>
<%@ attribute name="placeholder" required="false" %>
<input type="text" name="${id}" id="${id}" placeholder="${placeholder}">
<script>
	$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
</script>