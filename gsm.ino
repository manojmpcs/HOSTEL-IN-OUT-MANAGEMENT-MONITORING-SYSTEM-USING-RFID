 #include <SoftwareSerial.h>
#include <LiquidCrystal.h>
const int rs = 2, en = 3, d4 = 4, d5 = 5, d6 = 6, d7 = 7;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
boolean start=true;
SoftwareSerial mySerial(10, 11);
int count=0,lastIndex=0,counter=0;
char d;
const int numberOfPieces = 50;
String pieces[numberOfPieces],t;
String readMsg="";
String o1="7018427394";
void setup()
{
  mySerial.begin(9600);   // Setting the baud rate of GSM Module  
  Serial.begin(9600); 
   lcd.begin(16, 2);// Setting the baud rate of Serial Monitor (Arduino)
  delay(100);
}


void loop()
{
  // print the number of seconds since reset:
//  lcd.print(millis() / 10000);
String a;
if(start==true)
{
while(Serial.available()>0)
{
d=Serial.read();
readMsg+=d;
count++;
if(count==10)
{
  if(count>0)
  {
 SendMessage(o1);
 readMsg="";
 count=0;
start=false;
  }
  else
  {
   readMsg="";
 count=0;
  }
}
}
//start=false;
}
}



 void SendMessage(String h)
{
  mySerial.println("AT+CMGF=1");    //Sets the GSM Module in Text Mode
  delay(1000);  // Delay of 1000 milli seconds or 1 second
  mySerial.println("AT+CMGS=\"+91"+h+"\"\r"); // Replace x with mobile number
  delay(1000);
  mySerial.println("your child has not reached hostel yet");// The SMS text you want to send
  delay(100);
   mySerial.println((char)26);// ASCII code of CTRL+Z
  delay(1000);
  //count=0;
}

