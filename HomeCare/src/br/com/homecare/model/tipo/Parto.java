package br.com.homecare.model.tipo;

public enum Parto {
	
	NORMAL(1, "Normal"),
	FORCEPS(2, "Fórceps"),
	CESAREA(3, "Cesárea");
	  
	private Integer valor;
	private String descricao;
 
	private Parto(Integer valor, String descricao) {
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

	public static Parto buscaEnum(Integer valor) {
		for (Parto e : Parto.values()) {
			if (e.getValor() == valor) {
				return e;
			}
		}
		
		return null;
	}
	
}
