package operands;

public class Memory {
	int baseAddress;
	public Register baseRegister;
	int offsetAddress;
	int value;

	public Memory(String str) throws Exception {
		super();

		if(str.contains("(")){
			String offset = str.substring(0, str.indexOf("("));
			this.offsetAddress = Integer.parseInt(offset);
			String base = str.substring(str.indexOf("(") + 1, str.indexOf(")"));

			if(Register.isValidRegister(base)){
				this.baseRegister = new Register(base);
			}else{
				this.baseAddress = Integer.parseInt(base);
			}
		}else{
			this.baseAddress = Integer.parseInt(str);
		}
	}

	public int calculateOffset() throws Exception {
		System.out.println("base resigiter "+baseRegister.getValue());
		if(this.baseRegister != null){
			return (int)this.baseRegister.getValue() + this.offsetAddress;
		}else{
			return this.baseAddress + this.offsetAddress;
		}
	}

	@Override
	public String toString() {
		return offsetAddress + "(" + baseRegister + ")";
	}
}
