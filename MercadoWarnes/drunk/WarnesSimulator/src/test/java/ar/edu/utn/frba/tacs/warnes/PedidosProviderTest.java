package ar.edu.utn.frba.tacs.warnes;

import java.io.File;

import ar.edu.utn.frba.tacs.mercadowarnessh.colas.PedidosProvider;
import junit.framework.TestCase;

public class PedidosProviderTest extends TestCase{
	public void testLoad() throws Exception {
		PedidosProvider pp = new PedidosProvider();
		pp.setInputFile(new File("./src/main/webapp/WEB-INF/pedidos.xml"));
		System.out.println(pp.getPedidos());
	}
}
