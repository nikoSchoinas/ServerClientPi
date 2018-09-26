
public class ServerProtocol {
	private SharedPi sharedPi;

	public ServerProtocol(SharedPi sharedPi) {
		this.sharedPi = sharedPi;
	}
	public double processReply(Reply theInput) {
		double rep = sharedPi.executeSum(theInput);
		return rep;
	}
}

