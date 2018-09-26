import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientProtocol {
	// no private variables cause we don't have constructor to initialize them. 
	
	int clientId; 
	// The number of threads are equal with number of PC cores. See Server class. 
	//This number can change without consequences. 
	int numThreads; 
	int numSteps; 
	int myStart;
	int myStop;
	double pi;
	Reply rep = new Reply();
	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
	// shared data structure
	double[] a; 

	public Reply processRequest(Request theInput) throws IOException {
		
		clientId = theInput.getThreadId();
		numThreads = theInput.getNumThreads();
		numSteps = theInput.getNumSteps();
		a = new double[numThreads];
		//Assign 0 to every table's position.
		for (int i = 0; i < numThreads; i++)
			a[i] = 0;
		
		PiThread[] threads = new PiThread[numThreads];

		// thread execution
		for(int i=0;i<numThreads;i++) {
			threads[i] = new PiThread(i,clientId,numThreads,a,numSteps);
			threads[i].start();
		}
		
		// wait for threads to terminate
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
		
		for (int i = 0; i < numThreads; i++) {
			pi += a[i];
		}

		rep.setPiValue(pi);
		return rep;
	}

}
