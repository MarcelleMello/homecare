package br.com.homecare.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import br.com.homecare.dao.DBUtil;
import br.com.homecare.dao.FuncionarioDAO;
import br.com.homecare.model.Funcionario;
import br.com.homecare.util.Util;


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

	public void cadastrar(Funcionario f) throws ServiceException, Exception {
		FuncionarioDAO dao = new FuncionarioDAO();
		Connection con = DBUtil.getConnection();
		try {
			DBUtil.beginTransaction(con);
			f.setSenha(Util.md5(f.getSenha()));
			
			//se for true quer dizer que foi encontrado um usuário com a mesma matrícula
			if(dao.obterPorMatricula(f.getCodigo(), con)) {
				throw new ServiceException("Matrícula existente");
			}
			
			dao.cadastrar(f,con);
			DBUtil.commit(con);
		} catch (ServiceException e) {
			DBUtil.rollback(con);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			DBUtil.rollback(con);
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
	}
	
	public List<Funcionario> consultar(Funcionario funcionarioFiltrado, Funcionario usuarioSessao) throws ServiceException, Exception {		 
		Connection con = DBUtil.getConnection();
		List<Funcionario> funcionarios = null;
		try {
			DBUtil.beginTransaction(con);
			
			funcionarios = FuncionarioDAO.consultar(funcionarioFiltrado, usuarioSessao, con);
		
			DBUtil.commit(con);
		} catch (Exception e) {
			DBUtil.rollback(con);
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return funcionarios;
	}
	

	
	}
