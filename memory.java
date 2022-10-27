import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.BitSet;
import java.util.Scanner; // Import the Scanner class to read text
import java.lang.Short;

    public class memory {
        public static void main(String[] args) {
            register Reg = new register();

            // Step 1: main memory space created
            byte[] mem = new byte[65536];

            // all the gpr registers are stored in it
            register[] reg_array = new register[16];
            //initialzing gpr registers
            for (int m =0; m<reg_array.length; m++)
            {
                reg_array[m] = new register();
            }
            // all the spr registers are stored in it
            register[] spr_array = new register[16];
            //initialzing spr registers
            for (int m =0; m<spr_array.length; m++)
            {
                spr_array[m] = new register();
            }
            //Special Purpose Register: Code Counter
            register CC = new register();
            spr_array[2].values = CC.values;
            //Step 3: Initializing PC and IR registers
            register PC = new register();
            //register PC_array = new register(array);
            register IR = new register();
            spr_array[9].values = PC.values;
            spr_array[10].values = IR.values;
            //Special Register: Code Base
            spr_array[0].values = 0;

            //array for storing hex values
            String[] hex_instr = new String[16];

            

            //counter variable
            int i = 0;

            //16 bit register array(not explicitly mentioned)
            byte[] reg = new byte[16];
            //short[] flag = new short[16];
        
            //Step 2: File Reading
            try {
                //path ="C:\Users\Umema Ahmed\Downloads\p1.txt"
                File myObj = new File("C:\\Users\\Umema Ahmed\\Downloads\\p1.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNext()) {
                    int data = myReader.nextInt();//reads every integer of text file
                    reg[i] = (byte) data;         //converts int to byte
                    mem[i] = reg[i]; //storing values into memory
                    //System.out.println("The value in binary of memory: " + Integer.toBinaryString(mem[i]));
                    CC.values =(byte)i;
                    hex_instr[i] = Integer.toHexString(data); //converts int to hex
                    System.out.println("The value of memory is: "+mem[i]);
                    //System.out.println(hex_instr[i]);//for verification purposes
                    i++;
                }
                System.out.println();
                myReader.close();
                System.out.println("The value of Code Counter: " + CC.values);
                System.out.println();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            //System.out.print(mem[0]);
            register one = new register();


            // now point these registers to the main memory
            PC.values = 0;
            //one.LogicOR(PC, IR);
            //System.out.print("The size of the register is: " + PC.size);
            // PC=mem;


            // opcode decoded
            //converting value in IR to hex
            System.out.println();
            for (int j = 0; j < CC.values; j++) {
                int opcode;
                spr_array[10].values =mem[PC.values];
                int IR_value_in_int = mem[PC.values] & 0xff;
                System.out.println("The value of IR is:" + IR_value_in_int);
                String hex_opcode = Integer.toHexString(IR_value_in_int);
                System.out.println("The value of hex is:" + hex_opcode);
                //int opcode = IR_value_in_int;
                //try-catch block
                try{
                    opcode = Integer.parseUnsignedInt(hex_opcode);
                    //System.out.println("Opcode= " + opcode);
                }
                catch(NumberFormatException e)
                {
                    opcode = IR_value_in_int;
                }
                //System.out.println("Hexadecimal Opcode Equivalent= " + opcode);
                // switch statements according to register type
                //Type 1: Register-Register Instruction
                //size= 3 bytes
                if ((opcode >= 16 && opcode<=19)|| (opcode >= 26 && opcode <= 28)) {
                    switch (opcode) {
                        case 16:
                            reg_array[mem[j+1]].values = reg_array[mem[j+1]].MOV(reg_array[mem[j+1]],reg_array[mem[j+2]]);
                            PC.values = PC.ADD_PC(PC, 3);
                            break;
                        case 17:
                            // r1 ke value is changing here =r1(calling add ka function and passing r1,r2 as paramerters
                            reg_array[mem[j+1]].values = reg_array[mem[j+1]].ADD(reg_array[mem[j+1]],reg_array[mem[j+2]]);
                            PC.values = PC.ADD_PC(PC, 3);
                            break;
                        case 18:
                            reg_array[mem[j+1]].values = reg_array[mem[j+1]].SUBTRACT(reg_array[mem[j+1]],reg_array[mem[j+2]]);
                            PC.values = PC.ADD_PC(PC, 3);
                            break;
                        case 19:
                            System.out.println("The value of j is: " + j);
                            System.out.println("The value of PC is: " + PC.values);
                            int index = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The index is: "+ index);
                            //short product = (short) (mem[PC.values+2]mem[PC.values+3]);
                            //System.out.println("The product is: "+ product);
                            reg_array[index].values = reg_array[index].MULTIPLY(reg_array[index],reg_array[index +1]);
                            System.out.println("The value of MUL instruction is: " + reg_array[index].values);
                            PC.values = PC.ADD_PC(PC, 3);
                            break;
                        case 26:
                            reg_array[mem[j+1]].values = reg_array[mem[j+1]].DIV(reg_array[mem[j+1]],reg_array[mem[j+2]]);
                            PC.values = PC.ADD_PC(PC, 3);
                            break;
                        case 27:
                            reg_array[mem[j+1]].values = reg_array[mem[j+1]].LogicAND(reg_array[mem[j+1]],reg_array[mem[j+2]]);
                            PC.values = PC.ADD_PC(PC, 3);
                            break;
                        case 28:
                            reg_array[mem[j+1]].values = reg_array[mem[j+1]].LogicOR(reg_array[mem[j+1]],reg_array[mem[j+2]]);
                            PC.values = PC.ADD_PC(PC, 3);
                            break;
                    }
                }
                //Type 2: Register-Immediate Instruction
                //size 4 bytes
                else if ((opcode >= 30 && opcode <= 39) || (opcode >= 58 && opcode <= 61)) {
                    System.out.println("Opcode= " + opcode);
                    switch (opcode) {
                        case 30:
                            System.out.println("The value of j is: " + j);
                            System.out.println("The value of PC is: " + PC.values);
                            int Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            short immediate = (short) (Byte.toUnsignedInt(mem[PC.values+2]) + Byte.toUnsignedInt(mem[PC.values+3]));
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            reg_array[Register].values = reg_array[Register].MOVI(reg_array[Register],immediate);
                            System.out.println("The value of register after MOVI instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 31:
                            //System.out.println("The value of j is: " + j);
                            //System.out.println("The value of PC is: " + PC.values);
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            reg_array[Register].values = reg_array[Register].ADDI(reg_array[Register],immediate);
                            System.out.println("The value of register after ADDI instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC,4);
                            break;
                        case 32:
                            //System.out.println("The value of j is: " + j);
                            //System.out.println("The value of PC is: " + PC.values);
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            reg_array[Register].values = reg_array[Register].SUBI(reg_array[Register],immediate);
                            System.out.println("The value of register after SUBI instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 33:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            reg_array[Register].values = reg_array[Register].MULI(reg_array[Register],immediate);
                            System.out.println("The value of register after MULI instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 34:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            reg_array[Register].values = reg_array[Register].DIVI(reg_array[Register],immediate);
                            System.out.println("The value of register after DIVI instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 35:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                            System.out.println("The immediate is: "+ immediate);
                            //System.out.println("The immediate in binary is: "+ Integer.toBinaryString(immediate));
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].ANDI(reg_array[Register],immediate);
                            System.out.println("The value of register after ANDI instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 36:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                            System.out.println("The immediate is: "+ immediate);
                            //System.out.println("The immediate in binary is: "+ Integer.toBinaryString(immediate));
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].ORI(reg_array[Register],immediate);
                            System.out.println("The value of register after ORI instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 37:
                            System.out.println("The value of PC is: " + PC.values);
                            immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of PC before BZ instruction is: " + PC.values);
                            PC.values = PC.BZ(immediate,Reg.flag_register.get(1),PC);
                            System.out.println("The value of PC after BZ instruction is: " + PC.values);
                            //PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 38:
                            System.out.println("The value of PC is: " + PC.values);
                            immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of PC before BNZ instruction is: " + PC.values);
                            PC.values = PC.BZ(immediate,Reg.flag_register.get(1),PC);
                            System.out.println("The value of PC after BNZ instruction is: " + PC.values);
                            //PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 39:
                            System.out.println("The value of PC is: " + PC.values);
                            immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of PC before BC instruction is: " + PC.values);
                            PC.values = PC.BZ(immediate,Reg.flag_register.get(0),PC);
                            System.out.println("The value of PC after BC instruction is: " + PC.values);
                            //PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 58:
                            System.out.println("The value of PC is: " + PC.values);
                            immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of PC before BS instruction is: " + PC.values);
                            PC.values = PC.BZ(immediate,Reg.flag_register.get(2),PC);
                            System.out.println("The value of PC after BS instruction is: " + PC.values);
                            break;
                        case 59:
                            System.out.println("The value of PC is: " + PC.values);
                            immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                            System.out.println("The immediate is: "+ immediate);
                            System.out.println("The value of PC before Jump instruction is: " + PC.values);
                            PC.values = PC.Jump(immediate,PC);
                            System.out.println("The value of PC after Jump instruction is: " + PC.values);
                            break;
                        case 60:
                            break;
                        case 61:
                            break;
                    }
                }
                //Type 3: Memory Instruction
                else if (opcode == 51 || opcode == 52) {
                    switch (opcode) {
                        case 51:
                            int Register;
                            short immediate;
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                            System.out.println("The immediate is: "+ immediate);
                            //System.out.println("The immediate in binary is: "+ Integer.toBinaryString(immediate));
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].MOVL(reg_array[Register],mem,(int)immediate);
                            System.out.println("The value of register after MOVL instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 52:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                            System.out.println("The immediate is: "+ immediate);
                            //System.out.println("The immediate in binary is: "+ Integer.toBinaryString(immediate));
                            System.out.println("The value of memory before instruction is: " + mem[(int)immediate]);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            mem[(int)immediate] = reg_array[Register].MOVS(reg_array[Register],mem, (int)immediate);
                            System.out.println("The value of register after MOVS instruction is: " + mem[(int)immediate]);
                            //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                    }
                }
                //Type 4: Single-Operand Instruction
                else if (opcode >= 71 && opcode <= 78) {
                    switch (opcode) {
                        case 71:
                        int Register;
                        short immediate;
                        Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                        System.out.println("The Register is: "+ Register);
                        System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                        reg_array[Register].values = reg_array[Register].SHL(reg_array[Register],Reg.flag_register.get(0));
                        System.out.println("The value of register after SHL instruction is: " + reg_array[Register].values);
                        //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 72:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].SHR(reg_array[Register],Reg.flag_register.get(0));
                            System.out.println("The value of register after SHR instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 73:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].SHR(reg_array[Register],Reg.flag_register.get(0));
                            System.out.println("The value of register after SHR instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 74:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].RTL(reg_array[Register],Reg.flag_register.get(0));
                            System.out.println("The value of register after RTL instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 75:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].RTL(reg_array[Register],Reg.flag_register.get(0));
                            System.out.println("The value of register after RTL instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 76:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].RTR(reg_array[Register],Reg.flag_register.get(0));
                            System.out.println("The value of register after RTR instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 77:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].INC(reg_array[Register]);
                            System.out.println("The value of register after INC instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                        case 78:
                            Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                            System.out.println("The Register is: "+ Register);
                            System.out.println("The value of register before instruction is: " + reg_array[Register].values);
                            //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(reg_array[Register].values));
                            reg_array[Register].values = reg_array[Register].DEC(reg_array[Register]);
                            System.out.println("The value of register after DEC instruction is: " + reg_array[Register].values);
                            PC.values = PC.ADD_PC(PC, 4);
                            break;
                    }
                }
                //Type 5: No-Operand Instruction
                else if (opcode >= 241 && opcode <= 243) {
                    switch (opcode) {
                        case 241:
                            PC.values = PC.ADD_PC(PC, 1);
                            break;
                        case 242:
                            //No operation
                            PC.values = PC.ADD_PC(PC, 1);
                            break;
                        case 243:
                            System.out.println("The program has ended.");
                            //PC.values = PC.ADDI(PC, 1);
                            break;
                    }
                    break;
                }
                System.out.println();
            }
            System.out.println("The value of all GPRs after execution of instructions is:");
            for(int a =0; a<reg_array.length; a++)
            {
                System.out.println("The value of register " + a + " is: "+ (reg_array[a].values));
            }
            System.out.println("");

             System.out.println("The value of all SPRs after execution of instructions is:");
            for(int a =0; a<spr_array.length; a++)
            {
                System.out.println("The value of register " + a + " is: "+ (spr_array[a].values & 0xff));
            }
            


        }
    }







