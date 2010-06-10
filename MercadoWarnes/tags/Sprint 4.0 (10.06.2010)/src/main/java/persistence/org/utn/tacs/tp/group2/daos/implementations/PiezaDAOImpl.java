package org.utn.tacs.tp.group2.daos.implementations;

import java.util.List;

import org.utn.tacs.tp.group2.daos.exceptions.PiezaInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;


public class PiezaDAOImpl extends PiezaDAO{
	
	@Override
	public List<Pieza> findByAuto(final Auto auto) {
		return getQueryHandler().setBody("from Pieza as pieza inner join fetch pieza.autoOrigen WHERE pieza.autoOrigen = :aut")
								.addParameter("aut", auto)
								.getResults();
	}

	@Override
	public List<Pieza> findByAutoId(Long autoId) {
		return getQueryHandler().setBody("from Pieza as pieza inner join fetch pieza.autoOrigen WHERE pieza.autoOrigen.id = :autId")
								.addParameter("autId", autoId)
								.getResults();
	}
	
	@Override
	public List<Pieza> findByCategoria(final String categoria) {
		return getQueryHandler().setBody("FROM Pieza WHERE categoria = :cat")
								.addParameter("cat", categoria)
								.getResults();
	}
	
	@Override
	public Pieza findByCodigo(String codigo) {			
		return getQueryHandler().setBody("FROM Pieza WHERE codigo = :cod")
								.addParameter("cod", codigo)
								.getResult();
	}

	@Override
	public List<Pieza> findByEstado(EstadoPieza estado) {
		return getQueryHandler().setBody("from Pieza as pieza WHERE pieza.estado = :est")
								.addParameter("est", estado)
								.getResults();
	}
	
	@Override
	public List<Pieza> findByEstadoAndAutoId(EstadoPieza estado, Long autoId) {
		return getQueryHandler().setBody("from Pieza as pieza inner join fetch pieza.autoOrigen WHERE pieza.autoOrigen.id = :autId AND pieza.estado = :est")
				.addParameter("est", estado)
				.addParameter("autId", autoId)
				.getResults();
	}

	@Override
	protected RuntimeException getNotFoundObjectException(Long id) {
		return new PiezaInexistenteException("No existe una pieza con el ID: '" + id + "'");
	}

//	@Override
//	public List<Pieza> findByEstadoAndAuto(String estado,Auto auto) {
//		return getQueryHandler().setBody("FROM Pieza WHERE estado.tipoEstado = :est and auto = :aut")
//								.addParameter("est", estado)
//								.addParameter("aut", auto)
//								.getResults();
//	}

//	@Override
//	public List<Pieza> findByEstadoAndCategoria(String estado,String categoria) {
//		return getQueryHandler().setBody("FROM Pieza WHERE estado.tipoEstado = :est and categoria = :cat")
//								.addParameter("est", estado)
//								.addParameter("cat", categoria)
//								.getResults();
//	}
	
}
