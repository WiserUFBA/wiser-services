/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorxbee;


import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.XBeeException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SensorPresenca implements Runnable{
    
    private boolean message,status;// o atributo status indica se há ou não alguem presente
    
    private XBeeResponse response ;
    private XBee xbee = new XBee();
    private long atual_time, prev_time, change_time = 0,intrpt_time;
    //boolean status = false;
    private Calendar cal;
    private SimpleDateFormat sdf;
    private String serialPort;
    private int baudrate;
    //cal = Calendar.getInstance();            
    //sdf = new SimpleDateFormat("d 'de' MMMM 'de' Y");
    //sdf = new SimpleDateFormat("d/M/yy");
    
    int cont = 0;
    public SensorPresenca(String serialPort,int baudrate){
        this.serialPort = serialPort;
        this.baudrate = baudrate;
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("d/M/yy");
        System.out.println( sdf.format(cal.getTime()) );
        
        
    }
    
    //thread que aplica a lógica de presença alterando o atributo status
    public void run(){
        try{
         xbee.open(serialPort,baudrate);
         while(true){
            response = xbee.getResponse();
            status = true;
            intrpt_time = System.currentTimeMillis();
            System.out.println(status);
         }   
         //xbee.close();
        }
        catch(XBeeException e){
            System.out.println("OH OH... there's an ERROR with all the PROGRAM :/");
            e.printStackTrace();
        }
    }
    public void presence(){
        while(true){
            if(atual_time - intrpt_time > 1500 ){
                status = false;
                System.out.println(status);
            }
            atual_time = System.currentTimeMillis();
        }
    }
    // o atributo status indica se há ou não alguem presente
    public boolean getStatus(){
        return status;
    }
 
}