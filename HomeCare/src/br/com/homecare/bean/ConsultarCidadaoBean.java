package br.com.homecare.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.homecare.model.Cidadao;
import br.com.homecare.model.Familia;
import br.com.homecare.service.CidadaoService;

@ViewScoped
@ManagedBean(name="consultarCidadaoBean")
public class ConsultarCidadaoBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 6712522758268465657L;
	
	private Cidadao cidadaoFiltro;
	private List<Cidadao> cidadaos;	
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;	
	
	@PostConstruct
	public void init() {
		cidadaoFiltro = new Cidadao(); 
		cidadaoFiltro.setFamilia(new Familia());
		consultar();
	}

	public void consultar() {
		try {
			cidadaos = new CidadaoService().consultar(cidadaoFiltro);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public Cidadao getCidadaoFiltro() {
		return cidadaoFiltro;
	}

	public void setCidadaoFiltro(Cidadao cidadaoFiltro) {
		this.cidadaoFiltro = cidadaoFiltro;
	}

	public List<Cidadao> getCidadaos() {
		return cidadaos;
	}

	public void setCidadaos(List<Cidadao> cidadaos) {
		this.cidadaos = cidadaos;
	}

	public UsuarioSessao getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}

}
