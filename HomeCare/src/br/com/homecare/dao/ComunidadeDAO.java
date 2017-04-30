package br.com.homecare.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.homecare.model.Comunidade;
import br.com.homecare.model.Unidade;

public class ComunidadeDAO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7954701554450313979L;

	public List<Comunidade> listarTodos(Connection con) throws SQLException {
		
		List<Comunidade> lista = new ArrayList<Comunidade>();

		String sql = "SELECT * FROM TBL_COMUNIDADE";
					
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
				
		while (rs.next()) {
				 
			Comunidade c = new Comunidade();
		         
			c.setId(rs.getInt("ID_COMUNIDADE"));
		    c.setNome(rs.getString("NOME"));
		    c.setCodigo(rs.getString("COD_UNIDADE"));
		    c.setUnidade(new Unidade(rs.getInt("ID_UNIDADE")));
		    
		    lista.add(c);
		} 
			      
		rs.close();
		stmt.close();
			      	
		return lista;
		
	}

}
