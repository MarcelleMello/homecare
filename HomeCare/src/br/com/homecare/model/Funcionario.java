package br.com.homecare.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.homecare.model.tipo.Perfil;
import br.com.homecare.model.tipo.SimNao;

public class Funcionario implements Serializable{

	private static final long serialVersionUID = 4526393390225489822L;
	
	private Integer id;
	private String codigo;
	private String nome;
	private String telefone;
	private String senha;
	private LocalDate dataCadastro;
	private String microArea;
	private SimNao ativo;
	private Comunidade comunidade;
	private Perfil perfil;
	
	public Funcionario() {
		
	}
	
	public Funcionario(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getMicroArea() {
		return microArea;
	}
	public void setMicroArea(String microArea) {
		this.microArea = microArea;
	}
	public SimNao getAtivo() {
		return ativo;
	}
	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}
	public Comunidade getComunidade() {
		return comunidade;
	}
	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
