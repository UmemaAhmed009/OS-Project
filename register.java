import java.util.BitSet;
public class register {
        public int hexcode;
        public int Register_name;
        public short size;
        public short values;
        BitSet flag_register = new BitSet(16);
        
        //carry bit -> index:0
        //zero bit -> index 1
        //sign bit -> index:2
        //overflow bit -> index:3
        public register()
        {

        }
            
    // methods for the Instruction set

    // Register-Register Instructions
    public short MOV(register R1,register R2){
          R1.values = R2.values;
          return R1.values;
    }

    public short ADD(register R1,register R2){
            int x=R1.values;
            int y=R2.values;
            int sum=x+y;
            R1.values=(short)sum;
            //overflow bit
            if (R1.values > 65536)
            {
                flag_register.set(3,4,true); 
            }
            else
            {
                flag_register.set(3,4,false);
            }
            //sign flag
            if (R1.values < 0)
            {
                flag_register.set(2,3,true); 
            }
            else
            {
                flag_register.set(2,3,false);
            }
            System.out.println("value of register after addition"+R1.values);
            //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
            return R1.values;
    }
    public short SUBTRACT(register R1,register R2){
            int x=R1.values;
            int y=R2.values;
            int sum=x-y;
            R1.values=(short)sum;
            //overflow bit
            if (R1.values > 65536)
            {
                flag_register.set(3,4,true); 
            }
            else
            {
                flag_register.set(3,4,false);
            }
            //sign flag
            if (R1.values < 0)
            {
                flag_register.set(2,3,true); 
            }
            else
            {
                flag_register.set(2,3,false);
            }
            //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
            return R1.values;
    }
    public short MULTIPLY(register R1,register R2){
        short X=R1.values;
        short Y=R2.values;
        short product = (short) (X*Y);
        R1.values=product;
        //overflow bit
        if (R1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;
    }
    public short DIV(register R1,register R2){
        short x=R1.values;
        short y=R2.values;
        short quotient=(short)(x/y);
        System.out.println("Output of DIV is:"+ quotient);
        R1.values=quotient;
        //overflow bit
        if (R1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;
    }
    public short LogicAND(register R1,register R2){
        int x=(int)R1.values;
        int y=(int)R2.values;
        short Logical_And_Value =(short)(x&y);
        R1.values = Logical_And_Value;
        System.out.println("Output of Logical And: "+ R1.values);
        //overflow bit
        if (R1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;
    }

    public short LogicOR(register R1,register R2){
        int x=(int)R1.values;
        int y=(int)R2.values;
        short Logical_OR_Value = (short)(x | y);
        R1.values=Logical_OR_Value;
        System.out.println("Output of Logical OR"+ R1.values);
        //overflow bit
        if (R1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;
    }
    public short MOVI(register r1, short num){
     r1.values  = num;
     return r1.values;
    }
    public short ADDI(register r1, short num){
        short sum=(short)(r1.values+num);
        r1.values=sum;
        //overflow flag
        if (r1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (r1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (r1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return r1.values;
        }
    //for incrementing Pc Counter
    public short ADD_PC(register r1, int num){
        short sum=(short)(r1.values+num);
        r1.values=sum;
        //setting zero flag to true
        if (r1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return r1.values;
        }
    public short SUBI(register r1, short num){
        short difference =(short)(r1.values-num);
        r1.values=difference;
        //overflow flag
        if (r1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (r1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (r1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return r1.values;
    }
    public short MULI(register r1, short num){
        short product=(short)(r1.values*num);
        r1.values=product;
        //overflow flag
        if (r1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (r1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (r1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return r1.values;

    }
    public short DIVI(register r1, short num){
        short quotient=(short)(r1.values/num);
        r1.values=quotient;
        //overflow flag
        if (r1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (r1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (r1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return r1.values;

    }
    public short ANDI(register R1, short num){
        short value=(short)(R1.values & num);
        R1.values=value;
        //overflow flag
        if (R1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;

    }
    public short ORI(register r1, short  num) {
        short value=(short)(r1.values |num);
        r1.values=value;
        //overflow flag
        if (r1.values > 65536)
        {
            flag_register.set(3,4,true); 
        }
        else
        {
            flag_register.set(3,4,false);
        }
        //sign flag
        if (r1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (r1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return r1.values;
        }
    public short BZ(short immediate, boolean variable,register PC) {
        if (variable == true) {                                   //boolean variable = zero flag
            PC.values = (short) (PC.values + (immediate*4));                //offset jumped
        }
        return PC.values;
    }
    public short BNZ(short immediate, boolean variable,register PC) {
        if (variable != true) {
            PC.values = (short) (PC.values + (immediate*4));                //offset
        }
        return PC.values;
    }   
    public short BC(short immediate, boolean variable,register PC){
            if (variable == true) {
                PC.values = (short) (PC.values + (immediate*4));
            }
            return PC.values;
    }
    public short BS(short immediate, boolean variable,register PC){
        if (variable == true) {
            PC.values = (short) (PC.values + (immediate*4));
        }
        return PC.values;
    }
    public short Jump(short immediate, register PC)
    {
        PC.values = (short) (PC.values + (immediate*4));
        return PC.values;
    }
    public void CALL(int num){

    }
    public void ACT(int num){

    }
    public short MOVL(register R1, byte[] memory, int offset){
        return R1.values = (short) memory[offset];
        }
    public byte MOVS(register R1, byte[] memory, int offset){
           return memory[offset] = (byte) R1.values;
    }
    public short SHL(register R1,boolean carry_bit){
        String binaryString = Integer.toBinaryString(R1.values);
        String withLeadingZeros = String.format("%16s",binaryString.replace(' ','0'));
        // carry bit value=leftmost
        if(Character.compare(withLeadingZeros.charAt(0),'0') == 0)
        {
            carry_bit = false;
        }
        else{
            carry_bit = true;
        }
        //shifting values
        R1.values=(short)(R1.values<<1);
        System.out.println("The value after shifting left once is: "+ R1.values);
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;
    }
    public short SHR(register R1, boolean carry_bit){
        String binaryString = Integer.toBinaryString(R1.values);
        String withLeadingZeros = String.format("%16s",binaryString.replace(' ','0'));
        // carry bit value=leftmost
        if(Character.compare(withLeadingZeros.charAt(0),'0') == 0)
        {
            carry_bit = false;
        }
        else{
            carry_bit = true;
        }
        //shifting values
        R1.values=(short)(R1.values>>1);
        System.out.println("The value after shifting right once is: "+ R1.values);
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;
    }

    public short RTL(register R1,boolean carry_bit){
        String binaryString = Integer.toBinaryString(R1.values);
        String withLeadingZeros = String.format("%16s",binaryString.replace(' ','0'));
        // carry bit value=leftmost
        if(Character.compare(withLeadingZeros.charAt(0),'0') == 0)
        {
            carry_bit = false;
        }
        else{
            carry_bit = true;
        }
        //rotate values
        R1.values=(short)Integer.rotateLeft(R1.values,1);
        System.out.println("The value after rotating left once is: "+ R1.values);
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;

        // now set the lowest bit

    }
    public short RTR(register R1, boolean carry_bit){
        String binaryString = Integer.toBinaryString(R1.values);
        String withLeadingZeros = String.format("%16s",binaryString.replace(' ','0'));
        // carry bit value=leftmost
        if(Character.compare(withLeadingZeros.charAt(0),'0') == 0)
        {
            carry_bit = false;
        }
        else{
            carry_bit = true;
        }
        //rotate values
        R1.values=(short)Integer.rotateRight(R1.values,1);
        System.out.println("The value after rotating right once is: "+ R1.values);
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        //setting zero flag to true
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        return R1.values;

    }
    public short INC(register R1)
    {
        return R1.values =(short)(R1.values+1);
    }
    public short DEC(register R1)
    {   R1.values =(short)(R1.values-1);
        //setting zero flag
        if (R1.values == 0)
        {
            flag_register.set(1,2,true); 
        }
        else
        {
            flag_register.set(1,2,false);
        }
        //setting sign flag
        if (R1.values < 0)
        {
            flag_register.set(2,3,true); 
        }
        else
        {
            flag_register.set(2,3,false);
        }
        return R1.values;
    }
    public void END()
    {
        System.exit(0);
    }
}

