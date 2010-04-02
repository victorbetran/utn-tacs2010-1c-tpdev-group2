package org.utn.tacs.tp.group2.ejemplo;

import org.junit.Before;
import org.junit.Test;

public class TestDeEjemplo {

	@Before
	private void levantarEscenario() {
		//Este m�todo se ejecuta antes que cualquier Test. Si hay mas de un test se va a ejecutar mas de una vez
		//entonces.
	}
	
	@Test
	private void soyUnTest() {
		//Este es un test donde se va a hacer Asserts y cosas cosmoz�nicas.
	}

	@Test(expected=RuntimeException.class)
	private void soyUnTestQueSoloDaVerdeSiLanzoUnaExcepcion() {
		//S�lo da verde si la excepci�n que se declar� en el annotation salta en la ejecuci�n
		//del c�digo que va ac�.
	}
	
}
