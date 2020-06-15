package mips.converter.instructions;

import mips.converter.memory.Memory;
import mips.converter.memory.MemoryBlock;
import mips.converter.register.Register;
import mips.converter.register.RegisterMain;

public class BinaryUtils {

    public static String processInstruction(String instruction) throws Exception {
        try {
            String[] bits = instruction.replace("$", "").replace("(", " ").split(" ", -1);
            String opcode = bits[0];
            String instructionType = InstructionHelpers.getRegisterType(opcode);
            String[] operands = instruction.replace(opcode, "").replace(" ", "").split(",");

            if (opcode.equals("lw") || opcode.equals("sw")){
                bits[0] = bits[0].replace(" ", "");
                bits[1] = bits[1].replace(")", "");
                bits[1] = bits[1].replace(",", "");
                Memory.setIndexAddress(Opcode.MAP_OPCODE.get(bits[0])+RegisterMain.getRegisterBinary(bits[1]));
            }

            String binary = getBinary(instructionType, opcode, operands);

            Register temp = null; Register temp2 = null;
            Register updated = null; long result = 0; long resultLo = 0; long resultHi = 0;
            System.out.println(opcode);
            switch(opcode){
                case "add":
                    updated = RegisterMain.getRegister("s0");
                    temp = RegisterMain.getRegister("s1");
                    temp2 = RegisterMain.getRegister("s2");
                    result = temp.getBaseValue() + temp2.getBaseValue();
                    RegisterMain.updateRegister(updated.getName(), result);
                    break;
                case "sub":
                    updated = RegisterMain.getRegister("s0");
                    temp = RegisterMain.getRegister("s1");
                    temp2 = RegisterMain.getRegister("s2");
                    result = temp.getBaseValue() - temp.getBaseValue();
                    RegisterMain.updateRegister(updated.getName(), result);
                    break;
                case "mult":
                    temp = RegisterMain.getRegister("s0");
                    temp2 = RegisterMain.getRegister("s1");
                    if ((temp.getBaseValue() * temp2.getBaseValue()) > 4294967299.){
                      resultLo = (temp.getBaseValue() * temp2.getBaseValue()) / 2;
                      resultHi = resultLo;
        
                      RegisterMain.updateRegister("hi", resultHi);
                      RegisterMain.updateRegister("lo", resultLo);
                    }else{
                      resultLo = temp.getBaseValue() * temp2.getBaseValue();
                      RegisterMain.updateRegister("lo", resultLo);
                    }
                    break;
                case "div":
                    temp = RegisterMain.getRegister("s0");
                    temp2 = RegisterMain.getRegister("s1");
        
                    try {
                      resultLo = temp.getBaseValue();
                      if (temp2.getBaseValue() > 0)
                        resultLo = temp.getBaseValue() / temp2.getBaseValue();
                      resultHi = temp.getBaseValue();
                      if (temp2.getBaseValue() > 0)
                        resultHi = temp.getBaseValue() % temp2.getBaseValue();
                    } catch (Exception e) {
                      throw e;
                    }
        
                    RegisterMain.updateRegister("hi", resultHi);
                    RegisterMain.updateRegister("lo", resultLo);
                    break;
                case "neg":
                    temp = RegisterMain.getRegister("s0");
                    RegisterMain.updateRegister(temp.getName(), -temp.getBaseValue());
                    break;
                case "slt":
                    updated = RegisterMain.getRegister("s0");
                    temp = RegisterMain.getRegister("s1");
                    temp2 = RegisterMain.getRegister("s2");
        
                    result = 0;
                    if (temp.getBaseValue() < temp2.getBaseValue())
                      result = 1;
        
                    RegisterMain.updateRegister(updated.getName(), result);
                    break;
                case "lw":
                    MemoryBlock mem = Memory.getIndexAddress();
                    temp = RegisterMain.getRegister("s0");
                    RegisterMain.updateRegister(temp.getName(), mem.getValueToLong());
                    break;
                case "sw":
                    Memory.setIndexAddress(RegisterMain.getRegister("s0").getBaseValue().toString());
                    break;
                case "and":
                    updated = RegisterMain.getRegister("s0");
                    temp = RegisterMain.getRegister("s1");
                    temp2 = RegisterMain.getRegister("s2");
                    result = (temp.getBaseValue() & temp2.getBaseValue());
                    RegisterMain.updateRegister(updated.getName(), result);
                    break;
                case "or":
                    updated = RegisterMain.getRegister("s0");
                    temp = RegisterMain.getRegister("s1");
                    temp2 = RegisterMain.getRegister("s2");
                    result = (temp.getBaseValue() | temp2.getBaseValue());
                    RegisterMain.updateRegister(updated.getName(), result);
                    break;
            }

            return binary;
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getBinary(String instructionType, String opCode, String[] operands) {
        return Opcode.MAP_OPCODE.get(opCode) + DecodeOperands.decode(instructionType, opCode, operands);
    }
}