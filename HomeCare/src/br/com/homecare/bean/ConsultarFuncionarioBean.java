package br.com.homecare.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.homecare.model.Funcionario;
import br.com.homecare.service.FuncionarioService;




@ViewScoped
@ManagedBean(name="consultarFuncionarioBean")
public class ConsultarFuncionarioBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 6712522758268465657L;
	
	private String tipoBusca;
	private Funcionario funcionarioFiltro;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;	
	private List<SelectItem> tipoBuscas;
	private String view;
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;	
	
	@PostConstruct
	public void init() {
		view = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		tipoBuscas = new ArrayList<SelectItem>();	
		
		funcionarioFiltro = new Funcionario(); 
		
		
		if(view.endsWith("consultar.xhtml")) {
			carregarTipoBuscas();
		}
		consultar();
	}
		
	
public String getView() {
		return view;
	}


	public void setView(String view) {
		this.view = view;
	}


public void consultar() {
	try {
			
		funcionarios = new FuncionarioService().consultar(funcionarioFiltro, usuarioSessao.getUsuario());	
		
		}
		  catch (Exception ex) {
			ex.printStackTrace();
		}		
		
}


public void carregarTipoBuscas() {
	try {
		
		tipoBuscas.add(new SelectItem("Selecione"));
		tipoBuscas.add(new SelectItem("Nome"));
		tipoBuscas.add(new SelectItem("Telefone"));
		
	
	} catch (Exception e) {
		e.printStackTrace();
	}
}




public Funcionario getFuncionario() {
	return funcionario;
}


public Funcionario getFuncionarioFiltro() {
	return funcionarioFiltro;
}


public void setFuncionarioFiltro(Funcionario funcionarioFiltro) {
	this.funcionarioFiltro = funcionarioFiltro;
}


public void setFuncionario(Funcionario funcionario) {
	this.funcionario = funcionario;
}


public List<Funcionario> getFuncionarios() {
	return funcionarios;
}


public void setFuncionarios(List<Funcionario> funcionarios) {
	this.funcionarios = funcionarios;
}


public List<SelectItem> getTipoBuscas() {
	return tipoBuscas;
}


public void setTipoBuscas(List<SelectItem> tipoBuscas) {
	this.tipoBuscas = tipoBuscas;
}


public String getTipoBusca() {
	return tipoBusca;
}


public void setTipoBusca(String tipoBusca) {
	this.tipoBusca = tipoBusca;
}


public UsuarioSessao getUsuarioSessao() {
	return usuarioSessao;
}


public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
	this.usuarioSessao = usuarioSessao;
}
	
}
