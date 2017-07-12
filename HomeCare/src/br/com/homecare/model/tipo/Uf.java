package br.com.homecare.model.tipo;

public enum Uf {
	
	AC("AC","Acre"),  
    AL("AL","Alagoas"),  
    AM("AM","Amazonas"),  
    AP("AP","Amapá"),  
    BA("BA","Bahia"),  
    CE("CE","Ceará"),  
    DF("DF","Distrito Federal"),  
    ES("ES","Espirito Santo"),  
    GO("GO","Goias"),  
    MA("MA","Maranhão"),  
    MG("MG","Minas Gerais"),  
    MS("MS","Mato Grosso Sul"),  
    MT("MT","Mato Grosso"),  
    PA("PA","Pará"),  
    PB("PB","Paraiba"),  
    PE("PE","Pernanbuco"),  
    PI("PI","Piaui"),  
    PR("PR","Parana"),  
    RJ("RJ","Rio de Janeiro"),  
    RN("RN","Rio Grande do Norte"),  
    RO("RO","Rondônia"),  
    RR("RR","Roraima"),  
    RS("RS","Rio Grande do Sul"),  
    SC("SC","Santa Catarina"),  
    SE("SE","Sergipe"),  
    SP("SP","São Paulo"),  
    TO("TO","Tocantins");  
	  
	private String valor;
	private String descricao;
 
	private Uf(String valor, String descricao) {
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

	public static Uf buscaEnum(String valor) {
		for (Uf e : Uf.values()) {
			if (e.getValor().equals(valor)) {
				return e;
			}
		}
		
		return null;
	}
	
}
