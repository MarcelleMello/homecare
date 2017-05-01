package br.com.homecare.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.homecare.bean.UsuarioSessao;

public class Autenticacao implements PhaseListener {

	private static final long serialVersionUID = 7894192061613723345L;
	
	private static List<String> restritos, livres, admin, primeiroAcesso;

	 //lista de paginas restrias e livres
	static {
		
		if( restritos == null ) {
			restritos = new ArrayList<String>();
			livres.add("/paginas/privado/index.xhtml");
		}
		
		if( livres == null ) {
			livres = new ArrayList<String>();
			livres.add("/paginas/publico/index.xhtml");
		}
		
		if( admin == null ) {
			admin = new ArrayList<String>();
			
		}
		
	}
	
	//notificando antes e depois de todas as fases
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; 
	}
	
	//antes da fase de validacao
	public void beforePhase(PhaseEvent event) { }

	//depois da fase de validacao
	public void afterPhase(PhaseEvent event) {

		FacesContext fc = event.getFacesContext();  
	   
		ExternalContext ec = fc.getExternalContext();  
	    	    
	    UsuarioSessao usuarioSessao = fc.getApplication().evaluateExpressionGet (fc , "#{usuarioSessao}" , UsuarioSessao.class);
	    
	    boolean logado = (usuarioSessao.getUsuario() != null);
	    
	    String path = ec.getRequestContextPath();
	   
	    String paginaDestino = fc.getViewRoot().getViewId();
		
		boolean urlProtegida, urlAdmin;
		
		urlProtegida = (restritos.contains(paginaDestino)); //verifica se a pagina e protegida 
		
		urlAdmin = (admin.contains(paginaDestino)); //verificar se a pagina e de administracao
		
		boolean sessaoExpirou = (boolean) ((ec.getSessionMap().get("sessaoExpirou") != null) ? ec.getSessionMap().get("sessaoExpirou") : false);  

		try {
			//pagina restrita e usuario nao logado 
			if(urlProtegida && !logado && !sessaoExpirou) {
				ec.redirect(path + "/paginas/usuario/index.jsf");
			
			//nao sendo pagina restrita, nao estando logado , sessao expirou, pagina nao for de admin e pagina nao for de primeiro acesso
			} else if(!urlProtegida && !logado && sessaoExpirou && !urlAdmin) {
				ec.getSessionMap().put("sessaoExpirou", false);  
				ec.redirect(path + "/paginas/usuario/index.jsf?faces-redirect=true&includeViewParams=true&sessaoexpirou=true");
				
			//pagina adiministrador e usuario nao logado	
			} else if(urlAdmin && !logado && !sessaoExpirou) {
				ec.redirect(path + "/paginas/usuario/index.jsf");
			
			}
		} catch (Exception ex) {
	
		}
	}

	public static List<String> getPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public static void setPrimeiroAcesso(List<String> primeiroAcesso) {
		Autenticacao.primeiroAcesso = primeiroAcesso;
	}
}