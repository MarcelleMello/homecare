package br.com.homecare.model.tipo;

public enum Nacionalidade {
	
	BRASILEIRO("B", "Brasileiro"),
	ESTRANGEIRO("E", "Estrangeiro");
	  
	private String valor;
	private String descricao;
 
	private Nacionalidade(String valor, String descricao) {
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

	public static Nacionalidade buscaEnum(String valor) {
		for (Nacionalidade e : Nacionalidade.values()) {
			if (e.getValor().equals(valor)) {
				return e;
			}
		}
		
		return null;
	}
	
}
