package br.com.homecare.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import br.com.homecare.dao.AtendimentoDAO;
import br.com.homecare.dao.CidadaoDAO;
import br.com.homecare.dao.DBUtil;
import br.com.homecare.model.Atendimento;
import br.com.homecare.model.Cidadao;

public class AtendimentoService implements Serializable {

	private static final long serialVersionUID = -4230117674936820744L;
	
	public void cadastrar(Atendimento a) throws ServiceException, Exception {
		AtendimentoDAO dao = new AtendimentoDAO();
		Connection con = DBUtil.getConnection();
		try {
			DBUtil.beginTransaction(con);
			dao.cadastrar(a,con);
			DBUtil.commit(con);
		} catch (Exception e) {
			DBUtil.rollback(con);
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
	}
	
	
	public List<Atendimento> consultar(Atendimento atendimentoFiltro) throws ServiceException, Exception {		 
		Connection con = DBUtil.getConnection();
		List<Atendimento> lista = null;
		try {
			
			AtendimentoDAO dao = new AtendimentoDAO();
			lista = dao.consultar(atendimentoFiltro, con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return lista;
	}


	public Atendimento ObterPorId(int id) throws Exception {
		AtendimentoDAO dao = new AtendimentoDAO();
		Connection con = DBUtil.getConnection();
		Atendimento a = null;
		try {
			
			a = dao.ObterPorId(id, con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return a;
	}


	public void atualizar(Atendimento a) throws ServiceException, Exception {
		AtendimentoDAO dao = new AtendimentoDAO();
		Connection con = DBUtil.getConnection();

		try {
			DBUtil.beginTransaction(con);
			
			Atendimento atendimento = dao.ObterPorId(a.getId(), con);
			
			atendimento.setDataUltimaConsulta(a.getDataUltimaConsulta());
			atendimento.setExameEscarro(a.getExameEscarro());
			atendimento.setObservacoes(a.getObservacoes());
			atendimento.setGlicose(a.getGlicose());
			atendimento.setPressao(a.getPressao());
			atendimento.setPeso(a.getPeso());
			
			atendimento.setExameEscarro(a.getExameEscarro());
			atendimento.setFuncionario(a.getFuncionario());
			atendimento.setReacoesIndesejadas(a.getReacoesIndesejadas());
			atendimento.setTomaMedicacao(a.getTomaMedicacao());
			atendimento.setTratamentoSupervisionado(a.getTratamentoSupervisionado());
		
			dao.atualizar(atendimento,con);
			
			DBUtil.commit(con);
		} catch (Exception e) {
			DBUtil.rollback(con);
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
	}
	
	
	public void excluir(Atendimento a) throws ServiceException, Exception {
		AtendimentoDAO dao = new AtendimentoDAO();
		Connection con = DBUtil.getConnection();

		try {
			DBUtil.beginTransaction(con);
			dao.excluir(a.getId(), con);
			DBUtil.commit(con);
		} catch (Exception e) {
			DBUtil.rollback(con);
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
	}

}
