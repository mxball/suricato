<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="value" type="java.util.Calendar" required="false" %>

<input type="text" name="${id}" id="${id}" class="cadastro-campo" value='<fmt:formatDate value="${value.time}" pattern="dd/MM/yyyy"/>'>
<script>
	$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
</script>