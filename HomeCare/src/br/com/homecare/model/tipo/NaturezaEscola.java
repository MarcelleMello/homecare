package br.com.homecare.model.tipo;

public enum NaturezaEscola {
	
	PARTICULAR(1, "Particular"),
	MUNICIPAL(2, "Municipal"),
	ESTADUAL(3, "Estadual"),
	FEDERAL(4, "Federal");
	  
	private Integer valor;
	private String descricao;
 
	private NaturezaEscola(Integer valor, String descricao) {
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

	public static NaturezaEscola buscaEnum(Integer valor) {
		for (NaturezaEscola e : NaturezaEscola.values()) {
			if (e.getValor() == valor) {
				return e;
			}
		}
		
		return null;
	}
	
}
