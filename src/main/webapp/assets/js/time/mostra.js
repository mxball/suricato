var url = $("#nomeUsuario").data("url");
console.log(url);
$("#nomeUsuario").autocomplete({
	minLength: 3,
    delay: 500,
    source: function (request, response) {
        $.getJSON(url + "/usuario/busca", request, function(result) {
            response($.map(result, function(usuario) {
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