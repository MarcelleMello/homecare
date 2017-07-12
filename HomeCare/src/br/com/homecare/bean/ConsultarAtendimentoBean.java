package br.com.homecare.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.homecare.model.Atendimento;
import br.com.homecare.model.Cidadao;
import br.com.homecare.model.Familia;
import br.com.homecare.model.Funcionario;
import br.com.homecare.service.AtendimentoService;

@ViewScoped
@ManagedBean(name="consultarAtendimentoBean")
public class ConsultarAtendimentoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 7112191256674659398L;
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;
	
	private List<Atendimento> atendimentos;
	private Atendimento atendimentoFiltro;
	
	@PostConstruct
	public void init() {
		carregar();
		consultar();
	}
	
	
	private void carregar() {
		atendimentoFiltro = new Atendimento();
		atendimentoFiltro.setCidadao(new Cidadao());
		atendimentoFiltro.getCidadao().setFamilia(new Familia());;
		atendimentoFiltro.setFuncionario(new Funcionario());
	}


	public void consultar() {
		AtendimentoService service = new AtendimentoService();
		try {
			atendimentos = service.consultar(atendimentoFiltro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void excluir(Atendimento a) {
		AtendimentoService service = new AtendimentoService();
		try {
			service.excluir(a);
			displayInfoMessage("Excluído com sucesso.");
			atendimentos = service.consultar(atendimentoFiltro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public UsuarioSessao getUsuarioSessao() {
		return usuarioSessao;
	}


	public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}


	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}


	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}


	public Atendimento getAtendimentoFiltro() {
		return atendimentoFiltro;
	}


	public void setAtendimentoFiltro(Atendimento atendimentoFiltro) {
		this.atendimentoFiltro = atendimentoFiltro;
	}
	
	
}
