package br.com.homecare.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;




@ViewScoped
@ManagedBean(name="cadastrarFuncionarioBean")
public class ConsultarFuncionarioBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 6712522758268465657L;
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;

	
}
