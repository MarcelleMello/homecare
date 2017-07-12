package br.com.homecare.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.homecare.model.Cidadao;
import br.com.homecare.model.Familia;
import br.com.homecare.model.tipo.Escolaridade;
import br.com.homecare.model.tipo.Nacionalidade;
import br.com.homecare.model.tipo.NaturezaEscola;
import br.com.homecare.model.tipo.Parto;
import br.com.homecare.model.tipo.RacaCor;
import br.com.homecare.model.tipo.Sexo;
import br.com.homecare.model.tipo.SimNao;
import br.com.homecare.model.tipo.SituacaoFamiliar;
import br.com.homecare.model.tipo.Uf;
import br.com.homecare.util.Util;

public class CidadaoDAO implements Serializable {

	private static final long serialVersionUID = 5353231339405622721L;


	public int cadastrar(Cidadao c, Connection con) throws SQLException {

		String sql = " INSERT INTO TBL_CIDADAO " +
									"( "+
									
									" NOME, " +
									" DATA_NASCIMENTO, " +
									" SEXO, " +
									" RACA_COR,"+
									
									" SITUACAO_FAMILIAR, "+
									" FREQUENTA_ESCOLA, "+
									" NATUREZA_ESCOLA, "+
									" POSSUI_PLANO_SAUDE, "+
									
									" FUMANTE, "+
									" PRESENTE_DURANTE_VISITA, "+
									" ESCOLARIDADE, "+
									" OCUPACAO, "+
									
									
									" CPF, "+		
									" DNV, "+				 
									" NACIONALIDADE, "+
									" NOME_MAE, "+
									" NOME_PAI, "+
									" MUNICIPIO, "+
									
									
									" UF, "+
									" PAIS_ORIGEM, "+
									" DATA_ENTRADA_BRASIL, "+
									" CNS, "+
									
									
									" BOLSA_FAMILIA, "+
									" CARTAO_FAMILIA_CARIOCA, "+
									" NIS, "+
									" PESO, "+
									" COMPRIMENTO, "+
									" CEFALICO, "+ 
									
									
									" DATA_TESTE_PEZINHO, "+
									" OBSERVACOES, "+
									" PARTO, " +
									" DATA_CADASTRO, "+
									" ID_FAMILIA "+
									
									") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, SYSDATE(),?)";
		
	    Integer indice = 0;
	        
	    PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

	    stmt.setString(++indice, c.getNome());
	    stmt.setDate(++indice, java.sql.Date.valueOf( c.getDataNascimento() ));
	    stmt.setString(++indice, c.getSexo().getValor());
	    stmt.setInt(++indice, c.getRacaCor().getValor());
	    
	    if(c.getSituacaoFamiliar() != null) {
	    	stmt.setInt(++indice, c.getSituacaoFamiliar().getValor());
	    } else {
		   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getFrequentaEscola() != null) {
	    	stmt.setInt(++indice, c.getFrequentaEscola().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getNaturezaEscola() != null) {
	    	stmt.setInt(++indice, c.getNaturezaEscola().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getPossuiPlanoSaude() != null) {
	    	stmt.setInt(++indice, c.getPossuiPlanoSaude().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	
	    if(c.getFumante() != null) {
	    	stmt.setInt(++indice, c.getFumante().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getPresenteDuranteVisita() != null) {
	    	stmt.setInt(++indice, c.getPresenteDuranteVisita().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getEscolaridade() != null) {
	    	stmt.setInt(++indice, c.getEscolaridade().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    stmt.setString(++indice, c.getOcupacao());
	    
	    if(c.getCpf() != null && !"".equals(c.getCpf())) {
	    	stmt.setString(++indice, c.getCpf());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	    
	    if(c.getDnv() != null && !"".equals(c.getDnv())) {
	    	stmt.setString(++indice, c.getDnv());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	    
	    if(c.getNacionalidade() != null && !"".equals(c.getNacionalidade())){
	    	stmt.setString(++indice, c.getNacionalidade().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	    
	    stmt.setString(++indice, c.getNomeMae());
	    stmt.setString(++indice, c.getNomePai());
	    stmt.setString(++indice, c.getMunicipio());
	    
	    if(c.getUf() != null) {
	    	stmt.setString(++indice, c.getUf().getValor());
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.VARCHAR);
	    	}

	    stmt.setString(++indice, c.getPaisOrigem());
	    
	    if(c.getDataEntradaBrasil() != null) {
	    	stmt.setDate(++indice, java.sql.Date.valueOf( c.getDataEntradaBrasil() ));
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.DATE);
	    	}
	    
	    if(c.getCns() != null && !"".equals(c.getCns())) {
	    	stmt.setString(++indice, c.getCns());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	   
	    if(c.getBolsaFamilia() != null) {
	    	stmt.setInt(++indice, c.getBolsaFamilia().getValor());
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.INTEGER);
	    	}
	    
	    if(c.getCartaoFamiliaCarioca() != null) {
	    	stmt.setInt(++indice, c.getCartaoFamiliaCarioca().getValor());
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.INTEGER);
	    	}
	   
	    if(c.getNis() != null && !"".equals(c.getNis())) {
	    	stmt.setString(++indice, c.getNis());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	    
	    stmt.setString(++indice, c.getPeso());
	    stmt.setString(++indice, c.getComprimento());
	    stmt.setString(++indice, c.getCefalico());
	    
	   
	    if(c.getDataTestePezinho() != null) {
	    	stmt.setDate(++indice, java.sql.Date.valueOf( c.getDataTestePezinho() ));
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.DATE);
	    	}
	    
	    stmt.setString(++indice, c.getObservacoes());
	    
	    if(c.getParto() != null) {
	    	stmt.setInt(++indice, c.getParto().getValor());
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.DATE);
	    	}

	    stmt.setInt(++indice, c.getFamilia().getId());
	    
	    stmt.executeUpdate();
	    
	    ResultSet rs = stmt.getGeneratedKeys();  
        rs.next();  
        int id = rs.getInt(1);  
        
	    stmt.close();
	    rs.close();
	    
	    return id;
		
	}
	
	
	public List<Cidadao> consultar(Cidadao cidadaoFiltro, Connection con) throws SQLException {
		
	    List<Cidadao> lista = new ArrayList<Cidadao>();
	    
	    String sql = "SELECT C.ID_CIDADAO, C.NOME, C.CNS, C.CPF, C.DNV, C.NIS, C.DATA_NASCIMENTO, C.DATA_CADASTRO, " 
	    		+ "C.SEXO, C.RACA_COR, F.ID_FAMILIA, F.COD_FAMILIA FROM TBL_CIDADAO C " 
				+ "JOIN TBL_FAMILIA F ON " 
				+ "C.ID_FAMILIA = F.ID_FAMILIA " 
				+"WHERE  1";
	    
	    if(cidadaoFiltro.getNome() != null && !"".equals(cidadaoFiltro.getNome())) {
	    	sql += " AND C.NOME LIKE ? ";
	    }
	    if(cidadaoFiltro.getCpf() != null && !"".equals(cidadaoFiltro.getCpf())) {
	    	sql += " AND C.CPF = ? ";
	    }
	    if(cidadaoFiltro.getDnv() != null && !"".equals(cidadaoFiltro.getDnv())) {
	    	sql += " AND C.DNV = ? ";
	    }
	    if(cidadaoFiltro.getNis() != null && !"".equals(cidadaoFiltro.getNis())) {
	    	sql += " AND C.NIS = ? ";
	    }
	    if(cidadaoFiltro.getFamilia().getCodigo() != null && !"".equals(cidadaoFiltro.getFamilia().getCodigo())){
	    	sql += " AND F.COD_FAMILIA LIKE ? ";
	    }
 	      	      
	    sql += " ORDER BY NOME ASC ";
	      	   		     
	    PreparedStatement stmt = con.prepareStatement(sql);

	    int indice = 0;
	    Cidadao c = null;
	    
	    if(cidadaoFiltro.getNome() != null && !"".equals(cidadaoFiltro.getNome())) {
	   	 	stmt.setString(++indice, "%"+cidadaoFiltro.getNome()+"%");
	    }
	    if(cidadaoFiltro.getCpf() != null && !"".equals(cidadaoFiltro.getCpf())) {
	    	stmt.setString(++indice, cidadaoFiltro.getCpf());
	    }
	    if(cidadaoFiltro.getDnv() != null && !"".equals(cidadaoFiltro.getDnv())) {
	    	stmt.setString(++indice, cidadaoFiltro.getDnv());
	    }
	    if(cidadaoFiltro.getNis() != null && !"".equals(cidadaoFiltro.getNis())) {
	    	stmt.setString(++indice, cidadaoFiltro.getNis());
	    }
	    if(cidadaoFiltro.getFamilia().getCodigo() != null && !"".equals(cidadaoFiltro.getFamilia().getCodigo())){
	    	stmt.setString(++indice, "%"+cidadaoFiltro.getFamilia().getCodigo()+"%");
	    }
	    	   	   	   
	    ResultSet rs = stmt.executeQuery();
	    
	    while (rs.next()) {
	    	
	    	c = new Cidadao();
	    	
	    	c.setId(rs.getInt("ID_CIDADAO"));
	    	
	    	c.setNome(rs.getString("NOME"));
	    	LocalDate dataNasc = rs.getDate("DATA_NASCIMENTO").toLocalDate();    
			c.setDataNascimento(dataNasc);		
	    	c.setSexo(Sexo.buscaEnum("SEXO"));
			c.setRacaCor(RacaCor.buscaEnum(rs.getInt("RACA_COR")));
			
		    c.setCpf(rs.getString("CPF"));
		    c.setDnv(rs.getString("DNV"));

		    c.setCns(rs.getString("CNS"));
		    c.setNis(rs.getString("NIS"));
		    
		    LocalDate dataCadastro = rs.getDate("DATA_CADASTRO").toLocalDate();    
		    c.setDataCadastro(dataCadastro);
		    
		    c.setFamilia(new Familia(rs.getInt("ID_FAMILIA")));
		    c.getFamilia().setCodigo(rs.getString("COD_FAMILIA"));
		    
		    lista.add(c);
	    }
	    
	    rs.close();
	    stmt.close();        
		
	    return lista;

}

	
	
	
	public Cidadao ObterPorId(int id, Connection con) throws SQLException {
		
		String sql = "SELECT " +
					
					" C.ID_CIDADAO, " +
					" C.NOME, " +
					" C.DATA_NASCIMENTO, " +
					" C.SEXO, " +
					" C.RACA_COR,"+
					
					" C.SITUACAO_FAMILIAR, "+
					" C.FREQUENTA_ESCOLA, "+
					" C.NATUREZA_ESCOLA, "+
					" C.POSSUI_PLANO_SAUDE, "+
					
					" C.FUMANTE, "+
					" C.PRESENTE_DURANTE_VISITA, "+
					" C.ESCOLARIDADE, "+
					" C.OCUPACAO, "+
					
					" C.CPF, "+		
					" C.DNV, "+				 
					" C.NACIONALIDADE, "+
					" C.NOME_MAE, "+
					" C.NOME_PAI, "+
					" C.MUNICIPIO, "+
					
					" C.UF, "+
					" C.PAIS_ORIGEM, "+
					" C.DATA_ENTRADA_BRASIL, "+
					" C.CNS, "+
					
					" C.BOLSA_FAMILIA, "+
					" C.CARTAO_FAMILIA_CARIOCA, "+
					" C.NIS, "+
					" C.PESO, "+
					" C.COMPRIMENTO, "+
					" C.CEFALICO, "+ 
					
					" C.DATA_TESTE_PEZINHO, "+
					" C.OBSERVACOES, "+
					" C.PARTO, " +
					" C.DATA_CADASTRO, "+
	
		    		" F.ID_FAMILIA, F.COD_FAMILIA FROM TBL_CIDADAO C " +
					" JOIN TBL_FAMILIA F ON " +
					" C.ID_FAMILIA = F.ID_FAMILIA " +
					
					" WHERE  C.ID_CIDADAO = ? ";
		
		Cidadao c = null;
			
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
				
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
				 
			c = new Cidadao();
	    	
	    	c.setId(rs.getInt("ID_CIDADAO"));
	    	c.setNome(rs.getString("NOME"));
	    	LocalDate dataNasc = rs.getDate("DATA_NASCIMENTO").toLocalDate();    
			c.setDataNascimento(dataNasc);		
	    	c.setSexo(Sexo.buscaEnum(rs.getString("SEXO")));
			c.setRacaCor(RacaCor.buscaEnum(rs.getInt("RACA_COR")));
			
			c.setSituacaoFamiliar(SituacaoFamiliar.buscaEnum( Util.getInteger(rs, "SITUACAO_FAMILIAR")));
			c.setFrequentaEscola(SimNao.buscaEnum( Util.getInteger(rs, "FREQUENTA_ESCOLA")));
			c.setNaturezaEscola(NaturezaEscola.buscaEnum(Util.getInteger(rs, "NATUREZA_ESCOLA")));
		    c.setPossuiPlanoSaude(SimNao.buscaEnum( Util.getInteger(rs, "POSSUI_PLANO_SAUDE")));
		    
		    c.setFumante(SimNao.buscaEnum( Util.getInteger(rs, "FUMANTE")));
		    c.setPresenteDuranteVisita(SimNao.buscaEnum( Util.getInteger(rs, "PRESENTE_DURANTE_VISITA")));
		    c.setEscolaridade(Escolaridade.buscaEnum(Util.getInteger(rs, "ESCOLARIDADE")));
		    c.setOcupacao(rs.getString("OCUPACAO"));
		    
		    c.setCpf(rs.getString("CPF"));
		    c.setDnv(rs.getString("DNV"));
		    
		    
		    c.setNacionalidade(Nacionalidade.buscaEnum(Util.getString(rs, "NACIONALIDADE")));
		    c.setNomeMae(rs.getString("NOME_MAE"));
		    c.setNomePai(rs.getString("NOME_PAI"));
		    c.setMunicipio(rs.getString("MUNICIPIO"));
		    
		    c.setUf(Uf.buscaEnum(Util.getString(rs, "UF")));
		    c.setPaisOrigem(rs.getString("PAIS_ORIGEM"));
		    c.setDataEntradaBrasil(Util.getDate(rs, "DATA_ENTRADA_BRASIL"));
		    c.setCns(rs.getString("CNS"));
		  
		    c.setBolsaFamilia(SimNao.buscaEnum( Util.getInteger(rs, "BOLSA_FAMILIA")));
		    c.setCartaoFamiliaCarioca(SimNao.buscaEnum( Util.getInteger(rs, "CARTAO_FAMILIA_CARIOCA")));
		    
		    c.setNis(rs.getString("NIS"));
		    c.setPeso(rs.getString("PESO"));
		    
		    c.setComprimento(rs.getString("COMPRIMENTO"));
		    c.setCefalico(rs.getString("CEFALICO"));
		    c.setDataTestePezinho(Util.getDate(rs, "DATA_TESTE_PEZINHO")  );
		    
		    c.setParto(Parto.buscaEnum(Util.getInteger(rs, "PARTO")));
		    c.setObservacoes(rs.getString("OBSERVACOES"));

		    c.setDataCadastro(Util.getDate(rs, "DATA_CADASTRO"));
		    
		    c.setFamilia(new Familia(rs.getInt("ID_FAMILIA")));
		    c.getFamilia().setCodigo(rs.getString("COD_FAMILIA"));

		} 
		      
		rs.close();
		stmt.close();
			      	
		return c;
	}
	

	public void Atualizar(Cidadao c, Connection con) throws SQLException {
		
		String sql = " UPDATE TBL_CIDADAO SET " +
									
					" NOME = ? , " +
					" DATA_NASCIMENTO = ? , " +
					" SEXO = ? , " +
					" RACA_COR = ? ,"+
					
					" SITUACAO_FAMILIAR = ?, "+
					" FREQUENTA_ESCOLA = ? , "+
					" NATUREZA_ESCOLA = ? , "+
					" POSSUI_PLANO_SAUDE = ? , "+
					
					" FUMANTE = ? , "+
					" PRESENTE_DURANTE_VISITA = ? , "+
					" ESCOLARIDADE = ? , "+
					" OCUPACAO = ? , "+
					
					" CPF = ? , "+		
					" DNV = ? , "+				 
					" NACIONALIDADE = ? , "+
					" NOME_MAE = ? , "+
					" NOME_PAI = ? , "+
					" MUNICIPIO = ? , "+
					
					
					" UF = ? , "+
					" PAIS_ORIGEM = ? , "+
					" DATA_ENTRADA_BRASIL = ? , "+
					" CNS = ? , "+
					
					" BOLSA_FAMILIA = ? , "+
					" CARTAO_FAMILIA_CARIOCA = ? , "+
					" NIS = ? , "+
					" PESO = ? , "+
					" COMPRIMENTO = ? , "+
					" CEFALICO = ? , "+ 
					
					" DATA_TESTE_PEZINHO = ? , "+
					" OBSERVACOES = ? , "+
					" PARTO = ? , " +
					
					" ID_FAMILIA = ? "+
					" WHERE ID_CIDADAO = ? ";
				
	    
	    Integer indice = 0;
	        
	    PreparedStatement stmt = con.prepareStatement(sql);
	    	
	    stmt.setString(++indice, c.getNome());
	    stmt.setDate(++indice, java.sql.Date.valueOf( c.getDataNascimento() ));
	    stmt.setString(++indice, c.getSexo().getValor());
	    stmt.setInt(++indice, c.getRacaCor().getValor());
	    
	    if(c.getSituacaoFamiliar() != null) {
	    	stmt.setInt(++indice, c.getSituacaoFamiliar().getValor());
	    } else {
		   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getFrequentaEscola() != null) {
	    	stmt.setInt(++indice, c.getFrequentaEscola().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getNaturezaEscola() != null) {
	    	stmt.setInt(++indice, c.getNaturezaEscola().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getPossuiPlanoSaude() != null) {
	    	stmt.setInt(++indice, c.getPossuiPlanoSaude().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}

	    if(c.getFumante() != null) {
	    	stmt.setInt(++indice, c.getFumante().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getPresenteDuranteVisita() != null) {
	    	stmt.setInt(++indice, c.getPresenteDuranteVisita().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    if(c.getEscolaridade() != null) {
	    	stmt.setInt(++indice, c.getEscolaridade().getValor());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.INTEGER);
			}
	    
	    stmt.setString(++indice, c.getOcupacao());
	    
	
	    if(c.getCpf() != null && !"".equals(c.getCpf())) {
	    	stmt.setString(++indice, c.getCpf());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	    
	    if(c.getDnv() != null && !"".equals(c.getDnv())) {
	    	stmt.setString(++indice, c.getDnv());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	    
	    stmt.setString(++indice, c.getNacionalidade().getValor());
	    
	    stmt.setString(++indice, c.getNomeMae());
	    stmt.setString(++indice, c.getNomePai());
	    stmt.setString(++indice, c.getMunicipio());
	    

	    if(c.getUf() != null) {
	    	stmt.setString(++indice, c.getUf().getValor());
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.VARCHAR);
	    	}
	    
	    
	    stmt.setString(++indice, c.getPaisOrigem());
	    
	    if(c.getDataEntradaBrasil() != null) {
	    	stmt.setDate(++indice, java.sql.Date.valueOf( c.getDataEntradaBrasil() ));
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.DATE);
	    	}
	    
	    
	    
	    if(c.getCns() != null && !"".equals(c.getCns())) {
	    	stmt.setString(++indice, c.getCns());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	   
	    if(c.getBolsaFamilia() != null) {
	    	stmt.setInt(++indice, c.getBolsaFamilia().getValor());
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.INTEGER);
	    	}
	    
	    if(c.getCartaoFamiliaCarioca() != null) {
	    	stmt.setInt(++indice, c.getCartaoFamiliaCarioca().getValor());
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.INTEGER);
	    	}
	   
	    if(c.getNis() != null && !"".equals(c.getNis())) {
	    	stmt.setString(++indice, c.getNis());
	    } else {
			   stmt.setNull(++indice, java.sql.Types.VARCHAR);
			}
	    
	    stmt.setString(++indice, c.getPeso());
	    stmt.setString(++indice, c.getComprimento());
	    stmt.setString(++indice, c.getCefalico());
	    
	   
	    if(c.getDataTestePezinho() != null) {
	    	stmt.setDate(++indice, java.sql.Date.valueOf( c.getDataTestePezinho() ));
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.DATE);
	    	}
	    
	    stmt.setString(++indice, c.getObservacoes());
	    
	    if(c.getParto() != null) {
	    	stmt.setInt(++indice, c.getParto().getValor());
	    } else {
	    	stmt.setNull(++indice, java.sql.Types.DATE);
	    	}

	    stmt.setInt(++indice, c.getFamilia().getId());
	    stmt.setInt(++indice, c.getId());
	    
	    stmt.executeUpdate();
	    stmt.close();
	 
	}
	
	
public static List<Cidadao> ListarTodos(Connection con) throws SQLException {
		
	    List<Cidadao> lista = new ArrayList<Cidadao>();
	    
	    String sql = " SELECT * FROM TBL_CIDADAO F WHERE 1 " ;	
	   
	    sql += " ORDER BY NOME ASC ";
	      	   		     
	    PreparedStatement stmt = con.prepareStatement(sql);

	    Cidadao c = null;
  	   	   
	    ResultSet rs = stmt.executeQuery();
	    
	    while (rs.next()) {
	    	
	    	c = new Cidadao();
	    	
	    	c.setId(rs.getInt("ID_CIDADAO"));
	    	c.setNome(rs.getString("NOME"));
	    	LocalDate dataNasc = rs.getDate("DATA_NASCIMENTO").toLocalDate();    
			c.setDataNascimento(dataNasc);		
	    	c.setSexo(Sexo.buscaEnum("SEXO"));
			c.setRacaCor(RacaCor.buscaEnum(rs.getInt("RACA_COR")));
			c.setSituacaoFamiliar(SituacaoFamiliar.buscaEnum(rs.getInt("SITUACAO_FAMILIAR")));
			c.setFrequentaEscola(SimNao.buscaEnum(rs.getInt("FREQUENTA_ESCOLA")));
			c.setNaturezaEscola(NaturezaEscola.buscaEnum(rs.getInt("NATUREZA_ESCOLA")));	
		    c.setPossuiPlanoSaude(SimNao.buscaEnum(rs.getInt("POSSUI_PLANO_SAUDE")));
		    c.setFumante(SimNao.buscaEnum(rs.getInt("FUMANTE")));
		    c.setPresenteDuranteVisita(SimNao.buscaEnum(rs.getInt("PRESENTE_DURANTE_VISITA")));
		    c.setEscolaridade(Escolaridade.buscaEnum(rs.getInt("ESCOLARIDADE")));
		    c.setOcupacao(rs.getString("OCUPACAO"));
		    c.setCpf(rs.getString("CPF"));
		    c.setDnv(rs.getString("DNV"));
		    c.setNacionalidade(Nacionalidade.buscaEnum(rs.getString("NACIONALIDADE")));
		    c.setNomeMae(rs.getString("NOME_MAE"));
		    c.setNomePai(rs.getString("NOME_PAI"));
		    c.setMunicipio(rs.getString("MUNICIPIO"));
		    c.setUf(Uf.buscaEnum(rs.getString("UF")));
		    c.setPaisOrigem(rs.getString("PAIS_ORIGEM"));
		    
		    LocalDate dataEntradaBrasil = rs.getDate("DATA_ENTRADA_BRASIL").toLocalDate();    
		    c.setDataEntradaBrasil(dataEntradaBrasil);
		    
		    c.setCns(rs.getString("CNS"));
		    c.setBolsaFamilia(SimNao.buscaEnum(rs.getInt("BOLSA_FAMILIA")));
		    c.setCartaoFamiliaCarioca(SimNao.buscaEnum(rs.getInt("CARTAO_FAMILIA_CARIOCA")));
		    c.setNis(rs.getString("NIS"));
		    c.setPeso(rs.getString("PESO"));
		    c.setComprimento(rs.getString("COMPRIMENTO"));
		    c.setCefalico(rs.getString("CEFALICO"));
		    
		    LocalDate dataTestePezinho = rs.getDate("DATA_TESTE_PEZINHO").toLocalDate();    
		    c.setDataTestePezinho(dataTestePezinho);
		    
		    c.setObservacoes(rs.getString("OBSERVACOES"));

		    LocalDate dataCadastro = rs.getDate("DATA_CADASTRO").toLocalDate();    
		    c.setDataCadastro(dataCadastro);
		    
		    c.setFamilia(new Familia(rs.getInt("ID_FAMILIA")));
		    
		    lista.add(c);
	    }
	    
	    rs.close();
	    stmt.close();        
		
	    return lista;

}

	public boolean verificarCns(String cns, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_CIDADAO WHERE CNS = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, cns);
					
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}

	public boolean verificarNis(String nis, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_CIDADAO WHERE NIS = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, nis);
					
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}
	
public boolean verificarCpf(String cpf, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_CIDADAO WHERE CPF = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, cpf);
					
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}
	
	public boolean verificarDnv(String dnv, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_CIDADAO WHERE DNV = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, dnv);
					
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}
	
	
	
	
	
	
	public boolean verificarCns(String cnsNovo, String cnsAtual, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_CIDADAO WHERE CNS = ? AND CNS != ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, cnsNovo);
		stmt.setString(2, cnsAtual);
		
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}

	public boolean verificarNis(String nisNovo, String nisAtual, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_CIDADAO WHERE NIS = ? AND NIS != ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, nisNovo);
		stmt.setString(2, nisAtual);
		
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}
	
	public boolean verificarCpf(String cpfNovo, String cpfAtual, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_CIDADAO WHERE CPF = ? AND CPF != ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, cpfNovo);
		stmt.setString(2, cpfAtual);
		
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}
	
	public boolean verificarDnv(String dnvNovo, String dnvAtual, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_CIDADAO WHERE DNV = ? AND DNV != ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, dnvNovo);
		stmt.setString(2, dnvAtual);	
	
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}
}
