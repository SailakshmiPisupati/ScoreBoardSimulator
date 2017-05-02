package operands;

public class Memory {
	int base_address;
	public Register base_register;
	int offset_address;
	int value;

	public Memory(String str) throws Exception {
		super();

		if(str.contains("(")){
			String offset = str.substring(0, str.indexOf("("));
			this.offset_address = Integer.parseInt(offset);
			String base = str.substring(str.indexOf("(") + 1, str.indexOf(")"));

			if(Register.isValidRegister(base)){
				this.base_register = new Register(base);
			}else{
				this.base_address = Integer.parseInt(base);
			}
		}else{
			this.base_address = Integer.parseInt(str);
		}
	}

	public int final_address() throws Exception {
		if(this.base_register != null){
			return (int)this.base_register.getValue() + this.offset_address;
		}else{
			return this.base_address + this.offset_address;
		}
	}

	@Override
	public String toString() {
		return offset_address + "(" + base_register + ")";
	}
}
