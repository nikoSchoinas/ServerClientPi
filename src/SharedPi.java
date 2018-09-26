
public class SharedPi {
	private static volatile double piValue;
	
	public SharedPi() {
		piValue = 0;
	}
	
	public synchronized double executeSum(Reply theInput) {
		double repliedPi = theInput.getPiValue();
		piValue += repliedPi;
		return piValue;
	}

	public static double getPiValue() {
		return piValue;
	}
	
	
}
