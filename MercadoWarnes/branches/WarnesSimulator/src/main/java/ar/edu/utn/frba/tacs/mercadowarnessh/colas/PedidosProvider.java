package ar.edu.utn.frba.tacs.mercadowarnessh.colas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import ar.edu.utn.frba.tacs.warnes.Cliente;
import ar.edu.utn.frba.tacs.warnes.Item;
import ar.edu.utn.frba.tacs.warnes.Pedido;

public class PedidosProvider {
	private File inputFile = null;
	
	@SuppressWarnings("unchecked")
	public List<Pedido> getPedidos(){
		XStream stream = new XStream();
		stream.alias("pedido", Pedido.class);
		stream.alias("cliente", Cliente.class);
		stream.alias("cliente", Cliente.class);
		stream.alias("item", Item.class);
		stream.alias("pedidos",List.class);
		
		try {
			return (List<Pedido>) stream.fromXML(new FileInputStream(inputFile));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
}
