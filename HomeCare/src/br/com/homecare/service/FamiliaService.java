package br.com.homecare.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import br.com.homecare.dao.DBUtil;
import br.com.homecare.dao.FamiliaDAO;
import br.com.homecare.model.Familia;
import br.com.homecare.model.Funcionario;

public class FamiliaService implements Serializable{

	private static final long serialVersionUID = 1364879138748317316L;

	
	
	public void cadastrar(Familia familia, Funcionario funcionario) throws ServiceException, Exception {
		FamiliaDAO dao = new FamiliaDAO();
		Connection con = DBUtil.getConnection();
		try {
			DBUtil.beginTransaction(con);
			
			//se for true quer dizer que foi encontrado um usuário com a mesma matrícula
			if(dao.verificarCodigo(familia.getCodigo(), con)) {
				throw new ServiceException("Código já existe.");
			}
			
			familia.setFuncionario(funcionario);
			
			dao.cadastrar(familia,con);
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
	
	public List<Familia> consultar(Familia familiaFiltro) throws ServiceException, Exception {		 
		Connection con = DBUtil.getConnection();
		List<Familia> lista = null;
		try {
			
			lista = FamiliaDAO.consultar(familiaFiltro, con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return lista;
	}

	public Familia ObterPorId(int id) throws Exception {
		FamiliaDAO dao = new FamiliaDAO();
		Connection con = DBUtil.getConnection();
		Familia f = null;
		try {
			
			f = dao.ObterPorId(id, con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return f;
	}

	public void atualizar(Familia fam, Funcionario func) throws Exception {
		FamiliaDAO dao = new FamiliaDAO();
		Connection con = DBUtil.getConnection();
		Familia familia = null;
		try {
			
			familia = dao.ObterPorId(fam.getId(), con);
						
			familia.setEndereco(fam.getEndereco());
			familia.setBairro(fam.getBairro());
			familia.setComplemento(fam.getComplemento());    	
			familia.setNumero(fam.getNumero());
			familia.setCep(fam.getCep());
			familia.setTelefone(fam.getTelefone());
			familia.setFuncionario(func);
		    
			dao.Atualizar(familia, con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
	}
	
	
	
}
