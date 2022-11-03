import java.io.*;
public class OSExceptions extends Exception {
    // public OSExceptions(String filename)
    // {
    //     //Call constructor of parent Exception and pass Error_Message to it
    //     super(filename); 
    // }
    public void InvalidOpcode()
    {

    }
    public void IllegalAccess()
    {

    }
    public void InvalidJump()
    {

    }
    public boolean InvalidPriority(FileInputStream process) throws IOException
    {
        System.out.println("This process is terminated abruptly.");
        process.close();
        return false;
    }
    public void StackOverflowOrUnderflow()
    {

    }
    public void DivideByZero()
    {

    }
    public void InvalidCodeSize()
    {

    }
}
