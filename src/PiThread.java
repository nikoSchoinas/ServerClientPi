
public class PiThread extends Thread {
	private int clientId;
    private int numThreads;
    private double [] table;
    private int numSteps;
    private int myStart;
    private int myStop;
	private int threadId;
    
    
    public PiThread(int threadId, int clientId, int numThreads, double[] table, int numSteps) {
		this.clientId = clientId;  
		this.numThreads = numThreads;
		this.table = table;
		this.numSteps = numSteps;
		this.threadId = threadId;
		myStart = clientId * (numSteps / numThreads);
        myStop = myStart + (numSteps / numThreads);
        if (clientId == (numThreads - 1)) myStop = numSteps;
	}



	public void run() {
		System.out.println("Hello from thread "+ threadId+ " of client No " + clientId+"\n");
    	double step = 1.0 / (double)numSteps;
    	double sum = 0;
        /* do computation */
        for (long i = myStart; i < myStop; ++i) {
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);
        }
        double pi = sum * step;
        table[clientId] = pi;
    }
    
}
