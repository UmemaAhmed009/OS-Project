import java.io.*;
import java.util.Scanner;
public class driverFile{
    public static byte[] mainMemory = new byte[65536];
    public static void main(String[] args) throws IOException{
        int i = 1; //temporary variable
        System.out.println("Hello World!");
        System.out.println("Hello Aiza!");
        //Program Loading
        try {
            File Program = new File("E:\\5th Semester\\OS\\demoFiles\\flags");
            FileInputStream inStream = new FileInputStream (Program);
            //DataInputStream dataInputStr = new DataInputStream(inStream);
            //Scanner Reader = new Scanner(Program);
        /* --Creating PCB for the process-- */
            PCB PCB = new PCB();
        while(inStream.available() > 0) //checks the remaining bytes of the file
        {
            //Total memory size = 64 Kb

            /* --Loading Program into memory-- */
            
            //String byte_to_String = String.valueOf(dataInputStr.readUTF()); //Converting byte to string
            if (i == 1)
            {
                //System.out.println("The Instream value: " + inStream.readUnsignedByte());
                //System.out.println("The datastream value: " + dataInputStr.readUTF());
                //PCB.process_Priority = inStream.read();
                System.out.println(" 1st value is :" + inStream.read());
                System.out.println(" 2nd value: " + inStream.read());
                System.out.println(" 3rd value: " + inStream.read());
                System.out.println(" 4th value: " + inStream.read());
                System.out.println(" 5th value: " + inStream.read());
                System.out.println(" 6th value: " + inStream.read());
                System.out.println(" 7th value: " + inStream.read());
                System.out.println(" 8th value: " + inStream.read());
                System.out.println(" 9th value: " + inStream.read());
                System.out.println(" 10th value: " + inStream.read());
            }
        //     else if(i == 2)
        //     {
        //         String ID = byte_to_String;
        //         i++;
        //         byte_to_String = String.valueOf(Reader.nextByte());
        //         ID = ID + byte_to_String;
        //         //PCB.process_ID = ID & 0xff;

        //     }
        //     else if(i ==4)
        //     {
        //         String data_size = byte_to_String;
        //         i++;
        //         byte_to_String = String.valueOf(Reader.nextByte());
        //         data_size = data_size + byte_to_String;
        //         //PCB.data_Size = data_Size & 0xff;
        //     }
 
        //     /* --Adding to the ready queue-- */
        //     
        // }
        // PCB.process_Size = i;
        // PCB.code_Size = PCB.process_Size - PCB.data_Size - 8;
        i++;
            
        } }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
        /* Storing information in the PCB */
        

    }
    

}