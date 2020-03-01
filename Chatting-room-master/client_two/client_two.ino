/*
    This sketch sends a string to a TCP server, and prints a one-line response.
    You must run a TCP server in your local network.
    For example, on Linux you can use this command: nc -v -l 3000
*/

#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

#ifndef STASSID
#define STASSID "Abdelwahab"
#define STAPSK  "44444444"
#endif

const char* ssid     = STASSID;
const char* password = STAPSK;

const char* host = "192.168.43.35";
const uint16_t port =17345;

int outputpin= A0;
ESP8266WiFiMulti WiFiMulti;
WiFiClient cl;

void setup() {
  Serial.begin(9600);
  // We start by connecting to a WiFi network
  WiFi.mode(WIFI_STA);
  WiFiMulti.addAP(ssid, password);

  Serial.println();
  Serial.print("Wait for WiFi... ");

  while (WiFiMulti.run() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  Serial.print("connecting to ");
  Serial.print(host);
  Serial.print(':');
  Serial.println(port);
  while(!cl.connect(host, port)) {
   Serial.println("connection failed");
   Serial.println("wait 5 sec...");
   delay(5000);
 } 
 Serial.println("Connected to server");
}


void loop() {
  int analogValue = analogRead(outputpin);
  float millivolts = (analogValue/1024.0) * 3000; //3000 is the voltage provided by NodeMCU
  float celsius = (millivolts-500)/100;
  String msg="Two "+((String)(celsius));
  if(cl.connected()){
     cl.println(msg);   
      }
      delay(15000);
}
