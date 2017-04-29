package operands;

import scoreboardstatus.RegisterStatus;

public class Register extends Operand {
	public boolean floating_point;
	public int index;
	
	public Register(String string) throws Exception {
		super();
		String register_name = string.trim();
		if(isValidRegister(register_name)){
			this.floating_point = (register_name.charAt(0) == 'R') ? false : true;
			this.index = getIndex(register_name);			
		}else{
			throw new Error("Invalid Register name - " + register_name);
		}
	}

	public double getValue() throws Exception {	
		return RegisterStatus.read(this);
	}
	public void setValue(double value) throws Exception {	
		RegisterStatus.write(this, value);
	}
//	public boolean isBeingRead() throws Exception {	
//		return RegisterManager.getReadStatus(this);
//	}	
	public boolean isBeingWritten() throws Exception {	
		return RegisterStatus.getWriteStatus(this);
	}	
//	public void setReadStatus(boolean value) throws Exception {	
//		RegisterManager.setReadStatus(this, value);
//	}	
	public void setWriteStatus(boolean value) throws Exception {	
		RegisterStatus.setWriteStatus(this, value);
	}	
	
	private static int getIndex(String register_name) throws Exception {	
		return Integer.parseInt(register_name.substring(1,register_name.length()));
	}

	public static boolean isValidRegister(String register_name) throws Exception {
		if(register_name.matches("[F|R]\\d+")){
			int index = getIndex(register_name);

			if(0 <= index && index < 32){
				return true;
			}else{
				return false;				
			}
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return (floating_point == true ? "F" : "R") + "" + index;
	}

}
