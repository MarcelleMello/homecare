package br.com.homecare.model.tipo;

public enum RacaCor {
	
	BRANCA(1, "Branca"),
	PRETA(2, "Preta"),
	AMARELA(3, "Amarela"),
	PARDA(4, "Parda"),
	INDIGENA(5, "Indígena");
	
	private Integer valor;
	private String descricao;
 
	private RacaCor(Integer valor, String descricao) {
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

	public static RacaCor buscaEnum(Integer valor) {
		for (RacaCor e : RacaCor.values()) {
			if (e.getValor()== valor) {
				return e;
			}
		}
		
		return null;
	}
	
}
