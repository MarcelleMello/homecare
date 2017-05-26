package br.com.homecare.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import br.com.homecare.model.Funcionario;

@SessionScoped
@ManagedBean(name="usuarioSessao")
public class UsuarioSessao extends AbstractBean implements Serializable{

	private static final long serialVersionUID = -7842545553301610129L;
	
	private Funcionario usuario;
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "login";
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public Funcionario getUsuario() {
		return usuario;
	}

	public void setUsuario(Funcionario usuario) {
		this.usuario = usuario;
	}

}
