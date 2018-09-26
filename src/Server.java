import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	private static final int PORT = 1234;
	private static SharedPi sharedPi = new SharedPi();
	private static int numThreads = Runtime.getRuntime().availableProcessors(); //takes the number of PC cores. 
	private static int numSteps = 100000000; 
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ServerSocket socketConn = new ServerSocket(PORT);
		ServerThread threads[] = new ServerThread[numThreads];
		
		//time before procedure starts
		long start = System.currentTimeMillis();
		
		for(int i=0; i<numThreads;i++) { //need to change
			System.out.println("Server is waiting...");
			Socket pipe = socketConn.accept();
			System.out.println("Received request from" + pipe.getInetAddress());
			threads[i] = new ServerThread(pipe,sharedPi,i,numThreads,numSteps);
			threads[i].start();
		}
		for(int i=0; i<numThreads;i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//serverInputStream.close();
		//serverOutputStream.close();
		//pipe.close();
		socketConn.close();
		
		// get current time and calculate elapsed time
		long elapsedTimeMillis = System.currentTimeMillis() - start;
		System.out.println("Elapsed time in ms:" + elapsedTimeMillis);
        System.out.printf("Number of steps: %d steps\n", numSteps);
        System.out.printf("Computed Pi: %22.20f\n" , SharedPi.getPiValue());
        System.out.printf("Difference between computed Pi and Math.PI = %22.20f\n", Math.abs(SharedPi.getPiValue() - Math.PI));
	}

}
