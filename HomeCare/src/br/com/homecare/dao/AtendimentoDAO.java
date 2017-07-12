package br.com.homecare.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.homecare.model.Atendimento;
import br.com.homecare.model.Cidadao;
import br.com.homecare.model.Familia;
import br.com.homecare.model.Funcionario;
import br.com.homecare.model.tipo.ExameEscarro;
import br.com.homecare.model.tipo.SimNao;
import br.com.homecare.model.tipo.TomaMedicacao;
import br.com.homecare.util.Util;

public class AtendimentoDAO implements Serializable {

	private static final long serialVersionUID = 783304338675760376L;

	public int cadastrar(Atendimento a, Connection con) throws SQLException {
		
			String sql = 
					"INSERT INTO TBL_ATENDIMENTO " +
					"( " +
					"	OBSERVACOES, " +
					"	PESO, " +
					"	PRESSAO, " +
					"	GLICOSE, " +
					"	DATA_ATENDIMENTO, " +
					"	DATA_ULTIMA_CONSULTA, " +
					"	TRATAMENTO_SUPERVISIONADO, " +
					"	REACOES_INDESEJADAS, " +
					"	TOMA_MEDICACAO, " +
					"	EXAME_ESCARRO, " +
					"	ID_FUNCIONARIO, " +
					"	ID_CIDADAO " +
					")  VALUES (?,?,?,?, SYSDATE() ,?,?,?,?,?,?,?)";
			
		    Integer indice = 0;
		        
		    PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

		    stmt.setString(++indice, a.getObservacoes());
		    stmt.setString(++indice, a.getPeso());
		    stmt.setString(++indice, a.getPressao());
		    stmt.setString(++indice, a.getGlicose());
		    
		    if(a.getDataUltimaConsulta() != null) {
		    	stmt.setDate(++indice, java.sql.Date.valueOf( a.getDataUltimaConsulta() ));
		    } else {
			   stmt.setNull(++indice, java.sql.Types.DATE);
				}
		    
		    if(a.getTratamentoSupervisionado() != null) {
		    	stmt.setInt(++indice, a.getTratamentoSupervisionado().getValor());
		    } else {
				   stmt.setNull(++indice, java.sql.Types.INTEGER);
				}
		    
		    if(a.getReacoesIndesejadas() != null) {
		    	stmt.setInt(++indice, a.getReacoesIndesejadas().getValor());
		    } else {
				   stmt.setNull(++indice, java.sql.Types.INTEGER);
				}
		    
		    if(a.getTomaMedicacao() != null) {
		    	stmt.setString(++indice, a.getTomaMedicacao().getValor());
		    } else {
				   stmt.setNull(++indice, java.sql.Types.VARCHAR);
				}
		    
		    if(a.getExameEscarro() != null) {
		    	stmt.setString(++indice, a.getExameEscarro().getValor());
		    } else {
				   stmt.setNull(++indice, java.sql.Types.VARCHAR);
				}
		    
		    stmt.setInt(++indice, a.getFuncionario().getId());
		    stmt.setInt(++indice, a.getCidadao().getId());
		    
		    stmt.executeUpdate();
		    
		    ResultSet rs = stmt.getGeneratedKeys();  
	        rs.next();  
	        int id = rs.getInt(1);  
	        
		    stmt.close();
		    rs.close();
		    
		    return id;
			
		}
		
public List<Atendimento> consultar(Atendimento atendimentoFiltro, Connection con) throws SQLException {
		
	    List<Atendimento> lista = new ArrayList<Atendimento>();
	    
	    String sql = " SELECT A.ID_ATENDIMENTO, A.PESO, A.GLICOSE, A.PRESSAO, A.DATA_ATENDIMENTO,  " +
					 " FU.ID_FUNCIONARIO, FU.COD_FUNCIONARIO, C.ID_CIDADAO, C.NOME, FA.COD_FAMILIA, FA.ID_FAMILIA" +
					 " FROM TBL_ATENDIMENTO A" +
					 " JOIN TBL_CIDADAO C ON C.ID_CIDADAO = A.ID_CIDADAO " +
					 " JOIN TBL_FAMILIA FA ON FA.ID_FAMILIA = C.ID_FAMILIA " +
					 " JOIN TBL_FUNCIONARIO FU ON FU.ID_FUNCIONARIO = A.ID_FUNCIONARIO " +
					 " WHERE  1";
	    
	    if(atendimentoFiltro.getCidadao().getFamilia().getCodigo() != null && !"".equals(atendimentoFiltro.getCidadao().getFamilia().getCodigo())) {
	    	sql += " AND FA.COD_FAMILIA LIKE ? ";
	    }
	    if(atendimentoFiltro.getFuncionario().getCodigo() != null && !"".equals(atendimentoFiltro.getFuncionario().getCodigo())) {
	    	sql += " AND FU.COD_FUNCIONARIO LIKE ? ";
	    }
	    if(atendimentoFiltro.getCidadao().getNome() != null && !"".equals(atendimentoFiltro.getCidadao().getNome())) {
	    	sql += " AND C.NOME LIKE ? ";
	    }
	     
	    sql += " ORDER BY A.DATA_ATENDIMENTO DESC ";
	      	   		     
	    PreparedStatement stmt = con.prepareStatement(sql);

	    int indice = 0;
	    Atendimento a = null;
	    
	    if(atendimentoFiltro.getCidadao().getFamilia().getCodigo() != null && !"".equals(atendimentoFiltro.getCidadao().getFamilia().getCodigo())) {
	   	 	stmt.setString(++indice, "%"+atendimentoFiltro.getCidadao().getFamilia().getCodigo()+"%");
	    }
	    if(atendimentoFiltro.getFuncionario().getCodigo() != null && !"".equals(atendimentoFiltro.getFuncionario().getCodigo())) {
	    	stmt.setString(++indice, "%"+atendimentoFiltro.getFuncionario().getCodigo()+"%");
	    }
	    if(atendimentoFiltro.getCidadao().getNome() != null && !"".equals(atendimentoFiltro.getCidadao().getNome())) {
	    	stmt.setString(++indice, "%"+atendimentoFiltro.getCidadao().getNome()+"%");
	    }
	  
	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
	    	
	    	a = new Atendimento();
	    	
	    	a.setId(rs.getInt("ID_ATENDIMENTO"));
	    	
	    	a.setPeso(rs.getString("PESO"));
	    	a.setGlicose(rs.getString("GLICOSE"));
	    	a.setPressao(rs.getString("PRESSAO"));
	    	a.setDataAtendimento(Util.getDate(rs, "DATA_ATENDIMENTO"));
	    	
	    	a.setFuncionario(new Funcionario(rs.getInt("ID_FUNCIONARIO")));
	    	a.getFuncionario().setCodigo(rs.getString("COD_FUNCIONARIO"));
	    	
	    	a.setCidadao(new Cidadao(rs.getInt("ID_CIDADAO")));
	    	a.getCidadao().setNome(rs.getString("NOME"));
	    	
	    	a.getCidadao().setFamilia(new Familia(rs.getInt("ID_FAMILIA")));
	    	a.getCidadao().getFamilia().setCodigo(rs.getString("COD_FAMILIA"));
		    
		    lista.add(a);
	    }
	    
