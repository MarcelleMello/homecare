package br.com.homecare.model.tipo;

public enum SimNao {
	
	SIM(1, "Sim"),
	NAO(0, "Não");
	  
	private Integer valor;
	private String descricao;
 
	private SimNao(Integer valor, String descricao) {
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

	public static SimNao buscaEnum(Integer valor) {
		for (SimNao e : SimNao.values()) {
			if (e.getValor()== valor) {
				return e;
			}
		}
		
		return null;
	}
	
}
