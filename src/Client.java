import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	// final variables for HOST and PORT of connection
	private static final String HOST = "localhost";
	private static final int PORT = 1234;

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		// make the connection
		Socket socketConn = new Socket(HOST, PORT);

		// Streams that read or write object. In this case object can be Reply or
		// Request.
		ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConn.getOutputStream());
		ObjectInputStream clientInputStream = new ObjectInputStream(socketConn.getInputStream());

		// create the ClientProtocol that will handle the Request.
		ClientProtocol app = new ClientProtocol();

		Request req; //Request from the server
		Reply rep = new Reply();
		req = (Request) clientInputStream.readObject();
		rep = app.processRequest(req);
		clientOutputStream.writeObject(rep);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		clientOutputStream.close();
		clientInputStream.close();

		socketConn.close();

	}

}
