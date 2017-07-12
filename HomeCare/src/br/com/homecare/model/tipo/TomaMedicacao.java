package br.com.homecare.model.tipo;

public enum TomaMedicacao {
	
	DIARIAMENTE("D", "Diária"),
	INTERMITENTE("I", "Intermitente");
	  
	private String valor;
	private String descricao;
 
	private TomaMedicacao(String valor, String descricao) {
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

	public static TomaMedicacao buscaEnum(String valor) {
		for (TomaMedicacao e : TomaMedicacao.values()) {
			if (e.getValor().equals(valor)) {
				return e;
			}
		}
		
		return null;
	}
	
}
