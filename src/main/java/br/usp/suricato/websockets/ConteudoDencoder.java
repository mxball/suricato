package br.usp.suricato.websockets;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;


public class ConteudoDencoder implements Decoder.Text<ConteudoJson> {

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

	@Override
	public ConteudoJson decode(String mensagem) throws DecodeException {
		JsonObject jsonObject = Json.createReader(new StringReader(mensagem)).readObject();
		System.out.println(jsonObject);
		ConteudoJson conteudoJson = new ConteudoJson();
		conteudoJson.setTipoConteudo(jsonObject.getString("tipoConteudo"));
		conteudoJson.setOperacao(jsonObject.getString("operacao"));
		conteudoJson.setTexto(jsonObject.getString("texto"));
		conteudoJson.setId(jsonObject.getInt("id"));
		conteudoJson.setPosicaoHorizontal(jsonObject.getJsonNumber("posicaoHorizontal").doubleValue());
		conteudoJson.setPosicaoVertical(jsonObject.getJsonNumber("posicaoVertical").doubleValue());
		conteudoJson.setLargura(jsonObject.getInt("largura"));
		conteudoJson.setAltura(jsonObject.getInt("altura"));
		conteudoJson.setCor(jsonObject.getString("cor"));
		return conteudoJson;
	}

	@Override
	public boolean willDecode(String mensagem) {
		try{
			Json.createReader(new StringReader(mensagem)).readObject();
			return true;
		} catch(Exception e ) {
			return false;
		}
	}

}
