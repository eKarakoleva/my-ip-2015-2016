package socket.ip.availability;


import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class CommandHandler{
	
	public synchronized void execute(String command, Socket socket, Map<String,User> clients,EchoServer echoServer) throws IOException{
		final PrintStream out = 
				new PrintStream(socket.getOutputStream());
		
		if(command.contains(":")){
			String[] split_command = command.split(":");
			if(split_command.length == 2){
				String clientName = split_command[0];	
				switch (split_command[1]) {
				
				case "shutdown": 
					if(clients.containsKey(clientName) && clients.get(clientName).isCurrentlylogged()){
						out.println("ok");
						echoServer.stopServer();
					}else{out.println("error:notlogged");}
					break;
				case "login":	
					if(!(clients.containsKey(clientName))){
						User user = new User(clientName);
						clients.put(clientName,user);
						out.println("ok");
						
					}
					if(clients.get(clientName).isCurrentlylogged()==false){
						clients.get(clientName).incrementCounter();
						clients.get(clientName).setCurrentlylogged(true);
						clients.get(clientName).setInDate(new Date());
						out.println("ok");
					}
					break;
				case "logout":
					
					if(clients.containsKey(clientName) && clients.get(clientName).isCurrentlylogged()){
							out.println("ok");
							clients.get(clientName).setCurrentlylogged(false);
							clients.get(clientName).setOutDate(new Date());
					}else{out.println("error:notlogged");};
					break;
					
				case "listabsent":
					if(clients.containsKey(clientName) && clients.get(clientName).isCurrentlylogged()){
						out.print("ok");
						  for (String userName : clients.keySet()){
							   User user = clients.get(userName);
								   if(!user.isCurrentlylogged()){
									   out.print(":" + userName);
								   }
						  }
						  out.println();
					}else{out.println("error:notlogged");}
					break;
				case "listavailable":
					if(clients.containsKey(clientName) && clients.get(clientName).isCurrentlylogged()){
						out.print("ok");
						  for (String userName : clients.keySet()){
							   User user = clients.get(userName);
								   if(user.isCurrentlylogged()){
									   out.print(":" + userName);
								   }
						  }
						out.println();
					}else{out.println("error:notlogged");}
					break;
				default: out.println("error:unknowncommand");
                break;
					
				}
			}else if (split_command.length == 3 && "info".contains(split_command[1]) ){
				String nameask = split_command[0];
				String namegive = split_command[2];
				if(clients.containsKey(nameask) && clients.containsKey(namegive) && clients.get(nameask).isCurrentlylogged()){
						out.print("ok");
						out.print(":" + namegive);
						out.print(":" + clients.get(namegive).isCurrentlylogged());
						out.print(":" + clients.get(namegive).getLogincount());
						Iterator<String> loggingDates = clients.get(namegive).getLoggingTime().iterator();
						while (loggingDates.hasNext()) {
							out.printf(loggingDates.next());
						}
						out.println();
				}else{out.println("error:notlogged");}
			}else{out.println("error:unknowncommand");}
		}else{out.println("error:unknowncommand");}
	}
	
}
