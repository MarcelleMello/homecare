package br.com.homecare.model.tipo;

public enum Perfil {
	
	ADMINISTRADOR(1, "Administrador"),
	AGENTE(2, "Agente");
	
	private Integer valor;
	private String descricao;

	private Perfil(Integer valor, String descricao) {
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

	public static Perfil buscaEnum(Integer valor) {
		for (Perfil e : Perfil.values()) {
			if (e.getValor() == valor) {
				return e;
			}
		}
		
		return null;
	}
}
