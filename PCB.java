import java.io.*;
public class PCB {
    public int program_Counter;
    public int process_ID;
    public int process_State;
    public int process_Priority;
    public String process_Filename;
    public FileInputStream Process;
    public int data_Size;
    public int code_Size;
    public int process_Size;
    public boolean que2more=false;
    public register[] gpr_array = new register[16];
    public register[] spr_array = new register[16];
    public int[] Page_Table;

    public boolean setProcessPriority(int Priority) throws IOException
    {
        try{
            if(Priority>=0 && Priority<=31)
            {
                this.process_Priority = Priority;
            }
            else if(Priority<0 || Priority>31)
            {
                throw new OSExceptions();
            }
        }
        catch (OSExceptions e) {
            System.out.println("The process " + this.process_Filename + " has invalid priority " + Priority + ".");
            e.InvalidPriority(this.Process);
            return false;
            }
        return true;
        }
    public int getProcessPriority()
    {
        return this.process_Priority;
    }
    
}
