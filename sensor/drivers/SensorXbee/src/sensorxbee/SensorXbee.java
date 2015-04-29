package sensorxbee;

import java.util.Scanner;

public class SensorXbee {
 
    
    public static void main(String[] args) {
        int baudrate;
        String serialPort; 
        SensorPresenca s;
        Thread t1;
        Scanner sc = new Scanner(System.in);
        
        
       // System.out.println("Indique o serialPort");
       // serialPort = sc.nextLine();
       // System.out.println("Indique o baudrate");
       // baudrate = sc.nextInt();
        
       
        //s = new SensorPresenca(serialPort,baudrate);
        s = new SensorPresenca("/dev/ttyUSB0",9600);
        
         /*try {
                Thread.sleep(10000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }*/
        
        t1 =  new Thread(s);
        t1.start();
        s.presence();
        
        
        
    }
    
}
