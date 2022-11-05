import java.io.*;
//import java.util.PriorityQueue;
//import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;
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
        PCB[] arraysort = new PCB[6]; //sorted array
        PCB temp; //temporary variable used in sorting
   /*     for (int q = 0; q < 5; q++) {
            System.out.print("enter number");
            // Scanner sc = new Scanner(System.in);
            //
        }
        */
        //Creating Ready Queues for CPU scheduling
        Queue<PCB> Queue_1 = new LinkedList<>();
        Queue<PCB> Queue_2 = new LinkedList<>();
        //Queue<PCB> Queue_1 = new LinkedList<>();

        class PCBComparorer implements Comparator<PCB> {

            @Override
            public int compare(PCB o1, PCB o2) {
                if (o1.process_Priority > o2.process_Priority) {
                    return 1;
                } else if (o1.process_Priority < o2.process_Priority) {
                    return -1;
                }
                return 0;
            }
        }
        PriorityQueue<PCB> Q1priority = new PriorityQueue<PCB>(6, new PCBComparorer()); //will sort the queues based on priorities

        PriorityQueue<PCB> Q2priority = new PriorityQueue<PCB>(6, new PCBComparorer()); //round robin, 8 cycles logic = 4 instructions

        Queue<PCB> Running_Queue = new LinkedList<>(); //normal queue, first come first out
        // Running queue normal execution code
        // we need to treat pcb from que2 >1 differently
        if (Que2more = true) {

            Running_Queue.add(Q2priority.poll());
            // execution for one slice time= 8 clock cycles
            //current state saved in PCB
            // again sent to ready queue 2

            Q2priority.add(Running_Queue.poll());
            // state for next process restored
            Running_Queue.add(Q2priority.poll());

        }
        {
            // else execute normally
        }

        /* --Creating PCB for the process-- */
        PCB tempPCB = new PCB(); //temp
        tempPCB.process_Priority = 0;

        File[] filenames;
        File Folder = new File("D:\\semster5\\os\\DemoFiles");
        filenames = Folder.listFiles();

        //Program Loading
        for (int f = 0; f < filenames.length; f++) {
            System.out.println("The file is: " + filenames[f]);
        }
        System.out.println("");
        boolean boolValue = true;
        for (int counter = 0; counter < 6; counter++) {
            try {
                FileInputStream inStream = new FileInputStream(filenames[counter]);
                i = 1;
                PCB PCB = new PCB();
                arraysort[counter] = PCB; // putting all the arrays
                PCB.Process = inStream;
                PCB.process_Filename = filenames[counter].getName();
                System.out.println("The filename is: " + PCB.process_Filename);
                //DataInputStream dataInputStr = new DataInputStream(inStream);
                while ((byteRead = inStream.read()) != -1) //checks whether we have reached the end of the file or not
                {
                    if (PCB.process_Filename.equals("power")) {
                        System.out.println("");
                        System.out.println("The value of i is: " + i);
                        System.out.println("The byte read is: " + byteRead);
                        boolValue = PCB.setProcessPriority(32);
                        System.out.println("The value of boolean is: " + boolValue);
                        if (boolValue == false) {
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

                        if (PCB.process_Priority >= 0 && PCB.process_Priority <= 15) {
                            Q1priority.add(PCB);
                            Queue_1.add(PCB);
                            Queue1_count++;
                            // System.out.println("process inside the queue1 based on priority"+Queue_1.remove().process_Priority);
                        } else if (PCB.process_Priority >= 16 && PCB.process_Priority <= 32) {
                            Q2priority.add(PCB);
                            Queue_2.add(PCB);
                            Queue2_count++;
                        }
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

        // Add error handling for priority && cpu sceduling
/*

        //Multilevel Queue Scheduling- Priority scheduling
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
        // testing sorting of Queues

        for (int c = 0; c < Queue1_count; c++) {
            System.out.print(Running_Queue.add(Q1priority.poll()));
        }
        if (Q1priority == null) {
            for (int n = 0; n < Queue2_count; n++) {

                System.out.println("the elements of que2 are" + Running_Queue.add(Q2priority.poll()));
            }

        }
       // CRunning_Queue.poll()

    }
}