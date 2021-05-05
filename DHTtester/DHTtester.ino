#include "DHT.h"
#define DHTPIN 2
#define DHTTYPE DHT11   // DHT 11 M2302)
DHT dht(DHTPIN, DHTTYPE);
void setup() {
  Serial.begin(9600);
  dht.begin();
}
void loop() {
  String umidade = String(dht.readHumidity());
  String temperatura = String(dht.readTemperature());

  Serial.print(umidade);
  Serial.print("-");
  Serial.println(temperatura);
  delay(2000);
}
