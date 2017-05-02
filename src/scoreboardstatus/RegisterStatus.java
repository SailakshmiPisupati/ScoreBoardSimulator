package scoreboardstatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import operands.Register;
import pipelinestages.Issue;

public class RegisterStatus {
	static HashMap<Register, Double> register_cache = new HashMap<Register, Double>();
	public static HashMap<String,Boolean> destinationRegisters= new HashMap<String,Boolean>();
	static double[] integer_registers = new double[32];
	static double[] floating_point_registers = new double[32];

//	static boolean[] integer_register_reading = new boolean[32];
	static boolean[] integer_register_writing = new boolean[32];
//	static boolean[] floating_point_register_reading = new boolean[32];
	static boolean[] floating_point_register_writing = new boolean[32];
	
	public static void initializeRegisters() {
			
			for(int i=1;i<=32;i++){
				destinationRegisters.put("R"+i, false);
		}
		for(int i=1;i<=32;i++){
			destinationRegisters.put("F"+i, false);
		}
		System.out.println(destinationRegisters);
	}
	
	public static void setDestinationRegisterBusy(String register,boolean value) {
		System.out.println("Setting register "+register+ " as "+value);
		destinationRegisters.put(register, value);
	}
	
	public static boolean checkIfRegisterIsBusy(String register){
		System.out.println("Register "+register+" is "+destinationRegisters.get(register));
		return destinationRegisters.get(register);
	}
	
	
	
	public static boolean getWriteStatus(Register register_operand) throws Exception {
		if(register_operand.floating_point)
			return integer_register_writing[register_operand.index-1];
		else
			return floating_point_register_writing[register_operand.index-1];
	}

//	public static boolean getReadStatus(Register register_operand) throws Exception {
//		if(register_operand.floating_point)
//			return integer_register_reading[register_operand.index-1];
//		else
//			return floating_point_register_reading[register_operand.index-1];			
//	}

	public static void setWriteStatus(Register register_operand, boolean value) throws Exception {
		if(register_operand.floating_point)
			integer_register_writing[register_operand.index-1] = value;
		else
			floating_point_register_writing[register_operand.index-1] = value;
	}

//	public static void setReadStatus(Register register_operand, boolean value) throws Exception {
//		if(register_operand.floating_point)
//			integer_register_reading[register_operand.index-1] = value;
//		else
//			floating_point_register_reading[register_operand.index-1] = value;			
//	}

	public static double read(Register register_operand) throws Exception {
		if(register_operand.floating_point){
			return floating_point_registers[register_operand.index-1];
		}else{
			return integer_registers[register_operand.index-1];
		}
	}

	public static void write(Register register_operand, Double value) throws Exception {
		register_cache.put(register_operand, value);
	}
	
	public static void push_cache_to_registers() throws Exception {
		Iterator<Entry<Register, Double>> it = register_cache.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Register, Double> pair = (Map.Entry<Register, Double>)it.next();
	        Register register_operand = pair.getKey();
	        Double value = pair.getValue();

//	        MIPS.print("Write to register: " + (register_operand.index-1) + " : " + value);

	        if(register_operand.floating_point){
				floating_point_registers[register_operand.index-1] = value;
			}else{
				integer_registers[register_operand.index-1] = value;
			}
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
	public static void debug() throws Exception {
		System.out.println("Integer Registers - \n" + Arrays.toString(RegisterStatus.integer_registers));
		System.out.println("\nFloating Point Registers - \n" + Arrays.toString(RegisterStatus.floating_point_registers));
	}

	
}
