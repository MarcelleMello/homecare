package br.com.homecare.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.homecare.model.Funcionario;
import br.com.homecare.model.tipo.SimNao;
import br.com.homecare.service.FuncionarioService;
import br.com.homecare.service.ServiceException;
import br.com.homecare.util.Util;

@ViewScoped
@ManagedBean(name="loginBean")
public class LoginBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -9008624429172522134L;
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;
	
	private String matricula;
	private String senha;
	
	@PostConstruct
	public void init() {
		
	}
	
	public String realizarLogin() {
		try {
			
			FuncionarioService service = new FuncionarioService();

			Funcionario f = service.realizarLogin(matricula, Util.getHash(senha, "MD5"));
			
			if(f == null) {
				displayErrorMessage("Matrícula e/ou senha invalido.");
				return "login";
			}
			
			if(f != null && f.getAtivo().equals(SimNao.NAO)) {
				displayErrorMessage("Funcionário inativo.");
				return "login";
			}
			
			usuarioSessao.setUsuario(f);
			return "principal";
			
		} catch (ServiceException e) {
			displayErrorMessage(e.getMessage());
		} catch (Exception e) {
			displayErrorMessage("Não foi possível realizar o login.");
			e.printStackTrace();
		}
		return "login";
	}

	public UsuarioSessao getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
