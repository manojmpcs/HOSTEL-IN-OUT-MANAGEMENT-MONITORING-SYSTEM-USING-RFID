import processing.serial.*;
import java.io.*;
import java.lang.*;
import java.time.LocalTime;
Serial myPort; //creates a software serial port on which you will listen to Arduino
Table dataTable; //table where we will read in and store values. You can name it something more creative!
 String lines[];
 int c=1;
  boolean b=true;
void setup()
{
  String portName = Serial.list()[0]; 
  //Here's a link to a basic handshake tutorial: https://processing.org/tutorials/overview/
  
 myPort = new Serial(this, portName, 9600); //set up your port to listen to the serial port
   
  //dataTable.addColumn("id"); //This column stores a unique identifier for each record. We will just count up from 0 - so your first reading will be ID 0, your second will be ID 1, etc. 
   
}
 
void serialEvent(Serial myPort)throws Exception
{
  /*try
  {
  val = myPort.readStringUntil('\n'); //The newline separator separates each Arduino loop. We will parse the data by each newline separator. 
  if (val!= null) { //We have a reading! Record it.
    val = trim(val);
   // println("manoj");//gets rid of any whitespace or Unicode nonbreakable space
    println(val); //Optional, useful for debugging. If you see this, you know data is being sent. Delete if  you like.
    fout=new FileOutputStream("RFID1.txt",true);
    println("manoj");
    BufferedOutputStream bout=new BufferedOutputStream(fout,1024);
    println("manoj");
   for(int i=0;i<val.length();i++)
   {
     System.out.println(val.charAt(i));
    bout.write(val.charAt(i)); 
   }
   bout.write(',');
    println("manoj3");
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
  }*/
  
}
 
void draw()
{ 
    //myPort.write("manoj");
  //// while(c==1)
    //{
      //myPort.write("manoj");
      if(b)
      {
      send();
      }
      //c++;
   //}
c++;
if(c==35)
{
 b=false; 
}
//exit();

  }
 // for (int a = 0 ; a < (lines.length) ; a++) {
   // println(lines[a]);
   // myPort.write(lines[a]);
     // return;
  //}   
  void send()
  {
    //myPort.write("manoj");
      File f=new File("send_data.txt");
  BufferedReader br=null;
  try
  {
    br=new BufferedReader(new FileReader(f));
    String text=null;
    while((text=br.readLine())!=null)
    {
      println(text);
     int p=text.length();
      char rt[]=text.toCharArray();
      for(int r=0;r<text.length();r++)
      {
      myPort.write(rt[r]);
      // delay();
      }
  
    }
  }
  catch(Exception e)
  {
   printStackTrace(e); 
  }
  finally
  {
    try
    {
    br.close();
    }
    catch(Exception e)
    {
      printStackTrace(e);
    }
  }
  }