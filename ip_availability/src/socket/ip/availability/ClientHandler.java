package socket.ip.availability;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

	public class ClientHandler implements Runnable {
		private static final String COMMAND_STOP_SERVER = "stopServer";

		private final Socket socket;

		private final EchoServer echoServer;

		public ClientHandler(EchoServer echoServer, Socket socket) {
			this.socket = socket;
			this.echoServer = echoServer;
		}
		
		@Override
		public void run() {
			try {
				final PrintStream out = 
					new PrintStream(socket.getOutputStream());
				final Scanner scanner =
					new Scanner(socket.getInputStream());
				
				final CommandHandler usercommand =
						new CommandHandler();
				
				while (scanner.hasNextLine()) {
					final String command = scanner.nextLine();
					usercommand.execute(command);
					
					if (COMMAND_STOP_SERVER.equals(command)) {
						echoServer.stopServer();
						break;
					}
					
					out.println(command);
				}
				scanner.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				echoServer.onClientStopped(this);
			}
		}

		public void stopClient() throws IOException {
			socket.close();
		}
	}

