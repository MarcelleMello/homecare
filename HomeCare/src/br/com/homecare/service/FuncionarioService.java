package br.com.homecare.service;

import java.io.Serializable;
import java.sql.Connection;
import br.com.homecare.dao.DBUtil;
import br.com.homecare.dao.FuncionarioDAO;
import br.com.homecare.model.Funcionario;

public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 4833077585041107955L;
	
	public Funcionario realizarLogin(String login, String senha) throws Exception {
		FuncionarioDAO dao = new FuncionarioDAO();
		Connection con = DBUtil.getConnection();
		Funcionario f = null;
		try {
			
			f = dao.realizarLogin(login, senha, con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return f;
	}
	
}
