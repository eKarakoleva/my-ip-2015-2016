package socket.ip.availability;

import java.io.IOException;

public class EchoServerStarter {
	
	private static final int SERVER_PORT = 2111;

	public static void main(String[] args) throws IOException {
		//create new socket on port SERVER_PORT
		final EchoServer server = new EchoServer(SERVER_PORT);
		server.startServer();
	}

}
