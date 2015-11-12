package socket.ip.availability;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public void startServer() throws IOException {
		final ServerSocket serverSocket =
			new ServerSocket(port);
		
		//socket listen
		final Socket socket = serverSocket.accept();
		
		//output command in socket
		final PrintStream out = 
				new PrintStream(socket.getOutputStream());
		
		//read command in socket
		final Scanner scanner =
			new Scanner(socket.getInputStream());
		
		while (scanner.hasNextLine()) {
			final String command = scanner.nextLine();
			out.println(command);
		}
		scanner.close();
		out.close();
		serverSocket.close();
	}

}
