import java.io.*;
//import java.util.PriorityQueue;
//import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
public class driverFile{
    public static byte[] mainMemory = new byte[65536];
    public static byte[] code;
    public static void main(String[] args) throws IOException {
        Pages[] abbasi_main_memory = new Pages[6];
        int i = 1; //temporary variable
        int Queue1_count = 0;
        int Queue2_count = 0;
        int byteRead = 0;
        Boolean Que2more = false;
        boolean quantumQ1 = false;
        //int clocktime = 12;
        int[] arraysort = new int[5]; //sorted array
        int temp; //temporary variable used in sorting
   /*     for (int q = 0; q < 5; q++) {
            System.out.print("enter number");
            // Scanner sc = new Scanner(System.in);
            //
        }


        for(int r=0;r<arraysort.length;r++) {
            for (int a = r + 1; a < arraysort.length; a++) {
                if (arraysort[r] > arraysort[a]) {
                    temp = arraysort[a];
                    arraysort[a] = arraysort[r];
                    arraysort[r] = temp;


                }

            }

            System.out.print(arraysort[r]);

        }

*/
        //Creating Ready Queues for CPU scheduling
        //Queue<String> Queue_1 = new LinkedList<>();
        //Queue<String> Queue_2 = new LinkedList<>();
        Queue<PCB> Queue_1 = new LinkedList<>();

        Queue<PCB> Queue_2 = new LinkedList<>();

        //Creating Running Queue

        // if (Running_Queue.isEmpty() == true)
        // {
        //     Running_Queue.add(Program.filename);
        // }
        //creating running Queue
        Queue<PCB> Running_Queue = new LinkedList<>();
        // Running queue normal execution code
        // we need to treat pcb from que2 >1 differently
        if (Que2more = true) {

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
        PCB tempPCB = new PCB(); //temp
        tempPCB.process_Priority = 0;

        File [] filenames;
        File Folder = new File("E:\\5th Semester\\OS\\demoFiles");
        filenames = Folder.listFiles();

        //Program Loading
        for(int f=0; f< filenames.length; f++)
        {
            System.out.println("The file is: " + filenames[f]);
        }
        System.out.println("");
        boolean boolValue = true;
        for(int counter=0;counter<6;counter++) {
            try {
                FileInputStream inStream = new FileInputStream(filenames[counter]);
                i=1;
                PCB PCB = new PCB();
                PCB.Process = inStream;
                PCB.process_Filename = filenames[counter].getName();
                System.out.println("The filename is: " + PCB.process_Filename);
                //DataInputStream dataInputStr = new DataInputStream(inStream);
                while ((byteRead = inStream.read()) != -1) //checks whether we have reached the end of the file or not
                {
                    if(PCB.process_Filename.equals("power"))
                    {   
                        System.out.println("");
                        System.out.println("The value of i is: " + i);
                        System.out.println("The byte read is: " + byteRead);
                        boolValue = PCB.setProcessPriority(32);
                        System.out.println("The value of boolean is: " + boolValue);
                        if(boolValue == false)
                        {
                            break;
                        }
                    }
                    //Total memory size = 64 Kb

                    /* --Loading Program into memory-- */
                    /* --Storing values in PCB--  */
                    else if (i == 1) {
                        PCB.process_Priority = byteRead;
                        System.out.println("The process priority is: " + PCB.process_Priority);
                    } else if (i == 2) {
                        String ID = String.valueOf(byteRead) + String.valueOf(inStream.read());
                        PCB.process_ID = Integer.parseInt(ID);
                        System.out.println("The process ID is: " + PCB.process_ID);

                    } else if (i == 3) {
                        String data_size = String.valueOf(byteRead) + String.valueOf(inStream.read());
                        PCB.data_Size = Integer.parseInt(data_size);
                        System.out.println("The process data size is: " + PCB.data_Size);
                    }

                    /* --Adding to the ready queue-- */
                    i++;
                }
                System.out.println("The last byte read is: " + byteRead);
                PCB.process_Size = i + 1;
                System.out.println("The process size is: " + PCB.process_Size);
                PCB.code_Size = PCB.process_Size - PCB.data_Size - 8;
                System.out.println("The code size is: " + PCB.code_Size);


            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.out.println();
        }

        /* Add error handling for priority && cpu sceduling
            for(PCB process : Queue_1){
        if(PCB.process_Priority>=0 && PCB.process_Priority<=15) {
            //priority
            if (Queue_1 == null) {
                Queue_1.add(PCB);
            } else if (Queue_1.peek().process_Priority > PCB.process_Priority) {
                tempPCB = Queue_1.poll();
                Queue_1.add(PCB);
                Queue_1.add(tempPCB);

            }
        }
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
        }


    // multilevel queue scheduling : priority wise
    // we will use priority check on the queue using a for loop to check which process has the highest priority and execute it
*/
    }
}