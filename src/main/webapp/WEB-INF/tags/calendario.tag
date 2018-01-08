<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="false" %>
<%@ attribute name="value" type="java.util.Calendar" required="false" %>
<label for="${id}" class="calendario cadastro-descricao --data">
	${label}
	<input type="text" name="${id}" id="${id}" class="cadastro-campo" value='<fmt:formatDate value="${value.time}" pattern="dd/MM/yyyy"/>'>
</label>
<script>
	$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
</script>