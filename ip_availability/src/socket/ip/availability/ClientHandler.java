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

				final Scanner scanner =
					new Scanner(socket.getInputStream());
				
				final CommandHandler usercommand =
						new CommandHandler();
				
				while (scanner.hasNextLine()) {
					final String command = scanner.nextLine();
					usercommand.execute(command, socket,clientsInfo,echoServer);
				}
				scanner.close();
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

