import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import jssc.SerialPort;
import jssc.SerialPortException;

public class MeuArduino {

	public static void main(String[] args) throws IOException {
		SerialPort serialPort = new SerialPort("COM3");
		String temperatura = null;
		String umidade = null;

		for (int i = 0; i < 10; i++) {
			try {
				serialPort.openPort();
				serialPort.setParams(9600, 8, 1, 0);

				String informacao = serialPort.readString(100);
				String[] dLines = informacao.split("\r\n");
				String[] dLines2 = dLines[5].split("-");
				temperatura = dLines2[1];
				umidade = dLines2[0];
				System.out.println("Umidade é: " + umidade);
				System.out.println("Temperatura é: " + temperatura);

				serialPort.closePort();
			} catch (SerialPortException ex) {
				System.out.println(ex);
			}
			
			URL link = new URL(
					"http://sipagmt.getenjoyment.net/php/set.php?p=PedroTeixeira&lat=-19.9229&lon=-43.9026&t=" + temperatura + "&u=" + umidade);
			BufferedReader in = new BufferedReader(new InputStreamReader(link.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
		}
		
	}
}