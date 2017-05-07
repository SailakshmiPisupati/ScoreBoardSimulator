package operands;

public class Immediates extends Operand{
	public double value;
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Immediates(String raw_value) {
		super();
		this.value = Float.parseFloat(raw_value);
	}
	
	@Override
	public String toString() {
		return Double.toString(value);
	}
}
