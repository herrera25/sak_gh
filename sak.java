// @author Gustavo Herrera

//* This application uses the open source component HttpRequest.java


import java.util.ArrayList;

public class sak  {
    public static void main(String[] args) {
        System.out.println("");
        if (args.length < 1) {
            System.out.println("This application requires at least one argument. Utilize the \"-Help\" parameter for more "
                +"information.");
            Help.printHelp();         
        } 
        // help
        else if (args[0].equalsIgnoreCase("-Help")) {
            System.out.println("Executing Help...");
            Help.printHelp();          
        } 
        // http request
        else if (args[0].equalsIgnoreCase("-HttpRequest")) {
            System.out.println("Executing HttpRequest...");
            if (args.length <2) {
                System.out.println("The -HttpRequest function requires a valid URL as the second parameter.");
            } else {
                String URL = args[1];
                HttpRequest request = new HttpRequest();
                if (request.readURL(URL)) {
                    System.out.println(request);
                }           
            }
        }
        // sleep
        else if (args[0].equalsIgnoreCase("-Sleep")) {
            System.out.println("Executing sleep...");
            OneSecondSleeper sleeper0 = new OneSecondSleeper(0);
            OneSecondSleeper sleeper1 = new OneSecondSleeper(1);
    
            System.out.println("\nSleep");
            long start = System.currentTimeMillis();
    
            sleeper0.sleep();
            sleeper1.sleep();
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");
        }
        // sleepfast
        else if (args[0].equalsIgnoreCase("-Sleepfast")) {
            System.out.println("Executing sleepfast...");

        OneSecondSleeper1 sleeper0 = new OneSecondSleeper1(0);
        OneSecondSleeper1 sleeper1 = new OneSecondSleeper1(1);

        System.out.println("\nNon-threaded Sleep");
        long start = System.currentTimeMillis();
        sleeper0.sleep();
        sleeper1.sleep();
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");

        System.out.println("\nThreaded Sleep");
        start = System.currentTimeMillis();
        sleeper0.start();
        sleeper1.start();

        try {
            sleeper0.join();
            sleeper1.join();
         } catch (Exception e) {
                System.out.println("Exception" + e);
            }
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");


            ArrayList<OneSecondSleeper1> sleeperList = new ArrayList<OneSecondSleeper1>();
            for (int i=2; i<12; i++) {
                sleeperList.add(new OneSecondSleeper1(i));
            }
    
            System.out.println("\nNon-threaded ArrayList sleep:");
            start = System.currentTimeMillis();
            for (OneSecondSleeper1 s: sleeperList) {
                s.sleep();
            }
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));
    
            System.out.println("\nThreaded ArrayList sleep:");
            start = System.currentTimeMillis();
            for (OneSecondSleeper1 s: sleeperList) {
                s.start();
            }
    
            try {
                for (OneSecondSleeper1 s: sleeperList) {
                    s.join();
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            System.out.println("Elapsed time = "+(System.currentTimeMillis()-start));


        }
         // sleepfastImplementsRunnable
         else if (args[0].equalsIgnoreCase("-sleepfastImplementsRunnable")) {
            System.out.println("Executing sleepfastImplementsRunnable...");
        
            OneSecondSleeper2 sleeper0 = new OneSecondSleeper2(0);
            OneSecondSleeper2 sleeper1 = new OneSecondSleeper2(1);
    
            System.out.println("\nNon-threaded Sleep");
            long start = System.currentTimeMillis();
            sleeper0.sleep();
            sleeper1.sleep();
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");
    
            System.out.println("\nThreaded Sleep");
            start = System.currentTimeMillis();
    
            Thread t0 = new Thread(sleeper0);
            Thread t1 = new Thread(sleeper1);
            t0.start();
            t1.start();
    
            try {
                t0.join();
                t1.join();
             } catch (Exception e) {
                    System.out.println("Exception" + e);
                }
                System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");
    
    
                ArrayList<OneSecondSleeper2> sleeperList = new ArrayList<OneSecondSleeper2>();
                for (int i=2; i<12; i++) {
                    sleeperList.add(new OneSecondSleeper2(i));
                }
        
                System.out.println("\nNon-threaded ArrayList sleep:");
                start = System.currentTimeMillis();
                for (OneSecondSleeper2 s: sleeperList) {
                    s.sleep();
                }
                System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));
        
                System.out.println("\nThreaded ArrayList sleep:");
                ArrayList<Thread> threadList = new ArrayList<Thread>();
                for (OneSecondSleeper2 s: sleeperList) {
                    threadList.add(new Thread(s));
                }
                start = System.currentTimeMillis();
                for (Thread t: threadList) {
                    t.start();
                }
    
                try {
                    for (Thread t: threadList) {
                        t.join();
                    }
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
                System.out.println("Elapsed time = "+(System.currentTimeMillis()-start));
         }
         // (jasonValitdateIndexThreaded)
         else if (args[0].equalsIgnoreCase("-JSONvalidateIndexthreaded")) {
            System.out.println("Executing JSONvalidateIndexThreaded...");    
           if (args.length != 2) {
               System.out.println("there was an error with the input");
            } else {
               String indexURL = args[1];
               HttpRequestURLs request = new HttpRequestURLs();
               if (request.readURL(indexURL)) {
                   System.out.println(request);
               }          
           }
         }
         // (jasonValitdateIndex)
         else if (args[0].equalsIgnoreCase("-JSONvalidateIndex")) {
            System.out.println("Executing JSONvalidateIndex...");    
           if (args.length != 2) {
               System.out.println("there was an error with the input");
            }else {
               String indexURL = args[1];
               HttpRequestURLs request = new HttpRequestURLs();
            
               if (request.readURL(indexURL)) {
                   System.out.println(request); 
                   
                
                } else {
                        System.out.println("There was an error reading the url");
                        System.out.println(request); 
                    }
                }
         }

        // http rquest index
        else if (args[0].equalsIgnoreCase("-HttpRequestIndex")) {
             System.out.println("Executing HttpRequestIndex...");    
            if (args.length != 2) {
                System.out.println("there was an error with the input");
             } else {
                String indexURL = args[1];
                HttpRequestURLs request = new HttpRequestURLs();
                if (request.readURL(indexURL)) {
                    System.out.println(request);
                }          
            }
            
        }       

        System.out.println("");
    }
} 