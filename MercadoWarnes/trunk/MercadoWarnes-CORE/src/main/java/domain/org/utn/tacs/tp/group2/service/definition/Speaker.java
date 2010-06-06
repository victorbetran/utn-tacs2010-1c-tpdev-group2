package org.utn.tacs.tp.group2.service.definition;

import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;

/**
 * Puesta en escena como ejemplo, para ver como se usa cxf.
 * @author ale
 *
 */
public interface Speaker {

	public String talkAsMaradona();

	public Integer mundiales(String pais);
	
	public PiezaDTO damePieza();
	
}
