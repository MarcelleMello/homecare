package br.com.homecare.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import br.com.homecare.model.Comunidade;
import br.com.homecare.model.Funcionario;
import br.com.homecare.model.tipo.Perfil;
import br.com.homecare.model.tipo.SimNao;


public class FuncionarioDAO implements Serializable{

	private static final long serialVersionUID = 7585270797836230443L;
	
	public Funcionario realizarLogin(String matricula, String senha, Connection con) throws SQLException {
			
		String sql = "SELECT * FROM TBL_FUNCIONARIO WHERE COD_FUNCIONARIO = ? AND SENHA = ? ";
				
		Funcionario f = null;
			
		int indice = 0;	
			
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(++indice, matricula);
		stmt.setString(++indice, senha);
				
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
				 
			f = new Funcionario();
		         
			f.setId(rs.getInt("ID_FUNCIONARIO"));
		    f.setNome(rs.getString("NOME"));
		    f.setCodigo(rs.getString("COD_FUNCIONARIO"));
		    f.setSenha(rs.getString("SENHA"));
		    f.setTelefone(rs.getString("TELEFONE"));
		    
		    f.setAtivo(SimNao.buscaEnum(rs.getInt("ATIVO")));
		    f.setPerfil(Perfil.buscaEnum(rs.getInt("PERFIL")));
		    
		    LocalDate data = rs.getDate("DATA_CADASTRO").toLocalDate();    
		    f.setDataCadastro(data);
		         
		    f.setComunidade(new Comunidade(rs.getInt("ID_COMUNIDADE")));
		} 
			      
		rs.close();
		stmt.close();
			      	
		return f;
	}

	public int cadastrar(Funcionario f, Connection con) throws SQLException {
		
		String sql = " INSERT INTO TBL_FUNCIONARIO(NOME, TELEFONE, SENHA, DATA_CADASTRO, "
				+ " MICRO_AREA, ATIVO, ID_COMUNIDADE, PERFIL, COD_FUNCIONARIO) "
				+ " VALUES (?, ?, ?, SYSDATE(), ?, ?, ?, ?, ?) ";
        
	    Integer indice = 0;
	        
	    PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

	    stmt.setString(++indice, f.getNome());
	    stmt.setString(++indice, f.getTelefone());
	    stmt.setString(++indice, f.getSenha());
	    
	    stmt.setString(++indice, f.getMicroArea());
	    stmt.setInt(++indice, f.getAtivo().getValor());
	    stmt.setInt(++indice, f.getComunidade().getId());
	    stmt.setInt(++indice, f.getPerfil().getValor());
	    stmt.setString(++indice, f.getCodigo());
	    
	    stmt.executeUpdate();
	    
	    ResultSet rs = stmt.getGeneratedKeys();  
        rs.next();  
        int id = rs.getInt(1);  
        
	    stmt.close();
	    rs.close();
	    
	    return id;
		
	}
	
}
