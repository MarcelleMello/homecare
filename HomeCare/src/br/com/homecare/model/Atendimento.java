package br.com.homecare.model;

import java.io.Serializable;
import java.time.LocalDate;
import br.com.homecare.model.tipo.ExameEscarro;
import br.com.homecare.model.tipo.SimNao;
import br.com.homecare.model.tipo.TomaMedicacao;

public class Atendimento implements Serializable{

	private static final long serialVersionUID = -7627670520018405865L;
	
	private Integer id;
	
	//funcionario que realizou o atendimento
	private Funcionario funcionario;
	
	//cidadao que recebera o atendimento
	private Cidadao cidadao;
	
	private LocalDate dataAtendimento; //data e hora do atendimento
	private String pressao;
	private String glicose;
	private String peso;	
	private LocalDate dataUltimaConsulta; //data ultima consulta com o medico 
	private TomaMedicacao tomaMedicacao;
	private SimNao tratamentoSupervisionado;
	private SimNao reacoesIndesejadas;
	private ExameEscarro exameEscarro;
	private String observacoes;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}

	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getPressao() {
		return pressao;
	}

	public void setPressao(String pressao) {
		this.pressao = pressao;
	}

	public String getGlicose() {
		return glicose;
	}

	public void setGlicose(String glicose) {
		this.glicose = glicose;
	}
	

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public LocalDate getDataUltimaConsulta() {
		return dataUltimaConsulta;
	}

	public void setDataUltimaConsulta(LocalDate dataUltimaConsulta) {
		this.dataUltimaConsulta = dataUltimaConsulta;
	}

	public TomaMedicacao getTomaMedicacao() {
		return tomaMedicacao;
	}

	public void setTomaMedicacao(TomaMedicacao tomaMedicacao) {
		this.tomaMedicacao = tomaMedicacao;
	}

	public SimNao getTratamentoSupervisionado() {
		return tratamentoSupervisionado;
	}

	public void setTratamentoSupervisionado(SimNao tratamentoSupervisionado) {
		this.tratamentoSupervisionado = tratamentoSupervisionado;
	}

	public SimNao getReacoesIndesejadas() {
		return reacoesIndesejadas;
	}

	public void setReacoesIndesejadas(SimNao reacoesIndesejadas) {
		this.reacoesIndesejadas = reacoesIndesejadas;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public ExameEscarro getExameEscarro() {
		return exameEscarro;
	}

	public void setExameEscarro(ExameEscarro exameEscarro) {
		this.exameEscarro = exameEscarro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
