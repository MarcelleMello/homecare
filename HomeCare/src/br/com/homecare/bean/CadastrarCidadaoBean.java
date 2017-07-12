package br.com.homecare.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.homecare.model.Cidadao;
import br.com.homecare.model.Familia;
import br.com.homecare.model.tipo.Escolaridade;
import br.com.homecare.model.tipo.Nacionalidade;
import br.com.homecare.model.tipo.NaturezaEscola;
import br.com.homecare.model.tipo.Parto;
import br.com.homecare.model.tipo.RacaCor;
import br.com.homecare.model.tipo.Sexo;
import br.com.homecare.model.tipo.SimNao;
import br.com.homecare.model.tipo.SituacaoFamiliar;
import br.com.homecare.model.tipo.Uf;
import br.com.homecare.service.CidadaoService;
import br.com.homecare.service.FamiliaService;

@ViewScoped
@ManagedBean(name="cadastrarCidadaoBean")
public class CadastrarCidadaoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -7091453408031055492L;

	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;
	
	private Cidadao cidadao;
	private Familia familia;

	private List<SelectItem> sexo;
	private List<SelectItem> racaCor;
	private List<SelectItem> situacaoFamiliar;
	private List<SelectItem> frequentaEscola;
	private List<SelectItem> naturezaEscola;
	private List<SelectItem> possuiPlanoSaude;
	private List<SelectItem> fumante;
	private List<SelectItem> presenteDuranteVisita;
	private List<SelectItem> escolaridade;
	private List<SelectItem> nacionalidade;
	private List<SelectItem> uf;
	private List<SelectItem> bolsaFamilia;
	private List<SelectItem> cartaoFamiliaCarioca;
	private List<SelectItem> parto;
	private List<SelectItem> tratamentoTuberculose;
	
	private List<Familia> familias;
	
	private long idade = 0;
	private boolean idadeVerificada = false;
	private Integer id = 0;
	private Integer tabindex = 0;
	
	@PostConstruct
	public void init() {
		try {
						
			cidadao = new Cidadao();
			cidadao.setNacionalidade(Nacionalidade.BRASILEIRO);
			cidadao.setFamilia(new Familia());
			
			Map<String,String> params = FacesContext.getCurrentInstance()
					 .getExternalContext().getRequestParameterMap();
		 
			id = Integer.valueOf(params.get("idFamilia"));
			
			if(id != null) {
				familia = new FamiliaService().ObterPorId(id);
				cidadao.setFamilia(familia);
			}
			
			carregarCombos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean dnvObrigatorio() {
		return (idade <= 17 && cidadao.getCpf() == null || "".equals(cidadao.getCpf()));
	}
	
	public boolean cpfObrigatorio() {
		return (idade > 17);
	}
	
	public boolean recemNascido() {
		return ( idadeVerificada &&  idade < 1 );
	}
	
	public boolean adulto() {
		return ( idadeVerificada && idade > 17 );
	}
	
	public boolean menorDeIdade() {
		return ( idadeVerificada && idade > 0 && idade < 18 );
	}
	
	public void verificarIdade() {
		try {
			
			idadeVerificada = true;
			
			LocalDateTime hoje = LocalDateTime.now();
			
			//calcula diferença
			idade = cidadao.getDataNascimento().until(hoje, ChronoUnit.YEARS);
			tabindex = 0;
			
		} catch (Exception e) {
			displayErrorMessage(e.getMessage());
		}
	}
	
	
	public void cadastrar() {
		try {
			
			CidadaoService service = new CidadaoService();
			
			service.cadastrar(cidadao);
			
			displayInfoMessage("Cadastrado com sucesso.");
			
			cidadao = new Cidadao();
			cidadao.setFamilia(familia);
			
			idadeVerificada = false;
			idade = 0;
			id = familia.getId();
			tabindex = 0;

		} catch (Exception e) {
			displayErrorMessage(e.getMessage());
		}
	}
	
	public void carregarCombos() {
		
		sexo = new ArrayList<SelectItem>();
				
		for(Sexo e : Sexo.values()) {
			sexo.add(new SelectItem(e, e.getDescricao()));
		}
		
		racaCor = new ArrayList<SelectItem>();
		
		racaCor.add(new SelectItem("", " - Selecione uma opção - "));
		
		for(RacaCor e : RacaCor.values()) {
			racaCor.add(new SelectItem(e, e.getDescricao()));
		}
		
		situacaoFamiliar = new ArrayList<SelectItem>();
		
		situacaoFamiliar.add(new SelectItem("", " - Selecione uma opção - "));
		
		for(SituacaoFamiliar e : SituacaoFamiliar.values()) {
			situacaoFamiliar.add(new SelectItem(e, e.getDescricao()));
		}
		
		frequentaEscola = new ArrayList<SelectItem>();
				
		for(SimNao e : SimNao.values()) {
			frequentaEscola.add(new SelectItem(e, e.getDescricao()));
		}
		
		naturezaEscola = new ArrayList<SelectItem>();
		
		naturezaEscola.add(new SelectItem("", " - Selecione uma opção - "));
		
		for(NaturezaEscola e : NaturezaEscola.values()) {
			naturezaEscola.add(new SelectItem(e, e.getDescricao()));
		}
		
		possuiPlanoSaude = new ArrayList<SelectItem>();
				
		for(SimNao e : SimNao.values()) {
			possuiPlanoSaude.add(new SelectItem(e, e.getDescricao()));
		}
		
		fumante = new ArrayList<SelectItem>();
				
		for(SimNao e : SimNao.values()) {
			fumante.add(new SelectItem(e, e.getDescricao()));
		}
		
		presenteDuranteVisita = new ArrayList<SelectItem>();
				
		for(SimNao e : SimNao.values()) {
			presenteDuranteVisita.add(new SelectItem(e, e.getDescricao()));
		}
		
		escolaridade = new ArrayList<SelectItem>();
		
		escolaridade.add(new SelectItem("", " - Selecione uma opção - "));
		
		for(Escolaridade e : Escolaridade.values()) {
			escolaridade.add(new SelectItem(e, e.getDescricao()));
		}
		
		nacionalidade = new ArrayList<SelectItem>();
				
		for(Nacionalidade e : Nacionalidade.values()) {
			nacionalidade.add(new SelectItem(e, e.getDescricao()));
		}
		
		uf = new ArrayList<SelectItem>();
		
		uf.add(new SelectItem("", " - Selecione uma opção - "));
		
		for(Uf e : Uf.values()) {
			uf.add(new SelectItem(e, e.getDescricao()));
		}
		
		bolsaFamilia = new ArrayList<SelectItem>();
		
		for(SimNao e : SimNao.values()) {
			bolsaFamilia.add(new SelectItem(e, e.getDescricao()));
		}
		
		cartaoFamiliaCarioca = new ArrayList<SelectItem>();
				
		for(SimNao e : SimNao.values()) {
			cartaoFamiliaCarioca.add(new SelectItem(e, e.getDescricao()));
		}
		
		parto = new ArrayList<SelectItem>();
		
		parto.add(new SelectItem("", " - Selecione uma opção - "));
		
		for(Parto e : Parto.values()) {
			parto.add(new SelectItem(e, e.getDescricao()));
		}
		
		tratamentoTuberculose = new ArrayList<SelectItem>();
				
		for(SimNao e : SimNao.values()) {
			tratamentoTuberculose.add(new SelectItem(e, e.getDescricao()));
		}
	}

	public UsuarioSessao getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public List<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}

	public List<SelectItem> getSexo() {
		return sexo;
	}

	public void setSexo(List<SelectItem> sexo) {
		this.sexo = sexo;
	}

	public List<SelectItem> getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(List<SelectItem> racaCor) {
		this.racaCor = racaCor;
	}

	public List<SelectItem> getSituacaoFamiliar() {
		return situacaoFamiliar;
	}

	public void setSituacaoFamiliar(List<SelectItem> situacaoFamiliar) {
		this.situacaoFamiliar = situacaoFamiliar;
	}

	public List<SelectItem> getFrequentaEscola() {
		return frequentaEscola;
	}

	public void setFrequentaEscola(List<SelectItem> frequentaEscola) {
		this.frequentaEscola = frequentaEscola;
	}

	public List<SelectItem> getNaturezaEscola() {
		return naturezaEscola;
	}

	public void setNaturezaEscola(List<SelectItem> naturezaEscola) {
		this.naturezaEscola = naturezaEscola;
	}

	public List<SelectItem> getPossuiPlanoSaude() {
		return possuiPlanoSaude;
	}

	public void setPossuiPlanoSaude(List<SelectItem> possuiPlanoSaude) {
		this.possuiPlanoSaude = possuiPlanoSaude;
	}

	public List<SelectItem> getFumante() {
		return fumante;
	}

	public void setFumante(List<SelectItem> fumante) {
		this.fumante = fumante;
	}

	public List<SelectItem> getPresenteDuranteVisita() {
		return presenteDuranteVisita;
	}

	public void setPresenteDuranteVisita(List<SelectItem> presenteDuranteVisita) {
		this.presenteDuranteVisita = presenteDuranteVisita;
	}

	public List<SelectItem> getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(List<SelectItem> escolaridade) {
		this.escolaridade = escolaridade;
	}

	public List<SelectItem> getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(List<SelectItem> nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<SelectItem> getUf() {
		return uf;
	}

	public void setUf(List<SelectItem> uf) {
		this.uf = uf;
	}

	public List<SelectItem> getBolsaFamilia() {
		return bolsaFamilia;
	}

	public void setBolsaFamilia(List<SelectItem> bolsaFamilia) {
		this.bolsaFamilia = bolsaFamilia;
	}

	public List<SelectItem> getCartaoFamiliaCarioca() {
		return cartaoFamiliaCarioca;
	}

	public void setCartaoFamiliaCarioca(List<SelectItem> cartaoFamiliaCarioca) {
		this.cartaoFamiliaCarioca = cartaoFamiliaCarioca;
	}

	public List<SelectItem> getParto() {
		return parto;
	}

	public void setParto(List<SelectItem> parto) {
		this.parto = parto;
	}

	public boolean isIdadeVerificada() {
		return idadeVerificada;
	}

	public void setIdadeVerificada(boolean idadeVerificada) {
		this.idadeVerificada = idadeVerificada;
	}

	public long getIdade() {
		return idade;
	}

	public void setIdade(long idade) {
		this.idade = idade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getTabindex() {
		return tabindex;
	}

	public void setTabindex(Integer tabindex) {
		this.tabindex = tabindex;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<SelectItem> getTratamentoTuberculose() {
		return tratamentoTuberculose;
	}

	public void setTratamentoTuberculose(List<SelectItem> tratamentoTuberculose) {
		this.tratamentoTuberculose = tratamentoTuberculose;
	}
	
}
