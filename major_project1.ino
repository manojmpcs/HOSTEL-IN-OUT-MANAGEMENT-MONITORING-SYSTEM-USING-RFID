#include<SoftwareSerial.h>
SoftwareSerial mySerial(10,11);
int count = 0; 
char singleChar; 
String readMsg="";
void setup(){
Serial.begin(9600);  
mySerial.begin(9600); //This line tells the Serial port to begin communicating at 9600 bauds
//mySerial.println("manoj");
}
 
//
void loop(){
  count=0;
 while(mySerial.available()>0)
 {
   singleChar = mySerial.read();
   count++;
   readMsg += singleChar;
   //Serial.println(readMsg);
   if(count == 12)  
    {
     Serial.println(readMsg);
     delay(1000);
      break; 
    }
 }
 // Serial.println(readMsg);
 delay(5000);
 readMsg="";
   }

