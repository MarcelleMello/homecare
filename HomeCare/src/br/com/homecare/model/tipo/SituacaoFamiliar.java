package br.com.homecare.model.tipo;

public enum SituacaoFamiliar {
	
	VIVE_COM_COMPANHEIRA_E_FILHO(1, "Vive com Comp. e filho(s)"),
	CONVIVE_COM_COMPANHEIRA_COM_LACOS_CONJUGAIS_E_SEM_FILHO(2, "Vive com comp. com laços conj. e sem filho(s)"),
	CONVIVE_COM_COMPANHEIRA_COM_FILHO_E_OU_OUTRO_FAMILIAR(3, "Vive com comp. com filho(s) e/ou outro(s) familiar(es)"),
	CONVIVE_COM_FAMILIAR_SEM_COMPANHEIRA(4, "Vive com  familiar(es), sem comp."),
	CONVIVE_COM_OUTRAS_PESSOAS_SEM_LACOS_CONSAGUINEOS_E_OU_CONJUGAIS(5, "Vive com outras pessoas sem laços consang. e ou conj."),
	VIVE_SO(5, "Vive só");
	
	private Integer valor;
	private String descricao;
 
	private SituacaoFamiliar(Integer valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static SituacaoFamiliar buscaEnum(Integer valor) {
		for (SituacaoFamiliar e : SituacaoFamiliar.values()) {
			if (e.getValor() == valor) {
				return e;
			}
		}
		
		return null;
	}
	
}
