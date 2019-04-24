import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

/*   public static void printTrace(PrintWriter trace, Queue[] s, int n, int time){
      trace.println("time= "+time);
      for (int i = 0; i<n+1; i++){ 
         trace.println(i+": "+s[i]);  
      }
*/
//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

      Scanner in = null;
      PrintWriter report = null;
      PrintWriter trace = null;
      Queue prequeue = new Queue();
      int n, m, time;
      int arrival, finish, min;
      //Job J;


//    1.  check command line arguments

   if(args.length < 1){
   System.out.println("Usage: Simulation infile");
         System.exit(1);
      }

//    2.  open files for reading and writing

   in = new Scanner(new File(args[0]));
   report = new PrintWriter(new FileWriter(args[0]+".rpt"));
   trace = new PrintWriter(new FileWriter(args[0]+".trc"));

//    3.  read in m jobs from input file
     //   m = in.nextInt();

       // for(int i = 1; i <= m; i++){ 
       //    int arr = in.nextInt();
       //    int dur = in.nextInt();
       //    prequeue.enqueue(new Job(arr, dur));
       // }
       
    String s = in.nextLine();
    m = Integer.parseInt(s);
     while(in.hasNext()){
         prequeue.enqueue((Job)getJob(in));
       }


//        System.out.print(prequeue);
        