	    rs.close();
	    stmt.close();        
		
	    return lista;

}

public Atendimento ObterPorId(int id, Connection con) throws SQLException {
	    
	    String sql = " SELECT A.ID_ATENDIMENTO, A.DATA_ULTIMA_CONSULTA AS DATA_ULTIMA_CONSULTA, A.OBSERVACOES, A.PESO, A.GLICOSE, A.PRESSAO, A.DATA_ATENDIMENTO,  " +
	    			 " A.TRATAMENTO_SUPERVISIONADO, A.REACOES_INDESEJADAS, A.TOMA_MEDICACAO, A.EXAME_ESCARRO, " +
					 " FU.ID_FUNCIONARIO, FU.COD_FUNCIONARIO, C.ID_CIDADAO, C.NOME, FA.COD_FAMILIA, FA.ID_FAMILIA" +
					 " FROM TBL_ATENDIMENTO A " +
					 " JOIN TBL_CIDADAO C ON C.ID_CIDADAO = A.ID_CIDADAO " +
					 " JOIN TBL_FAMILIA FA ON FA.ID_FAMILIA = C.ID_FAMILIA " +
					 " JOIN TBL_FUNCIONARIO FU ON FU.ID_FUNCIONARIO = A.ID_FUNCIONARIO " +
					 " WHERE A.ID_ATENDIMENTO = ? ";
 		     
	    PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.setInt(1, id);
	    
	    Atendimento a = null;

	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
	    	
	    	a = new Atendimento();
	    	
	    	a.setId(rs.getInt("ID_ATENDIMENTO"));
	    	a.setPeso(rs.getString("PESO"));
	    	a.setObservacoes(rs.getString("OBSERVACOES"));
	    	a.setGlicose(rs.getString("GLICOSE"));
	    	a.setPressao(rs.getString("PRESSAO"));
	    	a.setPeso(rs.getString("PESO"));
	    	a.setDataAtendimento(Util.getDate(rs, "DATA_ATENDIMENTO"));
	    	
	    	a.setDataUltimaConsulta(Util.getDate(rs, "DATA_ULTIMA_CONSULTA"));
	    	a.setTomaMedicacao(TomaMedicacao.buscaEnum(rs.getString("TOMA_MEDICACAO")));
	    	a.setTratamentoSupervisionado(SimNao.buscaEnum(rs.getInt("TRATAMENTO_SUPERVISIONADO")));
	    	a.setReacoesIndesejadas(SimNao.buscaEnum(rs.getInt("REACOES_INDESEJADAS")));
	    	a.setExameEscarro(ExameEscarro.buscaEnum(rs.getString("EXAME_ESCARRO")));
	    	
	    	a.setFuncionario(new Funcionario(rs.getInt("ID_FUNCIONARIO")));
	    	a.getFuncionario().setCodigo(rs.getString("COD_FUNCIONARIO"));
	    	
	    	a.setCidadao(new Cidadao(rs.getInt("ID_CIDADAO")));
	    	a.getCidadao().setNome(rs.getString("NOME"));
	    	
	    	a.getCidadao().setFamilia(new Familia(rs.getInt("ID_FAMILIA")));
	    	a.getCidadao().getFamilia().setCodigo(rs.getString("COD_FAMILIA"));
		    
	    }
	    
	    rs.close();
	    stmt.close();        
		
	    return a;
}

