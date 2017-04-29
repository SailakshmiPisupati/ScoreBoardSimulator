package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.TreeMap;

import opcodes.*;
import operands.*;
import simulator.ScoreBoard;

public class InstructionParser {
	private static BufferedReader bufferedReader;

	public static void readFile(String filename) throws Exception {
		try{
			String line;	
			bufferedReader = new BufferedReader(new FileReader(filename));
			int count = 0;
			while((line = bufferedReader.readLine()) != null){
				String label = null;
				
				if(line.contains(":")){	
					String[] tokens = line.split(":");					
					label = tokens[0];
					line = tokens[1];
				}
				
				String[] tokens = line.trim().split("[\\s]", 2);
				String opcode = tokens[0].trim().toUpperCase().replace(".", "");		
				String[] operands = new String[0];
				
				if(tokens.length > 1){
					operands = tokens[1].replaceAll("\\s+", "").trim().split(",");	
					//System.out.println("Opcode "+opcode+" operands "+operands[0]+" "+operands[1]);
				}	
				Instruction instruction = createInstruction(opcode, operands);
				
				ScoreBoard.instructions.put(count++, instruction);
				
				if(label != null){
					ScoreBoard.label_map.put(label, count);			
				}
			}
		}catch(IOException e){
			System.out.println("Exception "+ e);
		}
	}
	
	private static Instruction createInstruction(String opcode, String[] operands) throws Exception {
		Instruction inst = null;
		Immediates immediate_operand = null;
		Memory memory_operand = null;
		Register register_operand1 = null, register_operand2 = null, register_operand3 = null;
		
		switch(opcode){
		case "LW": // Load Word
			register_operand1 = new Register(operands[0]);
			memory_operand = new Memory(operands[1]);
			inst = new LW(register_operand1, memory_operand);
			break;
		case "LD": // Load Double
			register_operand1 = new Register(operands[0]);
			memory_operand = new Memory(operands[1]);
			inst = new LD(register_operand1, memory_operand);
			break;
		case "LI": // Load Immediate
			register_operand1 = new Register(operands[0]);
			immediate_operand = new Immediates(operands[1]);
			inst = new LI(register_operand1, immediate_operand);
			break;
		case "LUI": // Load upper Immediate
			register_operand1 = new Register(operands[0]);
			immediate_operand = new Immediates(operands[1]);
			inst = new LUI(register_operand1, immediate_operand);
			break;
		case "SW": // Store Word
			register_operand1 = new Register(operands[1]);
			memory_operand = new Memory(operands[0]);
			inst = new SW(register_operand1, memory_operand);
			break;
		case "SD":
			register_operand1 = new Register(operands[1]);
			memory_operand = new Memory(operands[0]);
			inst = new SD(register_operand1, memory_operand);
			break;
		case "DADD":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			register_operand3 = new Register(operands[2]);
			inst = new DADD(register_operand1, register_operand2, register_operand3);
			break;
		case "DADDI":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			immediate_operand = new Immediates(operands[2]);
			inst = new DADDI(register_operand1, register_operand2, immediate_operand);
			break;
		case "DSUB":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			register_operand3 = new Register(operands[2]);
			inst = new DSUB(register_operand1, register_operand2, register_operand3);
			break;
		case "DSUBI":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			immediate_operand = new Immediates(operands[2]);
			inst = new DSUBI(register_operand1, register_operand2, immediate_operand);
			break;
		case "AND":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			register_operand3 = new Register(operands[2]);
			inst = new AND(register_operand1, register_operand2, register_operand3);
			break;
		case "ANDI":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			immediate_operand = new Immediates(operands[2]);
			inst = new ANDI(register_operand1, register_operand2, immediate_operand);
			break;
		case "OR":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			register_operand3 = new Register(operands[2]);
			inst = new OR(register_operand1, register_operand2, register_operand3);
			break;
		case "ORI":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			immediate_operand = new Immediates(operands[2]);
			inst = new ORI(register_operand1, register_operand2, immediate_operand);
			break;
		case "ADDD":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			register_operand3 = new Register(operands[2]);
			inst = new ADDD(register_operand1, register_operand2, register_operand3);
			break;
		case "MULD":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			register_operand3 = new Register(operands[2]);
			inst = new MULD(register_operand1, register_operand2, register_operand3);
			break;
		case "DIVD":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			register_operand3 = new Register(operands[2]);
			inst = new DIVD(register_operand1, register_operand2, register_operand3);
			break;
		case "SUBD":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			register_operand3 = new Register(operands[2]);
			inst = new SUBD(register_operand1, register_operand2, register_operand3);
			break;
		case "J":
			inst = new J(operands[0]);
			break;
		case "BEQ":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			inst = new BEQ(register_operand1, register_operand2, operands[2]);
			break;
		case "BNE":
			register_operand1 = new Register(operands[0]);
			register_operand2 = new Register(operands[1]);
			inst = new BNE(register_operand1, register_operand2, operands[2]);
			break;
		case "HLT":
			inst = new HLT();
			break;
		default:
			throw new Error("Invalid Opcode !");
		}
		
		return inst;
	}
}
