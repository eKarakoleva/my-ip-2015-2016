package socket.ip.availability;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private final int port;
	private boolean running;

	public EchoServer(int port) {
		this.port = port;
	}

	public void startServer() throws IOException {
		running = true;
		final ServerSocket serverSocket =
			new ServerSocket(port);
		
		while(running) {
			final Socket socket = serverSocket.accept();
			
			final ClientHandler client =
				new ClientHandler(this, socket);
			client.run();
		}
		serverSocket.close();
	}

	public void stopServer() {
		running = false;
	}
}
