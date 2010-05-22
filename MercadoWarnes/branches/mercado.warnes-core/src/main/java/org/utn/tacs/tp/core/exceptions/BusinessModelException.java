package org.utn.tacs.tp.core.exceptions;
/**
 * Excepcion de la cual heredaran todas las excepciones vinculadas a la lógica de negocio.
 */
public class BusinessModelException extends RuntimeException{

	private static final long serialVersionUID = -6411954839605806617L;

	public BusinessModelException(String message) {
		super(message);	
	}
}
