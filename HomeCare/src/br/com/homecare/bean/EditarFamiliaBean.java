package br.com.homecare.bean;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.homecare.model.Familia;
import br.com.homecare.service.FamiliaService;

@ViewScoped
@ManagedBean(name="editarFamiliaBean")
public class EditarFamiliaBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = -4574038222467289698L;
	
	private Familia familia;
		
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;
	
	@PostConstruct
	public void init() {
		try {
			
			Map<String,String> params = FacesContext.getCurrentInstance()
					 .getExternalContext().getRequestParameterMap();
			 
			int id = Integer.valueOf(params.get("idFamilia"));
			
			familia = new FamiliaService().ObterPorId(id);
	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar() {
		try {
			
			FamiliaService service = new FamiliaService();
			
			service.atualizar(familia, usuarioSessao.getUsuario());
			
			displayInfoMessage("Atualizado com sucesso.");
						
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
