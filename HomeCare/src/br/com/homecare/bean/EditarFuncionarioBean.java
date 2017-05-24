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

import br.com.homecare.model.Comunidade;
import br.com.homecare.model.Funcionario;
import br.com.homecare.model.tipo.Perfil;
import br.com.homecare.model.tipo.SimNao;
import br.com.homecare.service.ComunidadeService;
import br.com.homecare.service.FuncionarioService;


@ViewScoped
@ManagedBean(name="editarFuncionarioBean")
public class EditarFuncionarioBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = -4574038222467289698L;
	
	private Funcionario funcionario;
	
	private List<SelectItem> simNao;
	private List<SelectItem> comunidades;
	private List<SelectItem> perfis;
	
	@ManagedProperty("#{usuarioSessao}")
	private UsuarioSessao usuarioSessao;
	
	@PostConstruct
	public void init() {
		try {
			
			Map<String,String> params = FacesContext.getCurrentInstance()
					 .getExternalContext().getRequestParameterMap();
			 
			int id = Integer.valueOf(params.get("idFuncionario"));
			
			funcionario = new FuncionarioService().ObterPorId(id);
			
			if(comunidades == null) {
				
				comunidades = new ArrayList<SelectItem>();
				comunidades.add(new SelectItem(null, "- Escolha uma opção -"));
				
				for (Comunidade c : new ComunidadeService().listarTodos()) {
					comunidades.add(new SelectItem(c, c.getNome()));
				}
			}
			if(simNao == null) {
				simNao = new ArrayList<SelectItem>();
				for (SimNao e : SimNao.values()) {
					simNao.add(new SelectItem(e, e.getDescricao()));
				}
			}
			if(perfis == null) {
				perfis = new ArrayList<SelectItem>();
				perfis.add(new SelectItem(null, "- Escolha uma opção -"));
				for (Perfil e : Perfil.values()) {
					perfis.add(new SelectItem(e, e.getDescricao()));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar() {
		try {
			
			FuncionarioService service = new FuncionarioService();
			
			service.atualizar(funcionario);
			
			displayInfoMessage("Cadastrado com sucesso.");
			
			funcionario = new Funcionario();
			
		} catch (Exception e) {
			displayErrorMessage(e.getMessage());
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<SelectItem> getSimNao() {
		return simNao;
	}

	public void setSimNao(List<SelectItem> simNao) {
		this.simNao = simNao;
	}

	public List<SelectItem> getComunidades() {
		return comunidades;
	}

	public void setComunidades(List<SelectItem> comunidades) {
		this.comunidades = comunidades;
	}

	public List<SelectItem> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<SelectItem> perfis) {
		this.perfis = perfis;
	}

	public UsuarioSessao getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(UsuarioSessao usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}
	
	
	
}
