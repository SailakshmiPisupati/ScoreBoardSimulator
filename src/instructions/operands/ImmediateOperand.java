package instructions.operands;

public class ImmediateOperand extends Operand{
	public double value;
	
	public ImmediateOperand(String raw_value) {
		super();
		this.value = Float.parseFloat(raw_value);
	}
	
	@Override
	public String toString() {
		return Double.toString(value);
	}
}
