import java.io.*;
import net.steppschuh.markdowngenerator.*;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.emphasis.BoldText;
import net.steppschuh.markdowngenerator.text.emphasis.ItalicText;
import net.steppschuh.markdowngenerator.text.emphasis.StrikeThroughText;

//import java.util.PriorityQueue;
//import java.util.Scanner;
import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;
import java.lang.Math;

public class driverFile{
//     public static void example() throws Exception {
//         StringBuilder sb = new StringBuilder()
//                 .append(new Text("I am normal")).append("\n")
//                 .append(new BoldText("I am bold")).append("\n")
//                 .append(new ItalicText("I am italic")).append("\n")
//                 .append(new StrikeThroughText("I am strike-through"));
    
//         System.out.println(sb);
//     }
    //public static byte[] mainMemory = new byte[65536];
    //public static byte[] code;
    public static void main(String[] args) throws IOException {
        //driverFile.example();
        Frames[] MainMemory = new Frames[512]; //Frames is the division of physical memory
        for(int j =0; j < MainMemory.length; j++)
        {
            MainMemory[j] = new Frames();
        }
        int i = 1; //temporary variable
        register PC = new register();
        int Queue1_count = 0;
        int Queue2_count = 0;
        int byteRead = 0;
        Boolean Que2more = false;
        boolean quantumQ1 = false;
        //int clocktime = 12;
        int[] arraysort = new int[6]; //sorted array

        int temp; //temporary variable used in sorting
        int FrameCounter =0;
        int FrameIndex =0;

        //Special-Purpose Registers
        
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
        
        /* --Loading Programs into memory in frames-- */
        for(int count=0;count<filenames.length;count++) {
            //try {
                FileInputStream inStream = new FileInputStream(filenames[count]);
                PCB PCB = new PCB();
                //Creating SPR Registers
                for(int j=0; j<PCB.spr_array.length; j++)
                {
                    PCB.spr_array[j] = new register();
                }
                //PCB.Process = inStream;
                PCB.process_Filename = filenames[count].getName();
                System.out.println("The filename is: " + PCB.process_Filename);

                PCB.process_Size = inStream.available();
                System.out.println("The process size is: " + PCB.process_Size);

                int[] NoOfPagesUsed = new int[filenames.length];
                //Calculating the number of pages/frames
                int PageArrayLength = (int)Math.ceil((double)(PCB.process_Size+50)/128);
                System.out.println("The no of pages of this process is: " + PageArrayLength);
                //Created Array of Pages for the particular process
                PCB.Page_Table = new int[PageArrayLength];
                NoOfPagesUsed[count] = PageArrayLength;
                
                /* --Storing values in PCB--  */
                byte[] PCBInfo = new byte[8];
                inStream.read(PCBInfo);
                PCB.process_Priority= PCBInfo[0];
                System.out.println("The process priority is: " + PCB.process_Priority);
                //Process_ID
                String ID = String.format("%02d", Integer.parseInt((String.valueOf(PCBInfo[1])))) + String.format("%02d", Integer.parseInt((String.valueOf(PCBInfo[2]))));
                PCB.process_ID = Integer.parseInt(ID, 16);
                System.out.println("The process ID is: " + PCB.process_ID);
                //data size
                String data_size= String.format("%02d", Integer.parseInt((String.valueOf(PCBInfo[3])))) + String.format("%02d", Integer.parseInt((String.valueOf(PCBInfo[4]))));
                System.out.println("The data size is: "+ data_size);
                PCB.data_Size = Integer.parseInt(data_size, 16);
                System.out.println("The process data size is: " + PCB.data_Size);
                //code size
                PCB.code_Size = PCB.process_Size - PCB.data_Size - 8;
                System.out.println("The code size is: " + PCB.code_Size);
                //Loading data in frames
                int PageCount =0;
                System.out.println("The Frame Counter is: " + FrameCounter);
                System.out.println(PCB.Page_Table.length);
                int StackIndex = 0;
                for(int f=FrameCounter; f<(PCB.Page_Table.length + FrameCounter); f++)
                {
                    StackIndex =0;
                    //System.out.println("The frame is: " + f);
                    PCB.Page_Table[PageCount] = FrameIndex;
                    for(int chunk =0; chunk< 128; chunk++)
                    {
                        if((byteRead = inStream.read()) != -1)
                    {
                        MainMemory[f].add((byte)byteRead,chunk);
                        StackIndex++;
                    }
                    }
                    //System.out.println("The frame " + f +" of Main Memory is: " + Arrays.toString(MainMemory[f].FrameObj));
                FrameIndex++;
                PageCount++;
                }
                //Reserving stack size in the memory
                for(int index =0; index <50; index++)
                {
                    if(StackIndex > 127)
                    {
                        FrameIndex++;
                        PCB.Page_Table[PageCount] = FrameIndex;
                        StackIndex =0;
                        MainMemory[FrameIndex].add((byte)0,StackIndex);
                        StackIndex++;
                    }
                    else
                    {
                        MainMemory[FrameIndex].add((byte)0,StackIndex);
                        StackIndex++;
                    }
                }
                System.out.println("The Page Table of this process is: " + Arrays.toString(PCB.Page_Table));
                FrameCounter = FrameCounter + PageCount;

                /* --Segmentation-- */
                //Data Segmentation
                PCB.spr_array[3].values = 0; //data base
                PCB.spr_array[4].values = (short)(PCB.data_Size -1); //data limit
                PCB.spr_array[5].values = 0; //data counter
                //Code Segmentation
                //change code base value
                PCB.spr_array[0].values = (short)(PCB.spr_array[4].values + (1)) ; //code base
                PCB.spr_array[1].values = (short)(PCB.spr_array[0].values + PCB.code_Size -1); //code limit
                PCB.spr_array[2].values = PCB.spr_array[0].values; //code counter
                //Stack Segmentation
                PCB.spr_array[6].values = (short)(PCB.process_Size) ; //stack base
                PCB.spr_array[8].values = (short)(PCB.spr_array[6].values + 49); //stack limit
                PCB.spr_array[7].values = PCB.spr_array[6].values; //stack count

                //Program Counter
                PCB.spr_array[9].values = PCB.spr_array[0].values;
                PC.values =PCB.spr_array[0].values;
                //Instruction Register
                
                System.out.println("The value of all SPRs after execution of instructions is:");
                System.out.print("[");
                for(int a =0; a<PCB.spr_array.length; a++)
                {
                    System.out.print(" "+ (PCB.spr_array[a].values)); //short to unsigned short
                }
                System.out.println(" ]");


                //int CodeCounter = (int)PCB.spr_array[0].values;
                int CodeCounter = 0;

                //Translation
                /* --Code Execution-- */
                System.out.println();
                //PCB.spr_array[0].values = 150;
                int PageNumber = Math.round(PCB.spr_array[0].values/128);//codebase = PCB.spr_array[0].values
                System.out.println("The Page Number is: " + PageNumber);
                int StartFrameNumber = PCB.Page_Table[PageNumber];//Page to Frame Translation
                //int codeBase = Short.toUnsignedInt((short)(PCB.spr_array[0].values- (128*StartFrameNumber)));
                int codeBase = (PCB.spr_array[0].values - (128*PageNumber));
                System.out.println("The Start Frame Number is: " + StartFrameNumber);
                PageNumber = Math.round(PCB.spr_array[1].values/128);
                int EndFrameNumber = PCB.Page_Table[PageNumber];
                System.out.println("The End Frame Number is: " + EndFrameNumber);
                //int codeLimit = Short.toUnsignedInt((short)(PCB.spr_array[1].values - (EndFrameNumber*128) +1));
                int codeLimit = (PCB.spr_array[1].values -(PageNumber*128)+1);
                System.out.println("The code base is: " + codeBase);
                System.out.println("The code limit is: " + codeLimit);
                //System.out.println("The code size is: " + (289 - 150+1));
                
                //CodeExecution
                int opcode;
                System.out.println("The PC value is: " + PC.values);
                if(PCB.process_Filename.equals("p5"))
                {
                    int k = StartFrameNumber; 
                    System.out.print("Code: [");
                    while(k <= EndFrameNumber)
                {
                    if(k == EndFrameNumber && codeBase<codeLimit && codeBase< 128)
                    {
                    //     /* --Calculating Opcode-- */
                    //     opcode = MainMemory[k].get((int)PC.values);
                    //     String hex_opcode = Integer.toHexString(opcode);//int to hex
                    //     try{
                    //         opcode = Integer.parseUnsignedInt(hex_opcode);//converting hex to unigned int
                    //         //System.out.println("Opcode= " + opcode);
                    //     }
                    //     catch(NumberFormatException e)
                    //     {
                    //         opcode = MainMemory[k].get((int)PC.values);
                    //     }
                        for(int j= codeBase; j<codeLimit; j++)
                        {
                            //Printing code segment
                            int num = Byte.toUnsignedInt(MainMemory[k].get(j));
                            //String num = String.format("%02X", Integer.parseInt(String.valueOf(MainMemory[k].get(j))));
                            System.out.print(" ");
                            System.out.printf("%S",Integer.toHexString(num & 0xFF)); //converting unsigned int value to hex
                            CodeCounter++;
                        //     if (CodeExecution.CheckOpcodeRange(opcode, PCB.gpr_array, PC, MainMemory[k].FrameObj) == true)
                        // {
                    //         break;
                    //     }
                    //  System.out.println();
                       }
                    }
                    else if(EndFrameNumber > k)
                    {
                        for(int j= codeBase; j<128; j++)
                        {
                            //int num = (int)(MainMemory[k].get(j));
                            int num = Byte.toUnsignedInt(MainMemory[k].get(j));
                            //String num = String.format("%02X", Integer.parseInt(String.valueOf(MainMemory[k].get(j))));
                            //System.out.print(" " + Integer.toHexString(num));
                            System.out.print(" ");
                            System.out.printf("%S",Integer.toHexString(num & 0xFF)); //converting unsigned int value to hex
                    //         /* --Calculating Opcode-- */
                    //         opcode = MainMemory[k].get((int)PC.values);
                    //         String hex_opcode = Integer.toHexString(opcode);//int to hex
                    //         try{
                    //             opcode = Integer.parseUnsignedInt(hex_opcode);//converting hex to unigned int
                    //             //System.out.println("Opcode= " + opcode);
                    //         }
                    //         catch(NumberFormatException e)
                    //         {
                    //             opcode = MainMemory[k].get((int)PC.values);
                    //         }
                    //         CodeCounter++;
                    //         if (CodeExecution.CheckOpcodeRange(opcode, PCB.gpr_array, PC, MainMemory[k].FrameObj) == true)
                    //     {
                    //         break;
                    //     }
                        }
                    }
                        //PC.values++;
                        codeBase =0;
                        k++;
                    }
                    System.out.println(" ]");
                System.out.println("The value of Code Counter is: " + CodeCounter);
                //     //for printing frames and pages
                    if(PCB.process_Filename.equals("flags"))
                    {
                        System.out.println("The value of all SPRs after execution of instructions is:");
                        System.out.print("[");
                        for(int a =0; a<PCB.spr_array.length; a++)
                        {
                            System.out.print(" "+ (PCB.spr_array[a].values & 0xff));
                        }
                        System.out.println(" ]");
                    }
                //         {
                //             for(int f =0; f<PCB.spr_array.length; f++)
                //         {
                //             System.out.println("The ")
                //             for(int k=0; k< 128; k++)
                //         {
                //             System.out.println("The byte read in page " + f + " at index " + k + " is: " + MainMemory[f].get(k));
                //         }
                //     }
                // }
                    
                    //Checking exception  
                        // System.out.println("The value of i is: " + i);
                        // System.out.println("The byte read is: " + byteRead);
                        // boolValue = PCB.setProcessPriority(32);
                        // System.out.println("The value of boolean is: " + boolValue);
                        // if(boolValue == false)
                        // {
                        //     break;
                        // }
                //         System.out.println("");
                //         
                // }

                    /* --Adding to the ready queue-- */
                // }
            
            // } catch (FileNotFoundException e) {
            //     System.out.println("An error occurred.");
            //     e.printStackTrace();
            // }
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
}

