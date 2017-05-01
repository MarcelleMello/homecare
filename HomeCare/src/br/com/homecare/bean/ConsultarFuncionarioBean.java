package br.com.homecare.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.homecare.model.Funcionario;
import br.com.homecare.service.FuncionarioService;




@ViewScoped
@ManagedBean(name="consultarFuncionarioBean")
public class ConsultarFuncionarioBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 6712522758268465657L;
	
	private String item;
	private Funcionario funcionarioFiltro;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;	
	private List<SelectItem> itens;
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;	
	
	@PostConstruct
	public void init() {
		itens = new ArrayList<SelectItem>();	
		
		funcionarioFiltro = new Funcionario(); 
		
		consultar();
	}
		
	
public void consultar() {
	try {
			
		funcionarios = new FuncionarioService().consultar(funcionarioFiltro, usuarioSessao.getUsuario());	
		
		}
		  catch (Exception ex) {
			ex.printStackTrace();
		}		
		
}

public void carregarItens() {
	try {
		
		itens.add(new SelectItem("", "SELECIONE"));
		itens.add(new SelectItem("NOME"));
		itens.add(new SelectItem("TELEFONE"));
		itens.add(new SelectItem("MATRÍCULA"));
		itens.add(new SelectItem("MICROAREA"));
		itens.add(new SelectItem("PERFIL"));
		itens.add(new SelectItem("ID_COMUNIDADE"));
		itens.add(new SelectItem("ATIVO"));
		itens.add(new SelectItem("DATA DE CADASTRO"));
	
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void selecionar(Funcionario funcionario) {
	
	
}



public Funcionario getFuncionario() {
	return funcionario;
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


public List<SelectItem> getItens() {
	return itens;
}


public void setItens(List<SelectItem> itens) {
	this.itens = itens;
}


public String getItem() {
	return item;
}


public void setItem(String item) {
	this.item = item;
}


public UsuarioSessao getUsuarioSessao() {
	return usuarioSessao;
}


public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
	this.usuarioSessao = usuarioSessao;
}
	
}
