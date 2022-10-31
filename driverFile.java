import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
public class driverFile{
    public static byte[] mainMemory = new byte[65536];
    public static byte[] code;
    public static void main(String[] args) throws IOException{
        int i = 1; //temporary variable
        int Que1count=0;
        int Que2count=0;
        int byteRead = 0;
        Boolean Que2more=false;
        boolean quantumQ1=false;
        int clocktime=12;


        //Creating Ready Queues for CPU scheduling
        //Queue<String> Queue_1 = new LinkedList<>();
        //Queue<String> Queue_2 = new LinkedList<>();
        PriorityQueue<PCB> pq = new PriorityQueue<PCB>();
        Queue<PCB> Queue_1=new LinkedList<>();
        Queue<PCB> Queue_2=new LinkedList<>();

        //Creating Running Queue

        // if (Running_Queue.isEmpty() == true)
        // {
        //     Running_Queue.add(Program.filename);
        // }
        //creating running Queue
        Queue<PCB> Running_Queue=new LinkedList<>();
        // Running queue normal execution code
        // we need to treat pcb from que2 >1 differently
        if(Que2more=true){

          Running_Queue.add(Queue_2.poll());
            // execution for one slice time= 8 clock cycles
            //current state saved in PCB
            // again sent to ready queue 2

          Queue_2.add(Running_Queue.poll());
          // state for next process restored
            Running_Queue.add(Queue_2.poll());

        }
        {
            // else execute normally

        }


        /* --Creating PCB for the process-- */
        PCB PCB = new PCB();
        PCB HighPCB=new PCB();
        HighPCB.process_Priority=0;

        //Program Loading
        try {
            File Program = new File("D:\\semster5\\os\\flags");
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
        /* Add error handling for priority && cpu sceduling  */

        if(PCB.process_Priority>=0 && PCB.process_Priority<=15)
        {
            Queue_1.add(PCB);
            //Queue_1.add(PCB.process_Filename); //PCB is created and added to the ready queue
            Que1count++;
        }
        else
        {
            Queue_2.add(PCB);
            //Queue_2.add(PCB.process_Filename);
            Que2count++;

        }
        System.out.println("Elements of queue 1" + Queue_1);
        System.out.println("Elements of queue 2" + Queue_2);

        //Multilevel Queue Scheduling- Priority scheduling


            for (int j = 0; j < Que1count; j++) {
               if (Queue_1.element().process_Priority>HighPCB.process_Priority);
                 {
                     HighPCB=Queue_1.poll();
                 }
                    Running_Queue.add(HighPCB);
                    // we have got the process with highest priority is ready to be executed
            }


        // while loop false i.e Que1=null ; will jump to Queue2
        // Round Robin '+
        if (Que2count==1){
            Running_Queue.add(Queue_2.poll());

        }
        else if(Que2count>=1){
            Que2more=true;

        }
        // Multi level Feedback Queue Scheduling
        // execution
        if (quantumQ1=true){
            PCB.process_Priority= PCB.process_Priority+16;
            Queue_2.add(PCB);
        }
        if(clocktime>=12){

        }
        }


    // multilevel queue scheduling : priority wise
    // we will use priority check on the queue using a for loop to check which process has the highest priority and execute it


}