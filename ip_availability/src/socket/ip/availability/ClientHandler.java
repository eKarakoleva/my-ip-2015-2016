package socket.ip.availability;


import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

	public class ClientHandler implements Runnable {
		Map<String,User>  usersInfo = new HashMap<String,User>();
		Map<String,User> clientsInfo = Collections.synchronizedMap(usersInfo);
		private static final String COMMAND_STOP_SERVER = "stopServer";

		private final Socket socket;

		private final EchoServer echoServer;

		public ClientHandler(EchoServer echoServer, Socket socket, Map<String,User> clientsInfo ) {
			this.socket = socket;
			this.echoServer = echoServer;
			this.clientsInfo = clientsInfo;
		}
		
		@Override
		public void run() {
			try {
				//final PrintStream out = 
					//new PrintStream(socket.getOutputStream());
				final Scanner scanner =
					new Scanner(socket.getInputStream());
				
				final CommandHandler usercommand =
						new CommandHandler();
				
				while (scanner.hasNextLine()) {
					final String command = scanner.nextLine();
					usercommand.execute(command, socket,clientsInfo);
					
					if (COMMAND_STOP_SERVER.equals(command)) {
						echoServer.stopServer();
						break;
					}
					
					//out.println(command);
				}
				scanner.close();
			//	out.close();
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

