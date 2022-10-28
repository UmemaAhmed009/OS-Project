import java.io.*;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
public class driverFile{
    public static byte[] mainMemory = new byte[65536];
    public static byte[] code;
    public static void main(String[] args) throws IOException{
        int i = 1; //temporary variable
        int byteRead = 0;

        /* --Creating PCB for the process-- */
        PCB PCB = new PCB();

        //Creating Ready Queues
        Queue<String> Queue_1 = new LinkedList<>();
        Queue<String> Queue_2 = new LinkedList<>();

        //Creating Running Queue
        // Queue<String> Running_Queue = new LinkedList<>();
        // if (Running_Queue.isEmpty() == true)
        // {
        //     Running_Queue.add(Program.filename);
        // }

        //Program Loading
        try {
            File Program = new File("E:\\5th Semester\\OS\\demoFiles\\flags");
            FileInputStream inStream = new FileInputStream (Program);
            PCB.process_Filename = Program.getName();
            //DataInputStream dataInputStr = new DataInputStream(inStream);
        while((byteRead = inStream.read()) != -1) //checks whether we have reached the end of the file or not
        {
            //Total memory size = 64 Kb

            /* --Loading Program into memory-- */
            /* --Storing values in PCB--  */
            if (i == 1)
            {
                PCB.process_Priority = byteRead;
                System.out.println("The process priority is: " + PCB.process_Priority);
            }
            else if(i == 2)
            {
                String ID = String.valueOf(byteRead) + String.valueOf(inStream.read());
                PCB.process_ID = Integer.parseInt(ID);
                System.out.println("The process ID is: " + PCB.process_ID);

            }
            else if(i ==3)
            {
                String data_size = String.valueOf(byteRead) + String.valueOf(inStream.read());
                PCB.data_Size= Integer.parseInt(data_size);
                System.out.println("The process data size is: " + PCB.data_Size);
            }
 
            /* --Adding to the ready queue-- */
            i++;   
        }
        PCB.process_Size = i;
        System.out.println("The process size is: " + PCB.process_Size);
        PCB.code_Size = PCB.process_Size - PCB.data_Size - 8;
        System.out.println("The code size is: " + PCB.code_Size);
    }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        /* Add error handling for priority */
        if(PCB.process_Priority>=0 && PCB.process_Priority<=15)
        {
            Queue_1.add(PCB.process_Filename);
        }
        else
        {
            Queue_2.add(PCB.process_Filename);
        }
        System.out.println("Elements of queue 1" + Queue_1);
        System.out.println("Elements of queue 2" + Queue_2);

        }

}