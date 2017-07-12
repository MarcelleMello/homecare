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

	
	public boolean verificarMatricula(String matricula, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_FUNCIONARIO WHERE COD_FUNCIONARIO = ?";
				
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, matricula);
					
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
 	
		return false;
	}

public static List<Funcionario> consultar(Funcionario funcionarioFiltro, Connection con) throws SQLException {
		
	    List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	    
	    String sql = " SELECT * FROM TBL_FUNCIONARIO F WHERE 1 " ;	     
	      
	      if(funcionarioFiltro.getNome() != null && !"".equals(funcionarioFiltro.getNome())) {
	           sql += " AND F.NOME LIKE ? ";
	      }

	      if(funcionarioFiltro.getPerfil() != null && !"".equals(funcionarioFiltro.getPerfil())) {
		   	   sql += " AND F.PERFIL = ? ";
		  }

	      if(funcionarioFiltro.getAtivo() != null && !"".equals(funcionarioFiltro.getAtivo())) {
		   	   sql += " AND F.ATIVO = ? ";
		  }
	     	      	      	      
	      sql += " ORDER BY NOME ASC";
	      	   		     
	    PreparedStatement stmt = con.prepareStatement(sql);
	    int indice = 0;
	    Funcionario func = null;
	    	   	   	    
	    if(funcionarioFiltro.getNome() != null && !"".equals(funcionarioFiltro.getNome())) {
	   	 	stmt.setString(++indice, "%"+funcionarioFiltro.getNome()+"%");
	    }

	    if(funcionarioFiltro.getPerfil() != null && !"".equals(funcionarioFiltro.getPerfil())) {
	   	 	stmt.setString(++indice, "%"+funcionarioFiltro.getPerfil()+"%");
	    }
	   
	    if(funcionarioFiltro.getAtivo() != null && !"".equals(funcionarioFiltro.getAtivo())) {
	   	 	stmt.setString(++indice, "%"+funcionarioFiltro.getAtivo()+"%");
	    }
	       
	    ResultSet rs = stmt.executeQuery();
	    
	    while (rs.next()) {
	   	 
	    	func = new Funcionario();   
			
	    	func.setNome(rs.getString("NOME"));
	    	func.setTelefone(rs.getString("TELEFONE"));
	    	func.setCodigo(rs.getString("COD_FUNCIONARIO"));
	    	func.setMicroArea(rs.getString("MICRO_AREA"));	    			    
	    	func.setPerfil(Perfil.buscaEnum(rs.getInt("PERFIL")));		    
	    	func.setComunidade(new Comunidade(rs.getInt("ID_COMUNIDADE")));
		    func.setAtivo(SimNao.buscaEnum(rs.getInt("ATIVO")));
		    
		    LocalDate data = rs.getDate("DATA_CADASTRO").toLocalDate();    
		    func.setDataCadastro(data);		
		    
		    func.setId(rs.getInt("ID_FUNCIONARIO")); 
		    
		    funcionarios.add(func);
		    
	    }
	    
	    rs.close();
	    stmt.close();        
		
	return funcionarios;

}

public Funcionario ObterPorId(int id, Connection con) throws SQLException {
	String sql = "SELECT * FROM TBL_FUNCIONARIO WHERE ID_FUNCIONARIO = ? ";
	
	Funcionario f = null;
		
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setInt(1, id);
			
	ResultSet rs = stmt.executeQuery();
			
	if (rs.next()) {
			 
		f = new Funcionario();
	         
		f.setId(rs.getInt("ID_FUNCIONARIO"));
	    f.setNome(rs.getString("NOME"));
	    f.setSenha(rs.getString("SENHA"));
	    f.setTelefone(rs.getString("TELEFONE"));
	    f.setCodigo(rs.getString("COD_FUNCIONARIO"));
	    f.setAtivo(SimNao.buscaEnum(rs.getInt("ATIVO")));
	    f.setMicroArea(rs.getString("MICRO_AREA"));
	    f.setPerfil(Perfil.buscaEnum(rs.getInt("PERFIL")));
	    
	    LocalDate data = rs.getDate("DATA_CADASTRO").toLocalDate();    
	    f.setDataCadastro(data);
	         
	    f.setComunidade(new Comunidade(rs.getInt("ID_COMUNIDADE")));
	} 
		      
	rs.close();
	stmt.close();
		      	
	return f;
}

	public void Atualizar(Funcionario f, Connection con) throws SQLException {
		
		String sql = " UPDATE TBL_FUNCIONARIO SET NOME = ?, TELEFONE = ?, SENHA = ?, "
				+ " MICRO_AREA = ?, ATIVO = ?, ID_COMUNIDADE = ?, PERFIL = ? "
				+ " WHERE ID_FUNCIONARIO = ? ";
				
	    
	    Integer indice = 0;
	        
	    PreparedStatement stmt = con.prepareStatement(sql);
	
	    stmt.setString(++indice, f.getNome());
	    stmt.setString(++indice, f.getTelefone());
	    stmt.setString(++indice, f.getSenha());
	    
	    stmt.setString(++indice, f.getMicroArea());
	    stmt.setInt(++indice, f.getAtivo().getValor());
	    stmt.setInt(++indice, f.getComunidade().getId());
	    stmt.setInt(++indice, f.getPerfil().getValor());

	    stmt.setInt(++indice, f.getId());
	    
	    stmt.executeUpdate();
	    stmt.close();
	 
	}

	public static List<Funcionario> ListarTodos(Connection con) throws SQLException {
		
	    List<Funcionario> lista = new ArrayList<Funcionario>();
	    
	    String sql = " SELECT * FROM TBL_FUNCIONARIO F WHERE 1 " ;	     
	      	      	      
	    sql += " ORDER BY NOME ASC";
	      	   		     
	    PreparedStatement stmt = con.prepareStatement(sql);
	    Funcionario func = null;
 
	    ResultSet rs = stmt.executeQuery();
	    
	    while (rs.next()) {
	   	 
	    	func = new Funcionario();   
			
	    	func.setNome(rs.getString("NOME"));
	    	func.setTelefone(rs.getString("TELEFONE"));
	    	func.setCodigo(rs.getString("COD_FUNCIONARIO"));
	    	func.setMicroArea(rs.getString("MICRO_AREA"));	    			    
	    	func.setPerfil(Perfil.buscaEnum(rs.getInt("PERFIL")));		    
	    	func.setComunidade(new Comunidade(rs.getInt("ID_COMUNIDADE")));
		    func.setAtivo(SimNao.buscaEnum(rs.getInt("ATIVO")));
		    
		    LocalDate data = rs.getDate("DATA_CADASTRO").toLocalDate();    
		    func.setDataCadastro(data);		
		    
		    func.setId(rs.getInt("ID_FUNCIONARIO")); 
		    
		    lista.add(func);
		    
	    }
	    
	    rs.close();
	    stmt.close();        
		
	    return lista;

	}
}
