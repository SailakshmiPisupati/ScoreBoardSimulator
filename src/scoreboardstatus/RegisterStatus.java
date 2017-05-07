package scoreboardstatus;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import operands.Register;

public class RegisterStatus {
	static HashMap<Register, Double> registerValues = new HashMap<Register, Double>();
	public static HashMap<String,Boolean> destinationRegisters= new HashMap<String,Boolean>();
	static double[] intRegisters = new double[32];
	static double[] fpRegisters = new double[32];

	public static void initializeRegisters() {
			
		for(int i=1;i<=32;i++){
			destinationRegisters.put("R"+i, false);
		}
		for(int i=1;i<=32;i++){
			destinationRegisters.put("F"+i, false);
		}
	}
	
	public static void setDestinationRegisterBusy(String register,boolean value) {
		destinationRegisters.put(register, value);
	}
	
	public static boolean checkIfRegisterIsBusy(String register){
		return destinationRegisters.get(register);
	}
	

	public static double read(Register registerOperand) throws Exception {
		if(registerOperand.floating_point){
			return fpRegisters[registerOperand.index-1];
		}else{
			return intRegisters[registerOperand.index-1];
		}
	}

	public static void write(Register registerOperand, Double value) throws Exception {
		registerValues.put(registerOperand, value);
	}
	
	public static void writeValuesToRegisters() throws Exception {
		Iterator<Entry<Register, Double>> it = registerValues.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Register, Double> pair = (Map.Entry<Register, Double>)it.next();
	        Register registerOperand = pair.getKey();
	        Double value = pair.getValue();
	        
	        if(registerOperand.floating_point){
				fpRegisters[registerOperand.index-1] = value;
			}else{
				intRegisters[registerOperand.index-1] = value;
			}
	        it.remove();
	    }
	}
}