public void atualizar(Atendimento a, Connection con) throws SQLException {
	String sql = 
			"  UPDATE TBL_ATENDIMENTO SET " +
			"	OBSERVACOES = ?, " +
			"	PESO = ?, " +
			"	PRESSAO = ?, " +
			"	GLICOSE = ?, " +
			"	DATA_ULTIMA_CONSULTA = ?, " +
			"	TRATAMENTO_SUPERVISIONADO = ?, " +
			"	REACOES_INDESEJADAS = ?, " +
			"	TOMA_MEDICACAO = ?, " +
			"	EXAME_ESCARRO = ?, " +
			"	ID_FUNCIONARIO = ? " +
			"	WHERE ID_ATENDIMENTO = ? ";
		
    Integer indice = 0;
        
    PreparedStatement stmt = con.prepareStatement(sql);

    stmt.setString(++indice, a.getObservacoes());
    stmt.setString(++indice, a.getPeso());
    stmt.setString(++indice, a.getPressao());
    stmt.setString(++indice, a.getGlicose());
    
    if(a.getDataUltimaConsulta() != null) {
    	stmt.setDate(++indice, java.sql.Date.valueOf( a.getDataUltimaConsulta() ));
    } else {
	   stmt.setNull(++indice, java.sql.Types.DATE);
		}
    
    if(a.getTratamentoSupervisionado() != null) {
    	stmt.setInt(++indice, a.getTratamentoSupervisionado().getValor());
    } else {
		   stmt.setNull(++indice, java.sql.Types.INTEGER);
		}
    
    if(a.getReacoesIndesejadas() != null) {
    	stmt.setInt(++indice, a.getReacoesIndesejadas().getValor());
    } else {
		   stmt.setNull(++indice, java.sql.Types.INTEGER);
		}
    
    if(a.getTomaMedicacao() != null) {
    	stmt.setString(++indice, a.getTomaMedicacao().getValor());
    } else {
		   stmt.setNull(++indice, java.sql.Types.VARCHAR);
		}
    
    if(a.getExameEscarro() != null) {
    	stmt.setString(++indice, a.getExameEscarro().getValor());
    } else {
		   stmt.setNull(++indice, java.sql.Types.VARCHAR);
		}
    
    stmt.setInt(++indice, a.getFuncionario().getId());
    stmt.setInt(++indice, a.getId());
    
    stmt.executeUpdate();
    stmt.close();

}

public void excluir(int id, Connection con) throws SQLException {
	String sql = "DELETE FROM TBL_ATENDIMENTO WHERE ID_ATENDIMENTO = ?";
		        
    PreparedStatement stmt = con.prepareStatement(sql);

    stmt.setInt(1, id);
    
    stmt.executeUpdate();
    stmt.close();
	
}

}
