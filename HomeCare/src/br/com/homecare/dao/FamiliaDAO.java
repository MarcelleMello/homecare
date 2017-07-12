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
import br.com.homecare.model.Familia;
import br.com.homecare.model.Funcionario;

public class FamiliaDAO implements Serializable {

	private static final long serialVersionUID = 5353231339405622721L;

	public int cadastrar(Familia f, Connection con) throws SQLException {

		String sql = " INSERT INTO TBL_FAMILIA(COD_FAMILIA, ENDERECO, BAIRRO, COMPLEMENTO, "
				+ " NUMERO, CEP, TELEFONE, DATA_CADASTRO, ID_FUNCIONARIO) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE(), ?) ";
        
	    Integer indice = 0;
	        
	    PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

	    stmt.setString(++indice, f.getCodigo());
	    stmt.setString(++indice, f.getEndereco() );
	    stmt.setString(++indice, f.getBairro());
	    stmt.setString(++indice, f.getComplemento());
	    stmt.setString(++indice, f.getNumero());
	    stmt.setString(++indice, f.getCep());
	    stmt.setString(++indice, f.getTelefone());
	    stmt.setInt(++indice, f.getFuncionario().getId());
	    
	    stmt.executeUpdate();
	    
	    ResultSet rs = stmt.getGeneratedKeys();  
        rs.next();  
        int id = rs.getInt(1);  
        
	    stmt.close();
	    rs.close();
	    
	    return id;
		
	}
	
	public static List<Familia> consultar(Familia familiaFiltro, Connection con) throws SQLException {
		
	    List<Familia> lista = new ArrayList<Familia>();
	    
	    String sql = " SELECT * FROM TBL_FAMILIA F WHERE 1 " ;	
	    
	    if(familiaFiltro.getCodigo() != null && !"".equals(familiaFiltro.getCodigo())) {
	          sql += " AND F.COD_FAMILIA LIKE ? ";
	     }
 	      	      
	    sql += " ORDER BY DATA_CADASTRO DESC";
	      	   		     
	    PreparedStatement stmt = con.prepareStatement(sql);

	    int indice = 0;
	    Familia f = null;
	    
	    if(familiaFiltro.getCodigo() != null && !"".equals(familiaFiltro.getCodigo())) {
	   	 	stmt.setString(++indice, "%"+familiaFiltro.getCodigo()+"%");
	    }
	    	   	   	   
	    ResultSet rs = stmt.executeQuery();
	    
	    while (rs.next()) {
	   	 
	    	f = new Familia();   
			
		    f.setId(rs.getInt("ID_FAMILIA")); 
	    	f.setCodigo(rs.getString("COD_FAMILIA"));
	    	f.setEndereco(rs.getString("ENDERECO"));
	    	f.setBairro(rs.getString("BAIRRO"));
	    	f.setComplemento(rs.getString("COMPLEMENTO"));    	
	    	f.setNumero(rs.getString("NUMERO"));
	    	f.setCep(rs.getString("CEP"));
	    	f.setTelefone(rs.getString("TELEFONE"));
	    	
	    	LocalDate data = rs.getDate("DATA_CADASTRO").toLocalDate();    
		    f.setDataCadastro(data);	

		    f.setFuncionario(new Funcionario(rs.getInt("ID_FUNCIONARIO")));
		    
		    lista.add(f);
	    }
	    
	    rs.close();
	    stmt.close();        
		
	return lista;

}

	
	public boolean verificarCodigo(String codigo, Connection con) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM TBL_FAMILIA WHERE COD_FAMILIA = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, codigo);
					
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
		   return rs.getInt("TOTAL") > 0;
		} 
		
		rs.close();
		stmt.close();
		
		return false;
	}
	
	
	public Familia ObterPorId(int id, Connection con) throws SQLException {
		
		String sql = "SELECT * FROM TBL_FAMILIA WHERE ID_FAMILIA = ? ";
		
		Familia f = null;
			
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
				
		ResultSet rs = stmt.executeQuery();
				
		if (rs.next()) {
				 
			f = new Familia();   
			
		    f.setId(rs.getInt("ID_FAMILIA")); 
	    	f.setCodigo(rs.getString("COD_FAMILIA"));
	    	f.setEndereco(rs.getString("ENDERECO"));
	    	f.setBairro(rs.getString("BAIRRO"));
	    	f.setComplemento(rs.getString("COMPLEMENTO"));    	
	    	f.setNumero(rs.getString("NUMERO"));
	    	f.setCep(rs.getString("CEP"));
	    	f.setTelefone(rs.getString("TELEFONE"));
	    	
	    	LocalDate data = rs.getDate("DATA_CADASTRO").toLocalDate();    
		    f.setDataCadastro(data);	
	
		    f.setFuncionario(new Funcionario(rs.getInt("ID_FUNCIONARIO")));
		} 
		      
		rs.close();
		stmt.close();
			      	
		return f;
	}
	

	public void Atualizar(Familia f, Connection con) throws SQLException {
		
		String sql = " UPDATE TBL_FAMILIA SET COD_FAMILIA = ?, ENDERECO = ?, BAIRRO = ?, "
				+ " COMPLEMENTO = ?, NUMERO = ?, CEP = ?, TELEFONE = ?, ID_FUNCIONARIO = ? "
				+ " WHERE ID_FAMILIA = ? ";
				
	    
	    Integer indice = 0;
	        
	    PreparedStatement stmt = con.prepareStatement(sql);
	
	    stmt.setString(++indice, f.getCodigo());
	    stmt.setString(++indice, f.getEndereco());
	    stmt.setString(++indice, f.getBairro());
	    stmt.setString(++indice, f.getComplemento());
	    stmt.setString(++indice, f.getNumero());
	    stmt.setString(++indice, f.getCep());
	    stmt.setString(++indice, f.getTelefone());
	    stmt.setInt(++indice, f.getFuncionario().getId());
	    stmt.setInt(++indice, f.getId());
	    
	    stmt.executeUpdate();
	    stmt.close();
	 
	}
}
