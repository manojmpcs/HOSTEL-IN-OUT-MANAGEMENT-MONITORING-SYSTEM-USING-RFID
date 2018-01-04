import processing.serial.*;
import java.io.*;
import java.lang.*;
Serial myPort; //creates a software serial port on which you will listen to Arduino
Table dataTable; //table where we will read in and store values. You can name it something more creative!
 
int numReadings = 500; //keeps track of how many readings you'd like to take before writing the file. 
int a; //counts each reading to compare to numReadings. 
 FileOutputStream fout;
String fileName,val;
void setup()
{
  String portName = Serial.list()[0]; 
  //CAUTION: your Arduino port number is probably different! Mine happened to be 1. Use a "handshake" sketch to figure out and test which port number your Arduino is talking on. A "handshake" establishes that Arduino and Processing are listening/talking on the same port.
  //Here's a link to a basic handshake tutorial: https://processing.org/tutorials/overview/
  
  myPort = new Serial(this, portName, 9600); //set up your port to listen to the serial port
   
  //dataTable.addColumn("id"); //This column stores a unique identifier for each record. We will just count up from 0 - so your first reading will be ID 0, your second will be ID 1, etc. 
   
}
 
void serialEvent(Serial myPort)throws Exception
{
  try
  {
  val = myPort.readStringUntil('\n'); //The newline separator separates each Arduino loop. We will parse the data by each newline separator. 
  if (val!= null) { //We have a reading! Record it.
    val = trim(val);
   // println("manoj");//gets rid of any whitespace or Unicode nonbreakable space
    println(val); //Optional, useful for debugging. If you see this, you know data is being sent. Delete if  you like.
    fout=new FileOutputStream("RFID2.txt",true);
   // println("manoj");
    BufferedOutputStream bout=new BufferedOutputStream(fout,1024);
   // println("manoj");
   for(int i=0;i<val.length();i++)
   {
     System.out.println(val.charAt(i));
    bout.write(val.charAt(i)); 
   }
   bout.write(',');
   // println("manoj3");
   // int Value =int(val); //parses the packet from Arduino and places the valeus into the sensorVals array. I am assuming floats. Change the data type to match the datatype coming from Arduino.  
    //TableRow newRow = dataTable.addRow(); //add a row for this new reading
    //newRow.setString("id",val);//record a unique identifier (the row's index)
    // println("manoj");
     // fileName ="major";
     // saveTable(dataTable, fileName); //Woo! save it to your computer. It is ready for all your spreadsheet dreams. 
     //  println("manoj");
    bout.close();
   }
  }
  catch(Exception e)
  {
    System.out.println(e);
  }
}
 
void draw()
{ 
   //visualize your sensor data in real time here! In the future we hope to add some cool and useful graphic displays that can be tuned to different ranges of values. 
}