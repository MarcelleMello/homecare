package br.com.homecare.model.tipo;

public enum ExameEscarro {
	
	POSITIVO("P", "Positivo"),
	FEMININO("N", "Negativo"),
	INEXISTENTE("I", "Inexistente");
	  
	private String valor;
	private String descricao;
 
	private ExameEscarro(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static ExameEscarro buscaEnum(String valor) {
		for (ExameEscarro e : ExameEscarro.values()) {
			if (e.getValor().equals(valor)) {
				return e;
			}
		}
		
		return null;
	}
	
}
