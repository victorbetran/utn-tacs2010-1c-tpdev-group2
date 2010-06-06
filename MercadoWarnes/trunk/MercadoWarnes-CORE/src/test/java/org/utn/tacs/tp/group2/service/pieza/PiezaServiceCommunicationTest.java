package org.utn.tacs.tp.group2.service.pieza;

import org.junit.Test;
import org.restlet.Client;
import org.restlet.data.Protocol;

//TODO IMPLEMENTAR!!
public class PiezaServiceCommunicationTest {

	/*


Client client = new Client(Protocol.HTTP);
Request request = new Request(Method.GET, resourceRef);
Response response = client.handle(request);

assert response.getStatus().getCode() == 200;
assert response.isEntityAvailable();
assert response.getEntity().getMediaType().equals(MediaType.TEXT_HTML);

// Representation.getText() empties the InputStream, so we need to store the text in a variable
String text = response.getEntity().getText();
assert text.contains("search string");
assert text.contains("another search string");

	 */
	@Test
	public void blah(){
		Client client = new Client(Protocol.HTTP);
	}
	
}
