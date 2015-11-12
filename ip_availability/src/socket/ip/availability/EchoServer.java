package socket.ip.availability;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
		
		final ClientHandler client = new ClientHandler(socket);
		client.run();
		serverSocket.close();
	}

}
