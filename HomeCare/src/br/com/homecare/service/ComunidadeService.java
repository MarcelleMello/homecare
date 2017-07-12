package br.com.homecare.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import br.com.homecare.dao.ComunidadeDAO;
import br.com.homecare.dao.DBUtil;
import br.com.homecare.model.Comunidade;

public class ComunidadeService implements Serializable {

	private static final long serialVersionUID = -1007771106079137090L;

	public List<Comunidade> listarTodos() throws Exception {
		ComunidadeDAO dao = new ComunidadeDAO();
		Connection con = DBUtil.getConnection();
		List<Comunidade> lista = null;
		try {
			
			lista = dao.listarTodos(con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return lista;
	}

}
