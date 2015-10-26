<%@ attribute name="id" required="true" %>
<input type="text" name="${id}" id="${id}">
<script>
	$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
</script>