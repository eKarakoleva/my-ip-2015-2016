package socket.ip.availability;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
	
	private final Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			final PrintStream out = 
				new PrintStream(socket.getOutputStream());
			
			final Scanner scanner =
				new Scanner(socket.getInputStream());
			
			while (scanner.hasNextLine()) {
				final String command = scanner.nextLine();
				
				out.println(command);
			}
			scanner.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
