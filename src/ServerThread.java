import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket pipe;
	private ObjectInputStream serverInputStream;
	private ObjectOutputStream serverOutputStream;
	private SharedPi sharedPi;
	private int numThreads;
	private int numSteps;
	private int threadId;

	public ServerThread(Socket socket, SharedPi sharedPi, int threadId, int numThreads, int numSteps) {
		pipe = socket;
		this.numThreads = numThreads;
		this.numSteps = numSteps;
		this.threadId = threadId;

		try {
			serverInputStream = new ObjectInputStream(pipe.getInputStream());
			serverOutputStream = new ObjectOutputStream(pipe.getOutputStream());
			this.sharedPi = sharedPi;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		Reply rep = new Reply();
		Request req;

		try {

			req = new Request(threadId, numThreads, numSteps);
			//send the request
			serverOutputStream.writeObject(req);
			// serverOutputStream.reset();
			
			//receive the reply.
			rep = (Reply) serverInputStream.readObject();
			
			//process the reply.
			ServerProtocol serverProt = new ServerProtocol(sharedPi);
			serverProt.processReply(rep);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
