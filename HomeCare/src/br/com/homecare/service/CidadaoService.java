package br.com.homecare.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import br.com.homecare.dao.AtendimentoDAO;
import br.com.homecare.dao.CidadaoDAO;
import br.com.homecare.dao.DBUtil;
import br.com.homecare.model.Atendimento;
import br.com.homecare.model.Cidadao;

public class CidadaoService implements Serializable {

	private static final long serialVersionUID = 4833077585041107955L;
	
	

	public void cadastrar(Cidadao c) throws ServiceException, Exception {
		CidadaoDAO dao = new CidadaoDAO();
		Connection con = DBUtil.getConnection();
		try {
			DBUtil.beginTransaction(con);
		
			
			if(c.getCpf() != null && !"".equals(c.getCpf())){
				if(dao.verificarCpf(c.getCpf(), con)) {
					throw new ServiceException("CPF existente");
				}
			}
			
			if(c.getDnv()!= null && !"".equals(c.getDnv())){
				if(dao.verificarDnv(c.getDnv(), con)) {
					throw new ServiceException("DNV existente");
				}
			}
			
			if(c.getCns()!= null && !"".equals(c.getCns())){
				if(dao.verificarCns(c.getDnv(), con)) {
					throw new ServiceException("CNS existente");
				}
			}
			
			if(c.getNis()!= null && !"".equals(c.getNis())){
				if(dao.verificarNis(c.getNis(), con)) {
					throw new ServiceException("NIS existente");
				}
			}
			
			dao.cadastrar(c,con);
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
	
	public Cidadao ObterPorId(int id) throws Exception {
		CidadaoDAO dao = new CidadaoDAO();
		Connection con = DBUtil.getConnection();
		Cidadao c = null;
		try {
			
			c = dao.ObterPorId(id, con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return c;
	}
	
	public List<Cidadao> consultar(Cidadao cidadaoFiltro) throws ServiceException, Exception {		 
		Connection con = DBUtil.getConnection();
		List<Cidadao> lista = null;
		try {
			
			CidadaoDAO dao = new CidadaoDAO();
			lista = dao.consultar(cidadaoFiltro, con);
		
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		return lista;
	}

	

	public void atualizar(Cidadao c) throws Exception {
		
		CidadaoDAO dao = new CidadaoDAO();
		Connection con = DBUtil.getConnection();
		Cidadao cidadao = null;
		try {
			
			DBUtil.beginTransaction(con);
			
			cidadao = dao.ObterPorId(c.getId(), con);
			
			
			if(c.getCpf() != null && !"".equals(c.getCpf())){
				if(dao.verificarCpf(c.getCpf(), cidadao.getCpf(), con)) {
					throw new ServiceException("CPF existente");
				}
			}
			
			if(c.getDnv()!= null && !"".equals(c.getDnv())){
				if(dao.verificarDnv(c.getDnv(), cidadao.getDnv(), con)) {
					throw new ServiceException("DNV existente");
				}
			}
			
			if(c.getCns()!= null && !"".equals(c.getCns())){
				if(dao.verificarCns(c.getDnv(), cidadao.getDnv(), con)) {
					throw new ServiceException("CNS existente");
				}
			}
			
			if(c.getNis()!= null && !"".equals(c.getNis())){
				if(dao.verificarNis(c.getNis(), cidadao.getNis(), con)) {
					throw new ServiceException("NIS existente");
				}
			}
			
			cidadao.setNome(c.getNome());
	    	cidadao.setDataNascimento(c.getDataNascimento());		
	    	cidadao.setSexo(c.getSexo());
			cidadao.setRacaCor(c.getRacaCor());
			cidadao.setSituacaoFamiliar(c.getSituacaoFamiliar());
			cidadao.setFrequentaEscola(c.getFrequentaEscola());
			cidadao.setNaturezaEscola(c.getNaturezaEscola());	
		    cidadao.setPossuiPlanoSaude(c.getPossuiPlanoSaude());
		    
		    cidadao.setFumante(c.getFumante());
		    cidadao.setPresenteDuranteVisita(c.getPresenteDuranteVisita());
		    cidadao.setEscolaridade(c.getEscolaridade());
		    cidadao.setOcupacao(c.getOcupacao());
		    
		    cidadao.setCpf(c.getCpf());
		    cidadao.setDnv(c.getDnv());
		   
		    cidadao.setNacionalidade(c.getNacionalidade());
		    cidadao.setNomeMae(c.getNomeMae());
		    cidadao.setNomePai(c.getNomePai());
		    cidadao.setMunicipio(c.getMunicipio());
		    
		    cidadao.setUf(c.getUf());
		    cidadao.setPaisOrigem(c.getPaisOrigem());
		    cidadao.setDataEntradaBrasil(c.getDataEntradaBrasil());
		    cidadao.setCns(c.getCns());
		    
		    cidadao.setBolsaFamilia(c.getBolsaFamilia());
		    cidadao.setCartaoFamiliaCarioca(c.getCartaoFamiliaCarioca());
		    cidadao.setNis(c.getNis());
		    cidadao.setPeso(c.getPeso());
		    cidadao.setComprimento(c.getComprimento());
		    cidadao.setCefalico(c.getCefalico());
		    
		    cidadao.setDataTestePezinho(c.getDataTestePezinho());
		    
		    cidadao.setObservacoes(c.getObservacoes());
		    cidadao.setParto(c.getParto());
		    cidadao.setFamilia(c.getFamilia());
		
			dao.Atualizar(cidadao, con);
		
			DBUtil.commit(con);
		} catch (Exception e) {
			DBUtil.rollback(con);
			throw new Exception(e);
		} finally {
			DBUtil.closeConnection(con);
		}
		
	}
	
		public List<Cidadao> ListarTodos() throws ServiceException, Exception {		 
			Connection con = DBUtil.getConnection();
			List<Cidadao> lista = null;
			try {
				
				lista = CidadaoDAO.ListarTodos(con);
			
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.closeConnection(con);
			}
			return lista;
		}
	
	}
