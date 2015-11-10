package br.usp.suricato.websockets;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class ConteudoEncoder implements Encoder.Text<ConteudoJson> {

	@Override
	public void destroy() { 
	}

	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public String encode(ConteudoJson conteudoJson) throws EncodeException {
		JsonObject jsonObject = Json.createObjectBuilder()
									.add("tipoConteudo", conteudoJson.getTipoConteudo())
									.add("operacao", conteudoJson.getOperacao())
									.add("texto", conteudoJson.getTexto())
									.add("id", conteudoJson.getId())
									.add("posicaoHorizontal", conteudoJson.getPosicaoHorizontal())
									.add("posicaoVertical", conteudoJson.getPosicaoVertical())
									.add("largura", conteudoJson.getLargura())
									.add("altura", conteudoJson.getAltura())
									.add("cor", conteudoJson.getCor())
									.add("numeroVotos", conteudoJson.getNumeroVotos())
									.build();
		return jsonObject.toString();
	}

}