//    4.  run simulation with n processors for n=1 to n=m-1  
    
    for(n = 1; n < m; n++){ 
//    5.      declare and initialize an array of n processor Queues and any 
//            necessary storage Queues
            	
       Queue[] queueArray = new Queue[n+1]; 
       for(int i = 0; i < n+1; i++){ 
          queueArray[i] = new Queue();	
       }      
      trace.println("*****************************");
      trace.println(n +" processor:");
      trace.println("*****************************");      
     
    //  queueArray[0].dequeueAll();
      Job J;
      for(int i=1; i<=m; i++){
         J = (Job)prequeue.peek();
         prequeue.dequeue();
         prequeue.enqueue(J);

         int arr = J.getArrival();
         int dur = J.getDuration();
         queueArray[0].enqueue(new Job(arr, dur));
      }
      time = 0;

//    6.      while unprocessed jobs remain 
      while( queueArray[0].length() != m+1 || ((Job)queueArray[0].peek()).getFinish() == -1 ){
//    7.          determine the time of the next arrival or finish event and 
//                update time
//       queueArray[0] = prequeue;  
//       System.out.println(prequeue);              
       printTrace(trace, queueArray[0], queueArray,n,time);                
       min = getIndex(queueArray);
   
             min = getIndex(queueArray);
            if(!queueArray[0].isEmpty()){
            arrival = ((Job)queueArray[0].peek()).getArrival();
            }else{
            arrival = 28394756;
            }
            if(!queueArray[min].isEmpty()){
            finish = ((Job)queueArray[min].peek()).getFinish();
            }else{
            finish = 10283565;
            }
            if(arrival < finish){
                  time = arrival;
             }else{
                 time = finish;
             }

   // starting time
     if(time == 0){
             time = ((Job)queueArray[0].peek()).getArrival();
             Job temp = (Job)queueArray[0].peek();
             temp.computeFinishTime(time);
             queueArray[1].enqueue((Job)queueArray[0].peek());
             queueArray[0].dequeue();
             arrival = ((Job)queueArray[0].peek()).getArrival();
             min = getIndex(queueArray);
             if(!queueArray[min].isEmpty()){ 
             J = (Job)(queueArray[min].peek());
             finish = J.getFinish();
             }else{
             finish = 239465287;
             }
     // reset the queueArray[0] when ending
      }else if(queueArray[0].length() == m &&((Job)queueArray[0].peek()).getFinish() != -1 ){
            break;
      
     // Jobs with computed finish times are in porcessors but not yet assigned back to queueArray[0]
     }else if(  queueArray[0].length() > 0 && ((Job)queueArray[0].peek()).getFinish() != -1  ){
                    min = getIndex(queueArray);
                    int index = 1;
                    for( int j=1; j < queueArray.length; j++){
                       if(!queueArray[j].isEmpty()){
                       time = ((Job)queueArray[j].peek()).getFinish();
                       index = j;
                       for( int i=1; i < queueArray.length; i++){
                          if(!queueArray[i].isEmpty()){
                             if(((Job)queueArray[i].peek()).getFinish() < time){
                               time = ((Job)queueArray[i].peek()).getFinish();
                               index = i;
                               break;
                             }else if(((Job)queueArray[i].peek()).getFinish() > time ){
                               time = time;
                               index = j;
                               break;
                          }
                       }
                     }
                  }
               }
                         if(!queueArray[index].isEmpty()){
                         queueArray[0].enqueue(queueArray[index].peek());
                         queueArray[index].dequeue();
                         }
               
            
       // else it is not the end or the beginning 
       }else{

//    8.          complete all processes finishing now
             if(time == finish){
                     if(!queueArray[0].isEmpty()){
                        arrival = ((Job)queueArray[0].peek()).getArrival();
                     }

                  queueArray[0].enqueue((Job)queueArray[min].peek());
                  queueArray[min].dequeue();
                     min= getIndex(queueArray);
                     if(!queueArray[min].isEmpty()){
                        finish = ((Job)queueArray[min].peek()).getFinish();
                       } if(!queueArray[0].isEmpty()){
                           arrival = ((Job)queueArray[0].peek()).getArrival();
                       }
                  

//   9.          if there are any jobs arriving now, assign them to a processor 
//                Queue of minimum length and with lowest index in the queue array.

             }else if(time == arrival){
               
                  min = getIndex(queueArray);

                  if(queueArray[min].isEmpty()){
                     Job temp = (Job)queueArray[0].peek();
                     temp.computeFinishTime(time);
                     queueArray[min].enqueue((Job)queueArray[0].peek());
                     queueArray[0].dequeue();
                     if(!queueArray[0].isEmpty()){
                        arrival = ((Job)queueArray[0].peek()).getArrival();
                     }
                     min= getIndex(queueArray);
                     if(!queueArray[min].isEmpty()){
                        finish = ((Job)queueArray[min].peek()).getFinish();
                     }
                  }else{
                  
                     queueArray[min].enqueue((Job)queueArray[0].peek());
                     queueArray[0].dequeue();
                  
                    if(!queueArray[0].isEmpty()){
                        arrival = ((Job)queueArray[0].peek()).getArrival();
                     }
                     min= getIndex(queueArray);
                     if(!queueArray[min].isEmpty()){
                        finish = ((Job)queueArray[min].peek()).getFinish();
                     }
       
                 }
             }  
         }       
 // while jobs are unfinished at this time compute finish and put them back in pending queue 
     for(int i = 1; i < queueArray.length ; i++){
          if(!queueArray[i].isEmpty()){
           J = (Job)(queueArray[i].peek());
           finish = J.getFinish();
                      
          if( finish == -1 ){
               Job temp = (Job)queueArray[i].peek();
               temp.computeFinishTime(time);
                }    
             }
          }
       
//    10.     } end loop
}
//    11.     compute the total wait, maximum wait, and average wait for 
//            all Jobs, then reset finish times

            
     if(n==1){
        report.println("Report file: "+ args[0] + ".rpt");
        report.println( m + " Jobs:");
        report.println(queueArray[0] +"\n");
        report.println("************************************************");
      }
      float averageWait=0;
      int totalWait=0;
      int maxWait=0;
      int max=0;
      Queue Backup = new Queue();
      for( int i = m-1 ; i >= 0 ; i--){
        max = ((Job)queueArray[0].peek()).getWaitTime();
        if( maxWait < max){
          maxWait = max;
        }
        totalWait += ((Job)queueArray[0].peek()).getWaitTime();
        Backup.enqueue((Job)queueArray[0].peek());
        queueArray[0].dequeue();
      }  
      averageWait =(float)totalWait/m;
      if(n == 1){
      report.println( n + " processor: totalWait="+ totalWait+ " maxWait="+maxWait+" averageWait="+averageWait);
      }else{
      report.println( n + " processors: totalWait="+ totalWait+ " maxWait="+maxWait+" averageWait="+averageWait);
      }

      while(Backup.length() != 0){
              ((Job)Backup.peek()).resetFinishTime();
              queueArray[0].enqueue((Job)Backup.peek());
              Backup.dequeue(); 
       }

//    11.     compute the total wait, maximum wait, and average wait for 
//            all Jobs, then reset finish times

//    12. } end loop
   }
//    13. close input and output files

      in.close();
      report.close();
      trace.close();

        
   }


 public static void printTrace(PrintWriter trace, Queue t, Queue[] s, int n, int time){
      trace.println("time= "+time);
     // trace.println("0: "+t);
      for (int i = 0; i<n+1; i++){
         trace.println(i+": "+s[i]);
      }
      trace.println("");
   }


 public static int getIndex(Queue[] s){
   int index = 1;
   
       for(int i =1; i<s.length;i++){
          if(s[i].length() < s[index].length()){
             index = i;
          }else if(s[i].length() == s[index].length()){
             index = index;
          }else{
             index = index;
          }
        
      }
  return index;
  }
  
}
