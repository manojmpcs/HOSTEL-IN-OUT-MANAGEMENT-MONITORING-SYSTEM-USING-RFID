#include<SoftwareSerial.h>
SoftwareSerial mySerial(10,11);
#include <LiquidCrystal.h>
const int rs = 2, en = 3, d4 = 4, d5 = 5, d6 = 6, d7 = 7;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
int count = 0; 
char singleChar; 
String readMsg="";
void setup(){
Serial.begin(9600);  
mySerial.begin(9600); //This line tells the Serial port to begin communicating at 9600 bauds
lcd.begin(16,2);
}
 
//
void loop(){
  count=0;
  lcd.println("Hostel In Out");
 while(mySerial.available()>0)
 {
   singleChar = mySerial.read();
   count++;
   readMsg += singleChar;
   //Serial.println(readMsg);
   if(count == 12)  
    {
       lcd.clear();
       lcd.println("Detected");
     Serial.println(readMsg);
     delay(1000);
      break; 
    }
 }
 // Serial.println(readMsg);
 delay(5000);
 readMsg="";
 lcd.clear();
   }

