package br.usp.suricato.websockets;

import org.springframework.context.ApplicationContext;

import br.usp.suricato.daos.ComentarioDao;
import br.usp.suricato.daos.PostItDao;
import br.usp.suricato.models.Comentario;
import br.usp.suricato.models.PostIt;
import br.usp.suricato.models.Retrospectiva;

public class AtualizaRetrospectiva {

	public static Atualizador paraContexto(ApplicationContext ctx) {
		return new Atualizador(ctx);
	}

}

class Atualizador {
	
	private ApplicationContext ctx;
	
	public Atualizador(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	public void atualiza(Retrospectiva retrospectiva, ConteudoJson conteudo) {
		String operacao = conteudo.getOperacao();
		if(conteudo.getTipoConteudo().equals("postIt")) {
			PostItDao postItDao = this.ctx.getBean(PostItDao.class);
			PostIt postIt = conteudo.getPostIt();
			postIt.setRetrospectiva(retrospectiva);
			if(operacao.equals("adiciona")) {
				postItDao.saveAsync(postIt);
				conteudo.setId(postIt.getId());
			} else if(operacao.equals("remove")) {
				postItDao.removeAsync(postIt);
			} else if(operacao.equals("atualiza")) {
				postItDao.updateAsync(postIt);
			}
		} else if (conteudo.getTipoConteudo().equals("comentario")) {
			ComentarioDao comentarioDao = this.ctx.getBean(ComentarioDao.class);
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
