package functionunits;

public class WriteUnit {
	
	public static boolean isWriteBusy = false;

	public static boolean isWriteBusy() {
		return isWriteBusy;
	}

	public static void setWriteBusy(boolean isWriteBusy) {
		WriteUnit.isWriteBusy = isWriteBusy;
	}
	
	
	public static void execute(int count){
		setWriteBusy(true);
		
	}

}
