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
				switch (split_command[1]) {
				
				case "shutdown": 
					out.println("ok");
					echoServer.stopServer();
					break;
				case "login":
					String name = split_command[0];		
					if(!(clients.containsKey(name))){
						User user = new User(name);
						clients.put(name,user);
						out.println("ok");
						
					}
					if(clients.get(name).isCurrentlylogged()==false){

						clients.get(name).incrementCounter();
						clients.get(name).setCurrentlylogged(true);
						clients.get(name).setInDate(new Date());
						out.println("ok");
						
					}
					break;
				case "logout":
					String namelogout = split_command[0];	
					
					if(clients.containsKey(namelogout)){
						if(clients.get(namelogout).isCurrentlylogged()){
							out.println("ok");
							clients.get(namelogout).setCurrentlylogged(false);
							clients.get(namelogout).setOutDate(new Date());
						}else{
						out.println("error:notlogged");
						}
					}else{out.println("error:notlogged");};
					break;
					
				case "listabsent":
					out.print("ok");
					  for (String userName : clients.keySet()){
						   User user = clients.get(userName);
							   if(!user.isCurrentlylogged()){
								   out.print(":" + userName);
							   }
					  }
					break;
				case "listavailable":
					out.print("ok");
					  for (String userName : clients.keySet()){
						   User user = clients.get(userName);
							   if(user.isCurrentlylogged()){
								   out.print(":" + userName);
							   }
					  }
					break;
				default: out.println("error:unknowncommand");
                break;
					
				}
			}else if (split_command.length == 3 && "info".contains(split_command[1]) ){
				String nameask = split_command[0];
				String namegive = split_command[2];
				out.print("ok");
				if(clients.containsKey(nameask) && clients.containsKey(namegive)){
					if(clients.get(nameask).isCurrentlylogged()){
						out.print(":" + namegive);
						out.print(":" + clients.get(namegive).isCurrentlylogged());
						out.print(":" + clients.get(namegive).getLogincount());
						Iterator<String> loggingDates = clients.get(namegive).getLoggingTime().iterator();
						while (loggingDates.hasNext()) {
							out.printf(loggingDates.next());
						}
						out.println();
					}
				}else{out.println("error:notlogged");}
			}else{out.println("error:unknowncommand");}
		}else{out.println("error:unknowncommand");}
	}
	
}
