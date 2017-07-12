package br.com.homecare.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.homecare.model.Unidade;

public class UnidadeDAO implements Serializable{

	private static final long serialVersionUID = 2385077063152144316L;
	
	public List<Unidade> listarTodos(Connection con) throws SQLException {
		
		List<Unidade> lista = new ArrayList<Unidade>();

		String sql = "SELECT * FROM TBL_UNIDADE";
					
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
				
		while (rs.next()) {
				 
			Unidade u = new Unidade();
		         
			u.setId(rs.getInt("ID_UNIDADE"));
		    u.setNome(rs.getString("NOME"));
		    u.setCodigo(rs.getString("COD_UNIDADE"));
		  
		    
		    lista.add(u);
		} 
			      
		rs.close();
		stmt.close();
			      	
		return lista;
		
	}
}
