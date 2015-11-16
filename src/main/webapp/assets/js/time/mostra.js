$("#nomeUsuario").autocomplete({
	minLength: 5,
    delay: 1000,
    source: function (request, response) {
        $.getJSON("/usuario/busca", request, function(result) {
            response($.map(result, function(usuario) {
            	console.log(usuario.nome);
            	return {
                    label: usuario.nome,
                    value: usuario.id
                }
            }));
        });
    },
    select : function(event, ui) {
        if (ui.item) {
            event.preventDefault();
            $("#nomeUsuario").val(ui.item.label);
            return false;
        }
    }
});