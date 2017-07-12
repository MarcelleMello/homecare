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
	
	private static List<String> home, funcionario, cidadao, familia, livres;

	 //lista de paginas restrias e livres
	static {
		
		if( home == null ) {
			home = new ArrayList<String>();
			home.add("/paginas/privado/index.xhtml");
		}
		
		if( funcionario == null ) {
			funcionario = new ArrayList<String>();
			funcionario.add("/paginas/funcionario/consultar.xhtml");
			funcionario.add("/paginas/funcionario/cadastrar.xhtml");
			funcionario.add("/paginas/funcionario/editar.xhtml");
		}
		
		if( familia == null ) {
			familia = new ArrayList<String>();
			familia.add("/paginas/familia/consultar.xhtml");
			familia.add("/paginas/familia/cadastrar.xhtml");
			familia.add("/paginas/familia/editar.xhtml");
		}
		
		if( cidadao == null ) {
			cidadao = new ArrayList<String>();
			cidadao.add("/paginas/cidadao/consultar.xhtml");
			cidadao.add("/paginas/cidadao/cadastrar.xhtml");
			cidadao.add("/paginas/cidadao/editar.xhtml");
		}
		
		if( livres == null ) {
			livres = new ArrayList<String>();
			livres.add("/paginas/publico/index.xhtml");
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
		
		boolean urlAdmin, urlHome, urlAgente;
		
		urlAgente = (cidadao.contains(paginaDestino)); //verifica se a pagina e protegida 
		
		urlAdmin = (funcionario.contains(paginaDestino)); //verifica se a pagina e protegida 
		
		urlHome = (home.contains(paginaDestino)); //verificar se a pagina e de administracao
		
		boolean sessaoExpirou = (boolean) ((ec.getSessionMap().get("sessaoExpirou") != null) ? ec.getSessionMap().get("sessaoExpirou") : false);  

		try {
			//pagina restrita e usuario nao logado 
			if((urlHome || urlAdmin || urlAgente) && !logado && !sessaoExpirou) {
				ec.redirect(path + "/paginas/publico/403.jsf");
			
			//nao sendo pagina restrita, nao estando logado , sessao expirou, pagina nao for de admin e pagina nao for de primeiro acesso
			} else if(!(urlHome || urlAdmin || urlAgente) && !logado && sessaoExpirou && !urlAdmin) {
				ec.getSessionMap().put("sessaoExpirou", false);  
				ec.redirect(path + "/paginas/publico/index.jsf?faces-redirect=true&includeViewParams=true&sessaoexpirou=true");
				
			//pagina adiministrador e usuario nao logado	
			} else if((urlHome || urlAdmin || urlAgente) && !logado && !sessaoExpirou) {
				ec.redirect(path + "/paginas/public/403.jsf");
			
			} else if((urlHome || urlAdmin || urlAgente) && !logado && !sessaoExpirou) {
				ec.redirect(path + "/paginas/public/403.jsf");
			
			}
		} catch (Exception ex) {
	
		}
	}
}