package com.suricatoagil.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suricatoagil.daos.ComentarioDao;
import com.suricatoagil.daos.PostItDao;
import com.suricatoagil.models.Comentario;
import com.suricatoagil.models.PostIt;
import com.suricatoagil.models.Retrospectiva;

@Repository
public class AtualizaRetrospectiva {

	@Autowired
	private PostItDao postItDao;
	
	@Autowired
	private ComentarioDao comentarioDao;
	
	public void atualiza(Retrospectiva retrospectiva, ConteudoJson conteudo) {
		String operacao = conteudo.getOperacao();
		if(conteudo.getTipoConteudo().equals("postIt")) {
			PostIt postIt = conteudo.getPostIt();
			postIt.setRetrospectiva(retrospectiva);
			if(operacao.equals("adiciona")) {
				postItDao.saveAsync(postIt);
				conteudo.setId(postIt.getId());
			} else if(operacao.equals("remove")) {
				postItDao.removeAsync(postIt);
			} else if(operacao.equals("atualiza")) {
				postItDao.updateAsync(postIt);
			} else if(operacao.equals("adicionaLike")) {
				postItDao.atualizaNumeroVotos(postIt);
			} else if(operacao.equals("adicionaDeslike")) {
				postItDao.atualizaNumeroDeslikes(postIt);
			}
		} else if (conteudo.getTipoConteudo().equals("comentario")) {
			Comentario comentario = conteudo.getComentario();
			comentario.setRetrospectiva(retrospectiva);
			if(operacao.equals("adiciona")) {
				comentarioDao.saveAsync(comentario);
				conteudo.setId(comentario.getId());
			} else if(operacao.equals("remove")) {
				comentarioDao.removeAsync(comentario);
			} else if(operacao.equals("atualiza")) {
				comentarioDao.updateAsync(comentario);
			}
		}
	}

}

