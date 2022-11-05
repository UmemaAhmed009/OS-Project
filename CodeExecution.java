import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text file

    public class CodeExecution {
        public static byte[] mem;
        //Type 1: Register-Register Instruction
        //size= 3 bytes
        public static short RegisterToRegisterInstruction(int opcode, register Reg1, register Reg2, register PC)
        {
            switch (opcode) {
                case 16:
                    Reg1.values = Reg1.MOV(Reg1 ,Reg2);
                    System.out.println("The value of MOV instruction is: " + Reg1.values);
                    PC.values = PC.ADD_PC(PC, 3);
                    break;
                case 17:
                    // r1 ke value is changing here =r1(calling add ka function and passing r1,r2 as paramerters
                    Reg1.values = Reg1.ADD(Reg1, Reg2);
                    System.out.println("The value of ADD instruction is: " + Reg1.values);
                    PC.values = PC.ADD_PC(PC, 3);
                    break;
                case 18:
                    Reg1.values = Reg1.SUBTRACT(Reg1, Reg2);
                    System.out.println("The value of SUB instruction is: " + Reg1.values);
                    PC.values = PC.ADD_PC(PC, 3);
                    break;
                case 19:
                    Reg1.values = Reg1.MULTIPLY(Reg1, Reg2);
                    System.out.println("The value of MUL instruction is: " + Reg1.values);
                    PC.values = PC.ADD_PC(PC, 3);
                    break;
                case 26:
                    Reg1.values = Reg1.DIV(Reg1, Reg2);
                    System.out.println("The value of DIV instruction is: " + Reg1.values);
                    PC.values = PC.ADD_PC(PC, 3);
                    break;
                case 27:
                    Reg1.values = Reg1.LogicAND(Reg1, Reg2);
                    System.out.println("The value of LogicAND instruction is: " + Reg1.values);
                    PC.values = PC.ADD_PC(PC, 3);
                    break;
                case 28:
                    Reg1.values = Reg1.LogicOR(Reg1, Reg2);
                    System.out.println("The value of LogicOR instruction is: " + Reg1.values);
                    PC.values = PC.ADD_PC(PC, 3);
                    break;
            }
            return Reg1.values;
        }
            public static short RegisterToImmediateInstruction(int opcode, register PC, register Reg1, short immediate)
            {
                switch (opcode) {
                    case 30:
                        //System.out.println("The value of j is: " + j);

                        //int Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                        int Register = (int)Reg1.values;
                        System.out.println("The Register is: "+ Register);
                        //short immediate = (short) (Byte.toUnsignedInt(mem[PC.values+2]) + Byte.toUnsignedInt(mem[PC.values+3]));
                        System.out.println("The immediate is: "+ immediate);
                        //System.out.println("The value of register before instruction is: " + gpr_array[Register].values);
                        System.out.println("The value of register before instruction is: " +Reg1.values);
                        //gpr_array[Register].values = gpr_array[Register].MOVI(gpr_array[Register],immediate);
                        Reg1.values = Reg1.MOVI(Reg1,immediate);
                        System.out.println("The value of register after MOVI instruction is: " + Reg1.values);
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    // case 31:
                    //     //System.out.println("The value of j is: " + j);
                    //     //System.out.println("The value of PC is: " + PC.values);
                    //     Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                    //     System.out.println("The Register is: "+ Register);
                    //     immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of register before instruction is: " + gpr_array[Register].values);
                    //     gpr_array[Register].values = gpr_array[Register].ADDI(gpr_array[Register],immediate);
                    //     System.out.println("The value of register after ADDI instruction is: " + gpr_array[Register].values);
                    //     PC.values = PC.ADD_PC(PC,4);
                    //     break;
                    // case 32:
                    //     //System.out.println("The value of j is: " + j);
                    //     //System.out.println("The value of PC is: " + PC.values);
                    //     Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                    //     System.out.println("The Register is: "+ Register);
                    //     immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of register before instruction is: " + gpr_array[Register].values);
                    //     gpr_array[Register].values = gpr_array[Register].SUBI(gpr_array[Register],immediate);
                    //     System.out.println("The value of register after SUBI instruction is: " + gpr_array[Register].values);
                    //     PC.values = PC.ADD_PC(PC, 4);
                    //     break;
                    // case 33:
                    //     Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                    //     System.out.println("The Register is: "+ Register);
                    //     immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of register before instruction is: " + gpr_array[Register].values);
                    //     gpr_array[Register].values = gpr_array[Register].MULI(gpr_array[Register],immediate);
                    //     System.out.println("The value of register after MULI instruction is: " + gpr_array[Register].values);
                    //     PC.values = PC.ADD_PC(PC, 4);
                    //     break;
                    // case 34:
                    //     Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                    //     System.out.println("The Register is: "+ Register);
                    //     immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of register before instruction is: " + gpr_array[Register].values);
                    //     gpr_array[Register].values = gpr_array[Register].DIVI(gpr_array[Register],immediate);
                    //     System.out.println("The value of register after DIVI instruction is: " + gpr_array[Register].values);
                    //     PC.values = PC.ADD_PC(PC, 4);
                    //     break;
                    // case 35:
                    //     Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                    //     System.out.println("The Register is: "+ Register);
                    //     immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     //System.out.println("The immediate in binary is: "+ Integer.toBinaryString(immediate));
                    //     System.out.println("The value of register before instruction is: " + gpr_array[Register].values);
                    //     //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                    //     gpr_array[Register].values = gpr_array[Register].ANDI(gpr_array[Register],immediate);
                    //     System.out.println("The value of register after ANDI instruction is: " + gpr_array[Register].values);
                    //     //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                    //     PC.values = PC.ADD_PC(PC, 4);
                    //     break;
                    // case 36:
                    //     Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                    //     System.out.println("The Register is: "+ Register);
                    //     immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     //System.out.println("The immediate in binary is: "+ Integer.toBinaryString(immediate));
                    //     System.out.println("The value of register before instruction is: " + gpr_array[Register].values);
                    //     //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                    //     gpr_array[Register].values = gpr_array[Register].ORI(gpr_array[Register],immediate);
                    //     System.out.println("The value of register after ORI instruction is: " + gpr_array[Register].values);
                    //     //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                    //     PC.values = PC.ADD_PC(PC, 4);
                    //     break;
                    // case 37:
                    //     System.out.println("The value of PC is: " + PC.values);
                    //     immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of PC before BZ instruction is: " + PC.values);
                    //     PC.values = PC.BZ(immediate,Reg.flag_register.get(1),PC);
                    //     System.out.println("The value of PC after BZ instruction is: " + PC.values);
                    //     //PC.values = PC.ADD_PC(PC, 4);
                    //     break;
                    // case 38:
                    //     System.out.println("The value of PC is: " + PC.values);
                    //     immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of PC before BNZ instruction is: " + PC.values);
                    //     PC.values = PC.BZ(immediate,Reg.flag_register.get(1),PC);
                    //     System.out.println("The value of PC after BNZ instruction is: " + PC.values);
                    //     //PC.values = PC.ADD_PC(PC, 4);
                    //     break;
                    // case 39:
                    //     System.out.println("The value of PC is: " + PC.values);
                    //     immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of PC before BC instruction is: " + PC.values);
                    //     PC.values = PC.BZ(immediate,Reg.flag_register.get(0),PC);
                    //     System.out.println("The value of PC after BC instruction is: " + PC.values);
                    //     //PC.values = PC.ADD_PC(PC, 4);
                    //     break;
                    // case 58:
                    //     System.out.println("The value of PC is: " + PC.values);
                    //     immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of PC before BS instruction is: " + PC.values);
                    //     PC.values = PC.BZ(immediate,Reg.flag_register.get(2),PC);
                    //     System.out.println("The value of PC after BS instruction is: " + PC.values);
                    //     break;
                    // case 59:
                    //     System.out.println("The value of PC is: " + PC.values);
                    //     immediate = (short) (mem[PC.values+1]+mem[PC.values+2]);
                    //     System.out.println("The immediate is: "+ immediate);
                    //     System.out.println("The value of PC before Jump instruction is: " + PC.values);
                    //     PC.values = PC.Jump(immediate,PC);
                    //     System.out.println("The value of PC after Jump instruction is: " + PC.values);
                    //     break;
                    // case 60:
                    //     break;
                    // case 61:
                    //     break;
                }
                return Reg1.values;
            }
            public static short MemoryInstructions(int opcode, register PC, register Reg1, short immediate, byte[] mem)
            {
                switch (opcode) {
                    case 51:
                        System.out.println("The immediate is: "+ immediate);
                        //System.out.println("The immediate in binary is: "+ Integer.toBinaryString(immediate));
                        System.out.println("The value of register before instruction is: " + Reg1.values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        Reg1.values = Reg1.MOVL(Reg1,mem,(int)immediate);
                        System.out.println("The value of register after MOVL instruction is: " + Reg1.values);
                        //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    case 52:
                        //Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                        //System.out.println("The Register is: "+ Register);
                        //immediate = (short) (mem[PC.values+2]+mem[PC.values+3]);
                        System.out.println("The immediate is: "+ immediate);
                        //System.out.println("The immediate in binary is: "+ Integer.toBinaryString(immediate));
                        System.out.println("The value of memory before instruction is: " + mem[(int)immediate]);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        mem[(int)immediate] = Reg1.MOVS(Reg1,mem,(int)immediate);
                        System.out.println("The value of register after MOVS instruction is: " + mem[(int)immediate]);
                        //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                }
                return Reg1.values;
            }
            public static short SingleOperandInstruction(int opcode, register Reg1, register PC)
            {
                switch (opcode) {
                    case 71:
                    System.out.println("The Register is: "+ Reg1);
                    System.out.println("The value of register before instruction is: " + Reg1.values);
                    //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                    Reg1.values = Reg1.SHL(Reg1,Reg1.flag_register.get(0));
                    System.out.println("The value of register after SHL instruction is: " + Reg1.values);
                    //System.out.println("The value of register after ANDI instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    case 72:
                        //System.out.println("The Register is: "+ Register);
                        System.out.println("The value of register before instruction is: " + Reg1.values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        Reg1.values = Reg1.SHR(Reg1,Reg1.flag_register.get(0));
                        System.out.println("The value of register after SHR instruction is: " + Reg1.values);
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    case 73:
                        //System.out.println("The Register is: "+ Register);
                        System.out.println("The value of register before instruction is: " + Reg1.values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        Reg1.values = Reg1.SHR(Reg1,Reg1.flag_register.get(0));
                        System.out.println("The value of register after SHR instruction is: " + Reg1.values);
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    case 74:
                        //System.out.println("The Register is: "+ Register);
                        System.out.println("The value of register before instruction is: " + Reg1.values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        Reg1.values = Reg1.RTL(Reg1,Reg1.flag_register.get(0));
                        System.out.println("The value of register after RTL instruction is: " + Reg1.values);
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    case 75:
                        //System.out.println("The Register is: "+ Register);
                        System.out.println("The value of register before instruction is: " + Reg1.values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        Reg1.values = Reg1.RTL(Reg1,Reg1.flag_register.get(0));
                        System.out.println("The value of register after RTL instruction is: " + Reg1.values);
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    case 76:
                        //System.out.println("The Register is: "+ Register);
                        System.out.println("The value of register before instruction is: " + Reg1.values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        Reg1.values = Reg1.RTR(Reg1,Reg1.flag_register.get(0));
                        System.out.println("The value of register after RTR instruction is: " + Reg1.values);
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    case 77:
                        //System.out.println("The Register is: "+ Register);
                        System.out.println("The value of register before instruction is: " + Reg1.values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        Reg1.values = Reg1.INC(Reg1);
                        System.out.println("The value of register after INC instruction is: " + Reg1.values);
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                    case 78:
                        //Register = (mem[PC.values+1] & 0xFF);//converting byte to int
                        //System.out.println("The Register is: "+ Register);
                        System.out.println("The value of register before instruction is: " + Reg1.values);
                        //System.out.println("The value of register before instruction in binary is: " + Integer.toBinaryString(gpr_array[Register].values));
                        Reg1.values = Reg1.DEC(Reg1);
                        System.out.println("The value of register after DEC instruction is: " + Reg1.values);
                        PC.values = PC.ADD_PC(PC, 4);
                        break;
                }
                return Reg1.values;
            }
            public static boolean NoOperandInstruction(int opcode, register PC)
            {
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
                        return true;
                }
                return false;
            }
            public static boolean CheckOpcodeRange(int opcode, register[] gpr_array, register PC, byte[] mem)
            {
                //System.out.println("Opcode= " + opcode);
                System.out.println("The value of PC is: " + PC.values);
                int Reg1_index = Integer.parseUnsignedInt(String.valueOf(mem[PC.values+1]));//what is the reg_1 code
                System.out.println("The Reg1 index is: " + Reg1_index);
                short immediate;
                //Type 2: Register-Immediate Instruction
                //size 3 bytes
                if ((opcode >= 16 && opcode<=19)|| (opcode >= 26 && opcode <= 28)) {
                    int Reg2_index = ((byte)(mem[PC.values+2])) & 0xFF;//what is the reg_2 code
                    System.out.println("The Reg2 index is: " + Reg2_index);
                    if(Reg1_index >=16 || Reg2_index >=16)
                    {
                        PC.values = PC.ADD_PC(PC, 3);
                        return false; //skipping instruction
                    }
                    gpr_array[Reg1_index].values = CodeExecution.RegisterToRegisterInstruction(opcode, gpr_array[Reg1_index],gpr_array[Reg2_index], PC);
                    System.out.println();
                }//Type 2: Register-Immediate Instruction
                //size 4 bytes
                else if ((opcode >= 30 && opcode <= 39) || (opcode >= 58 && opcode <= 61)) {
                    //System.out.println("Opcode= " + opcode);
                    if(Reg1_index >=16)
                    {
                        PC.values = PC.ADD_PC(PC, 4);
                        return false; //skipping instruction
                    }
                    immediate = (short)((byte)mem[PC.values+2]+(byte)mem[PC.values+3]);
                    gpr_array[Reg1_index].values = CodeExecution.RegisterToImmediateInstruction(opcode, PC, gpr_array[Reg1_index], immediate);
                }
                //Type 3: Memory Instruction
                //size: 4 bytes
                else if (opcode == 51 || opcode == 52) {
                    if(Reg1_index >=16)
                    {
                        PC.values = PC.ADD_PC(PC, 4);
                        return false; //skipping instruction
                    }
                    immediate = (short)((byte)mem[PC.values+2]+(byte)mem[PC.values+3]);
                    gpr_array[Reg1_index].values = CodeExecution.MemoryInstructions(opcode, PC, gpr_array[Reg1_index], immediate, mem);
                }
                //Type 4: Single-Operand Instruction
                //size: 2 bytes
                else if (opcode >= 71 && opcode <= 78) {
                    if(Reg1_index >=16)
                    {
                        PC.values = PC.ADD_PC(PC, 2);
                        return false; //skipping instruction
                    }
                    gpr_array[Reg1_index].values = CodeExecution.SingleOperandInstruction(opcode, gpr_array[Reg1_index], PC);
                }
                //Type 5: No-Operand Instruction
                //size: 1 byte
                else if (opcode >= 241 && opcode <= 243) {
                    if(CodeExecution.NoOperandInstruction(opcode, PC) == true)
                    {
                        System.out.println();
                        return true;
                    }
            }
            else{
                System.out.println("Invalid Opcode");
                PC.ADD_PC(PC, 1);
            }
            return false;
            }

        public static void main(String[] args) {
            //String[] hex_instr = new String[16]; //array for storing hex values

            int i = 0; //counter variable

            byte[] mem = new byte[65536]; // Step 1: main memory space created

            register[] gpr_array = new register[16]; // all the gpr registers are stored in it
            register[] spr_array = new register[16]; // all the spr registers are stored in it
            //initialzing gpr registers
            for (int m =0; m<gpr_array.length; m++)
            {
                gpr_array[m] = new register();
            }
            //initialzing spr registers
            for (int m =0; m<spr_array.length; m++)
            {
                spr_array[m] = new register();
            }
            //Code Counter Register
            register CC = new register();
            spr_array[2].values = CC.values;
            // PC Register
            register PC = new register();
            spr_array[9].values = PC.values;
            PC.values = 0; //Pointing these registers to the main memory
            //IR Register
            register IR = new register();
            spr_array[10].values = IR.values;

            //Special Register: Code Base
            spr_array[0].values = 0;

            //16 bit register array(not explicitly mentioned)
            //byte[] reg = new byte[16];
            //short[] flag = new short[16];

            //Step 2: File Reading
            try {
                //path ="C:\Users\Umema Ahmed\Downloads\p1.txt"
                File myObj = new File("C:\\Users\\Umema Ahmed\\Downloads\\p1.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNext()) {
                    int data = myReader.nextInt();//reads every integer of text file
                    mem[i] = (byte)data; //storing values into memory
                    CC.values =(byte)i;
                    i++;
                    //reg[i] = (byte) data;         //converts int to byte

                    //hex_instr[i] = Integer.toHexString(data); //converts int to hex
                    //System.out.println("The value of memory is: "+mem[i]);
                }
                //Printing contents of memory
                System.out.println("The memory is: ");
                System.out.print(" [");
                for(int k =0; k< CC.values+1; k++)
                {
                    System.out.print(mem[k] + ", ");
                }
                System.out.println("]");
                System.out.println();
                myReader.close();

                System.out.println("The value of Code Counter: " + CC.values);
                System.out.println();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            // opcode decoded
            //converting value in IR to hex
            int opcode;
            for (int j = 0; j < CC.values; j++) {
                spr_array[10].values =mem[PC.values]; //spr_array[10] = IR
                //redundant code
                int IR_value_in_int = mem[PC.values] & 0xff;//byte to int
                System.out.println("The value of IR is:" + IR_value_in_int);
                System.out.println("The value of PC is: " + PC.values);

                String hex_opcode = Integer.toHexString(IR_value_in_int);//int to hex
                //System.out.println("The value of opcode in hex is:" + hex_opcode);
                //int opcode = IR_value_in_int;
                //try-catch block
                try{
                    opcode = Integer.parseUnsignedInt(hex_opcode);//converting hex to unigned int
                    //System.out.println("Opcode= " + opcode);
                }
                catch(NumberFormatException e)
                {
                    opcode = IR_value_in_int;
                }
                //System.out.println("Hexadecimal Opcode Equivalent= " + opcode);
                // switch statements according to register type

                //Type:1 Register-Register Instruction
                //size: 3 bytes

                if (CodeExecution.CheckOpcodeRange(opcode, gpr_array, PC, mem) == true)
                {
                    break;
                }
                
        }
            System.out.println("The value of all GPRs after execution of instructions is:");
            System.out.print("[");
            for(int a =0; a<gpr_array.length; a++)
            {
                System.out.print(" "+ (gpr_array[a].values & 0xff));
            }
            System.out.println(" ]");
            System.out.println();
            System.out.println("The value of all SPRs after execution of instructions is:");
            System.out.print("[");
            for(int a =0; a<spr_array.length; a++)
            {
                System.out.print(" "+ (spr_array[a].values & 0xff));
            }
            System.out.println(" ]");




        }
    }







