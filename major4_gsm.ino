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
if(Serial.available()>0)
{
   a=Serial.readString();
   a.trim();
      //lcd.print(a);
   for(int i = 0; i < a.length(); i++)
   {
    if (a.substring(i, i+1) == ",") {
         // Grab the piece from the last index up to the current position and store it
          //pieces[counter] = a.substring(lastIndex, i);
          t=a.substring(lastIndex, i);
          t.trim();
          int p=t.length();
             lcd.print(p);
            //SendMessage(t);
          // Update the last position and add 1, so it starts from the next character
          lastIndex = i + 1;
          // Increase the position in the array that we store into
          counter++;
        }

        // If we're at the end of the string (no more commas to stop us)
        if (i == a.length() - 1) {
          // Grab the last part of the string from the lastIndex to the end
          pieces[counter] = a.substring(lastIndex, i);
        }
   }
   for(int i=0;i<=counter;i++)
   {
    if(pieces[i].length()==10)
    {
      lcd.print(pieces[i]);
      SendMessage(pieces[i]);
    }
   }
}
}
start=false;
}



 void SendMessage(String h)
{
  String o="7018427394";
  mySerial.println("AT+CMGF=1");    //Sets the GSM Module in Text Mode
  delay(1000);  // Delay of 1000 milli seconds or 1 second
  mySerial.println("AT+CMGS=\"+91"+o+"\"\r"); // Replace x with mobile number
  delay(1000);
  mySerial.println("your ward is not there manoj"+h);// The SMS text you want to send
  delay(100);
   mySerial.println((char)26);// ASCII code of CTRL+Z
  delay(1000);
  //count=0;
}

