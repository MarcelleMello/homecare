package br.com.homecare.model.tipo;

public enum Escolaridade {
	
	NAOSABELERESCREVER(1, "Não sabe ler/escrever"),
	ALFABETIZADO(2, "Alfabetizado"),
	FUNDAMENTALINCOMPLETO(3, "Fundamental incompleto"),
	FUNDAMENTALCOMPLETO(4, "Fundamental completo"),
	MEDIOINCOMPLETO(5, "Médio incompleto"),
	MEDIOCOMPLETO(6, "Médio completo"),
	SUPERIORINCOMPLETO(7, "Superior incompleto"),
	SUPERIORCOMPLETO(8, "Superior completo"),
	ESPECRESIDENCIA(9, "Espec/Residência"),
	MESTRADO(10, "Mestrado"),
	DOUTORADO(11, "Doutorado");
	
	private Integer valor;
	private String descricao;
 
	private Escolaridade(Integer valor, String descricao) {
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

	public static Escolaridade buscaEnum(Integer valor) {
		for (Escolaridade e : Escolaridade.values()) {
			if (e.getValor()== valor) {
				return e;
			}
		}
		
		return null;
	}
	
}
