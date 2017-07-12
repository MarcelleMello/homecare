package br.com.homecare.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.homecare.model.tipo.Escolaridade;
import br.com.homecare.model.tipo.Nacionalidade;
import br.com.homecare.model.tipo.NaturezaEscola;
import br.com.homecare.model.tipo.Parto;
import br.com.homecare.model.tipo.RacaCor;
import br.com.homecare.model.tipo.Sexo;
import br.com.homecare.model.tipo.SimNao;
import br.com.homecare.model.tipo.SituacaoFamiliar;
import br.com.homecare.model.tipo.Uf;

public class Cidadao implements Serializable{
	
	private static final long serialVersionUID = -9113300822727447987L;
	
	private Integer id;
	private String nome;
	private LocalDate dataNascimento;
	private Sexo sexo;
	private RacaCor racaCor;
	private SituacaoFamiliar situacaoFamiliar;
	private SimNao frequentaEscola;
	private NaturezaEscola naturezaEscola;
	private SimNao possuiPlanoSaude;
	private SimNao fumante;
	private SimNao presenteDuranteVisita;
	private Escolaridade escolaridade;
	private String ocupacao;
	private String cpf; //obrigatório para maiores de 16 anos (unico)
	private String dnv; //menores de até 16 anos sem cpf obrigatorio o dnv (unico)
	
	private Nacionalidade nacionalidade;
	private String nomeMae;
	private String nomePai;
	private String municipio; //local nascimento
	private Uf uf;
	
	private String paisOrigem;
	private LocalDate dataEntradaBrasil;
	private String cns; //15 caracteres (O Cartão Nacional de Saúde (CNS) é um documento numerado que possibilita a identificação de qualquer pessoa no Brasil, ao utilizar os serviços de saúde. ... Além disso, todas as pessoas têm direito ao uso dos serviços do SUS, independentemente de vínculo a plano privado de saúde, ou de possuírem o cartão.8 de nov de 2012)
	
	private SimNao bolsaFamilia;
	private SimNao cartaoFamiliaCarioca;
	private String nis; //11 caracteres (NIS significa Número de Identificação Social. É um número de cadastro atribuído pela Caixa Econômica Federal às pessoas que serão beneficiadas por algum projeto social e ainda não possuem cadastro no PIS (Programa de Integração Social).
	
	private String peso;
	private String comprimento;
	private String cefalico; 
	
	private LocalDate dataTestePezinho;
	private String observacoes; //opcional
	
	private LocalDate dataCadastro;
	private Parto parto;
	
	private Familia familia;
	
	
	private SimNao tratamentoTuberculose;
	private LocalDate dataInicioTratamentoTuberculose; 
	private LocalDate dataFinalTratamentoTuberculose; 
	
	public Cidadao() {
		// TODO Auto-generated constructor stub
	}

	public Cidadao(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome != null) {
			this.nome = nome.toUpperCase();
		} else {
			this.nome = nome;
		}
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public RacaCor getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(RacaCor racaCor) {
		this.racaCor = racaCor;
	}

	public SituacaoFamiliar getSituacaoFamiliar() {
		return situacaoFamiliar;
	}

	public void setSituacaoFamiliar(SituacaoFamiliar situacaoFamiliar) {
		this.situacaoFamiliar = situacaoFamiliar;
	}

	public SimNao getFrequentaEscola() {
		return frequentaEscola;
	}

	public void setFrequentaEscola(SimNao frequentaEscola) {
		this.frequentaEscola = frequentaEscola;
	}

	public NaturezaEscola getNaturezaEscola() {
		return naturezaEscola;
	}

	public void setNaturezaEscola(NaturezaEscola naturezaEscola) {
		this.naturezaEscola = naturezaEscola;
	}

	public SimNao getPossuiPlanoSaude() {
		return possuiPlanoSaude;
	}

	public void setPossuiPlanoSaude(SimNao possuiPlanoSaude) {
		this.possuiPlanoSaude = possuiPlanoSaude;
	}

	public SimNao getFumante() {
		return fumante;
	}

	public void setFumante(SimNao fumante) {
		this.fumante = fumante;
	}

	public SimNao getPresenteDuranteVisita() {
		return presenteDuranteVisita;
	}

	public void setPresenteDuranteVisita(SimNao presenteDuranteVisita) {
		this.presenteDuranteVisita = presenteDuranteVisita;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDnv() {
		return dnv;
	}

	public void setDnv(String dnv) {
		this.dnv = dnv;
	}

	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		if(nomeMae != null) {
			this.nomeMae = nomeMae.toUpperCase();
		} else {
			this.nomeMae = nomeMae;
		}
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		if(nomePai != null) {
			this.nomePai = nomePai.toUpperCase();
		} else {
			this.nomePai = nomePai;
		}
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public LocalDate getDataEntradaBrasil() {
		return dataEntradaBrasil;
	}

	public void setDataEntradaBrasil(LocalDate dataEntradaBrasil) {
		this.dataEntradaBrasil = dataEntradaBrasil;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public SimNao getBolsaFamilia() {
		return bolsaFamilia;
	}

	public void setBolsaFamilia(SimNao bolsaFamilia) {
		this.bolsaFamilia = bolsaFamilia;
	}

	public SimNao getCartaoFamiliaCarioca() {
		return cartaoFamiliaCarioca;
	}

	public void setCartaoFamiliaCarioca(SimNao cartaoFamiliaCarioca) {
		this.cartaoFamiliaCarioca = cartaoFamiliaCarioca;
	}

	public String getNis() {
		return nis;
	}

	public void setNis(String nis) {
		this.nis = nis;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getComprimento() {
		return comprimento;
	}

	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}

	public String getCefalico() {
		return cefalico;
	}

	public void setCefalico(String cefalico) {
		this.cefalico = cefalico;
	}

	public LocalDate getDataTestePezinho() {
		return dataTestePezinho;
	}

	public void setDataTestePezinho(LocalDate dataTestePezinho) {
		this.dataTestePezinho = dataTestePezinho;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Parto getParto() {
		return parto;
	}

	public void setParto(Parto parto) {
		this.parto = parto;
	}
	
	public SimNao getTratamentoTuberculose() {
		return tratamentoTuberculose;
	}

	public void setTratamentoTuberculose(SimNao tratamentoTuberculose) {
		this.tratamentoTuberculose = tratamentoTuberculose;
	}

	public LocalDate getDataInicioTratamentoTuberculose() {
		return dataInicioTratamentoTuberculose;
	}

	public void setDataInicioTratamentoTuberculose(LocalDate dataInicioTratamentoTuberculose) {
		this.dataInicioTratamentoTuberculose = dataInicioTratamentoTuberculose;
	}

	public LocalDate getDataFinalTratamentoTuberculose() {
		return dataFinalTratamentoTuberculose;
	}

	public void setDataFinalTratamentoTuberculose(LocalDate dataFinalTratamentoTuberculose) {
		this.dataFinalTratamentoTuberculose = dataFinalTratamentoTuberculose;
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
		Cidadao other = (Cidadao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
