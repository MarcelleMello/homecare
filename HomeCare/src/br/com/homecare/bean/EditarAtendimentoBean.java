package br.com.homecare.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.homecare.model.Atendimento;
import br.com.homecare.model.Cidadao;
import br.com.homecare.model.tipo.ExameEscarro;
import br.com.homecare.model.tipo.SimNao;
import br.com.homecare.model.tipo.TomaMedicacao;
import br.com.homecare.service.AtendimentoService;

@ViewScoped
@ManagedBean(name="editarAtendimentoBean")
public class EditarAtendimentoBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 7112191256674659398L;
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;
	
	private Cidadao cidadao;
	private Atendimento atendimento;
	private List<SelectItem> tomaMedicacao;
	private List<SelectItem> tratamentoSupervisionado;
	private List<SelectItem> reacoesIndesejadas;
	private List<SelectItem> exameEscarro;
	private List<SelectItem> tratamentoTuberculose;
	
	@PostConstruct
	public void init() {
		
		carregar();
		
		Map<String,String> params = FacesContext.getCurrentInstance()
				 .getExternalContext().getRequestParameterMap();
	 
		Integer id = Integer.valueOf(params.get("idAtendimento"));
		
		if(id != null) {
			obterPorId(id);
		}	
	}
	
	
	private void obterPorId(Integer id) {
		try {
			atendimento = new AtendimentoService().ObterPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void carregar() {
		
		tratamentoSupervisionado = new ArrayList<SelectItem>();
		
		for(SimNao e: SimNao.values()) {
			tratamentoSupervisionado.add(new SelectItem(e, e.getDescricao()));
		}
		
		reacoesIndesejadas = new ArrayList<SelectItem>();
		
		for(SimNao e: SimNao.values()) {
			reacoesIndesejadas.add(new SelectItem(e, e.getDescricao()));
		}
		
		tomaMedicacao = new ArrayList<SelectItem>();
		
		tomaMedicacao.add(new SelectItem(null, " - Selecione uma opção - "));
		
		for(TomaMedicacao e: TomaMedicacao.values()) {
			tomaMedicacao.add(new SelectItem(e, e.getDescricao()));
		}
		
		exameEscarro = new ArrayList<SelectItem>();
		
		exameEscarro.add(new SelectItem(null, " - Selecione uma opção - "));
		
		for(ExameEscarro e: ExameEscarro.values()) {
			exameEscarro.add(new SelectItem(e, e.getDescricao()));
		}
		
		tratamentoTuberculose = new ArrayList<SelectItem>();
		
		for(SimNao e: SimNao.values()) {
			tratamentoTuberculose.add(new SelectItem(e, e.getDescricao()));
		}
	}
	
	
	public void atualizar() {
		try {
		//atualizando o funcionario que esta atualizando o formulário
			atendimento.setFuncionario(usuarioSessao.getUsuario());
			
			AtendimentoService service = new AtendimentoService();

			service.atualizar(atendimento);
			
			obterPorId(atendimento.getId());
			
			displayInfoMessage("Cadastrado com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Cidadao getCidadao() {
		return cidadao;
	}


	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}


	public Atendimento getAtendimento() {
		return atendimento;
	}


	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}


	public UsuarioSessao getUsuarioSessao() {
		return usuarioSessao;
	}


	public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}


	public List<SelectItem> getTomaMedicacao() {
		return tomaMedicacao;
	}


	public void setTomaMedicacao(List<SelectItem> tomaMedicacao) {
		this.tomaMedicacao = tomaMedicacao;
	}


	public List<SelectItem> getTratamentoSupervisionado() {
		return tratamentoSupervisionado;
	}


	public void setTratamentoSupervisionado(List<SelectItem> tratamentoSupervisionado) {
		this.tratamentoSupervisionado = tratamentoSupervisionado;
	}


	public List<SelectItem> getReacoesIndesejadas() {
		return reacoesIndesejadas;
	}


	public void setReacoesIndesejadas(List<SelectItem> reacoesIndesejadas) {
		this.reacoesIndesejadas = reacoesIndesejadas;
	}


	public List<SelectItem> getExameEscarro() {
		return exameEscarro;
	}


	public void setExameEscarro(List<SelectItem> exameEscarro) {
		this.exameEscarro = exameEscarro;
	}


	public List<SelectItem> getTratamentoTuberculose() {
		return tratamentoTuberculose;
	}


	public void setTratamentoTuberculose(List<SelectItem> tratamentoTuberculose) {
		this.tratamentoTuberculose = tratamentoTuberculose;
	}



	
	
	
	
}






