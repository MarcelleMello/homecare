package br.com.homecare.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.homecare.model.Familia;
import br.com.homecare.service.FamiliaService;

@ViewScoped
@ManagedBean(name="cadastrarFamiliaBean")
public class CadastrarFamiliaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -7091453408031055492L;

	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;
	
	private Familia familia;
	
	@PostConstruct
	public void init() {
		try {
			
			if(familia ==null) {
				familia = new Familia();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar() {
		try {
			
			FamiliaService service = new FamiliaService();
			
			service.cadastrar(familia, usuarioSessao.getUsuario());
			
			displayInfoMessage("Cadastrado com sucesso.");
			
			familia = new Familia();
			
		} catch (Exception e) {
			displayErrorMessage(e.getMessage());
		}
	}
	
	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public UsuarioSessao getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}
}
