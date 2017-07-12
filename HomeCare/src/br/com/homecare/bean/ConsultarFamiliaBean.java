package br.com.homecare.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.homecare.model.Familia;
import br.com.homecare.service.FamiliaService;

@ViewScoped
@ManagedBean(name="consultarFamiliaBean")
public class ConsultarFamiliaBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 6712522758268465657L;
	
	private Familia familiaFiltro;
	private Familia familia;
	private List<Familia> familias;	
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;	
	
	@PostConstruct
	public void init() {
		familiaFiltro = new Familia(); 
		consultar();
	}

	public void consultar() {
		try {
			familias = new FamiliaService().consultar(familiaFiltro);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public Familia getFamiliaFiltro() {
		return familiaFiltro;
	}

	public void setFamiliaFiltro(Familia familiaFiltro) {
		this.familiaFiltro = familiaFiltro;
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

	public UsuarioSessao getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}

	
	
	
}
