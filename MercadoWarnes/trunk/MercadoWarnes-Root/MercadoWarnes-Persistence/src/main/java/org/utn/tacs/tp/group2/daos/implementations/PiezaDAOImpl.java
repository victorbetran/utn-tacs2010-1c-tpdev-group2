package org.utn.tacs.tp.group2.daos.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;


public class PiezaDAOImpl extends PiezaDAO{

	private Pieza pieza;
	@Override
	public List<Pieza> findByAuto(final Auto auto) {
		final List<Pieza> resultado = new ArrayList<Pieza>();
		doExecute(new Command() { 
			
			@SuppressWarnings("unchecked")
			public void execute(Session session) {
				Query q = session.createQuery("from Pieza as pieza inner join fetch pieza.autoOrigen WHERE pieza.autoOrigen = :aut");
				q.setParameter("aut", auto);
				resultado.addAll(q.list());
			}
		});
		
		return resultado;
	}

	@Override
	public List<Pieza> findByCategoria(final String categoria) {
		
		final List<Pieza> resultado = new ArrayList<Pieza>();
		doExecute(new Command() { 
			
			@SuppressWarnings("unchecked")
			public void execute(Session session) {
				Query q = session.createQuery("FROM Pieza WHERE categoria = :cat"  );
				q.setParameter("cat", categoria);
				resultado.addAll(q.list());
		
			}
		});
		
		return resultado;
	}

	@Override
	public Pieza findByCodigo(final String codigo) {			
			doExecute(new Command() { 
			
			public void execute(Session session) {
				Query q = session.createQuery("FROM Pieza WHERE codigo = :cod"  );
				q.setParameter("cod", codigo);
				pieza=(Pieza)q.uniqueResult();
			}
		});
		return pieza;
	}

	@Override
	public List<Pieza> findByEstado(final EstadoPieza estado) {
		final List<Pieza> resultado = new ArrayList<Pieza>();
			doExecute(new Command() { 
			
			@SuppressWarnings("unchecked")
			public void execute(Session session) {
				Query q = session.createQuery("from Pieza as pieza inner join fetch pieza.estado WHERE pieza.estado = :est");
				q.setParameter("est", estado);
				resultado.addAll(q.list());
			}
		});
			return resultado;
	}

	@Override
	public List<Pieza> findByEstadoAndAuto(final String estado,final Auto auto) {
		final List<Pieza> resultado = new ArrayList<Pieza>();
		doExecute(new Command() { 
		
		@SuppressWarnings("unchecked")
		public void execute(Session session) {
			Query q = session.createQuery("FROM Pieza WHERE estado.tipoEstado = :est and auto=:aut"  );
			q.setParameter("est", estado);
			q.setParameter("aut", auto);
			resultado.addAll(q.list());
		}
	});
		return resultado;
	}

	@Override
	public List<Pieza> findByEstadoAndCategoria(final String estado,final String categoria) {
		final List<Pieza> resultado = new ArrayList<Pieza>();
		doExecute(new Command() { 
		
		@SuppressWarnings("unchecked")
		public void execute(Session session) {
			Query q = session.createQuery("FROM Pieza WHERE estado.tipoEstado = :est and categoria=:cat"  );
			q.setParameter("est", estado);
			q.setParameter("cat", categoria);
			resultado.addAll(q.list());
		}
	});
		return resultado;
	}
	
	
	@Override
	public Pieza findByID(final Long id) {
		doExecute(new Command(){

			public void execute(Session session) {
				pieza = (Pieza) session.load(Pieza.class, id);
			}
			
		});
		return pieza;
	}

	@Override
	public void save(final Pieza pieza) {
		doExecute(new Command() {
			
			public void execute(Session session) {
				session.save(pieza);
			}
			
		});
	}	

	@Override
	public void remove(final Pieza pieza) {
		doExecute(new Command() {
			
			public void execute(Session session) {
				session.delete(pieza);
			}

		});
		
	}

	private Boolean value = false;
	@Override
	public Boolean isPersisted(final Pieza pieza) {
		doExecute(new Command() {
			
			public void execute(Session session) {
				value = session.contains(pieza);
			}

		});
		return value;
	}

	
	

}
