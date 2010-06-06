package org.utn.tacs.tp.group2.service.definition;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;

/**
 * Puesta en escena como ejemplo, para ver como se usa cxf.
 * @author ale
 *
 */
@WebService
public interface Speaker {

	public String talkAsMaradona();

	public Integer mundiales(@WebParam(name="pais")String pais);
	
	public PiezaDTO damePieza();
	
}
