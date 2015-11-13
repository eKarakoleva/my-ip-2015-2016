package socket.ip.availability;


import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;

public class CommandHandler{
	
	public synchronized void execute(String command, Socket socket, Map<String,User> clients) throws IOException{
		final PrintStream out = 
				new PrintStream(socket.getOutputStream());
		
		if(command.contains(":")){
			String[] split_command = command.split(":");
			if(split_command.length == 2){
				switch (split_command[1]) {
				
				case "shutdown": 
					// to do echoServer.stopServer();
					break;
				case "login":
					String name = split_command[0];		
					if(!(clients.containsKey(name))){
						User user = new User(name);
						clients.put(name,user);
						out.println(clients.get(name).getUsername());
						
					}
					if(clients.get(name).isCurrentlylogged()==false){

						clients.get(name).incrementCounter();
						clients.get(name).setCurrentlylogged(true);
						out.println(clients.get(name).getUsername());
						
					}
					break;
				case "logout":
					String namelogout = split_command[0];	
					
					if(clients.containsKey(namelogout)){
						if(clients.get(namelogout).isCurrentlylogged()){
							clients.get(namelogout).setCurrentlylogged(false);
						}else{
						out.println("error:notlogged");
						}
					}else{out.println("error:notlogged");};
					break;
					
				case "listabsent":
					System.out.print("ok");
					  for (String userName : clients.keySet()){
						   User user = clients.get(userName);
							   if(!user.isCurrentlylogged()){
								   out.print(":" + userName);
							   }
					  }
					break;
				case "listavailable":
					  for (String userName : clients.keySet()){
						   User user = clients.get(userName);
							   if(user.isCurrentlylogged()){
								   out.print(":" + userName);
							   }
					  }
					break;
					
				}
			}else if (split_command.length == 3 && "info".contains(split_command[1]) ){
				String nameask = split_command[0];
				String namegive = split_command[2];
				if(clients.containsKey(nameask) && clients.containsKey(namegive)){
					if(clients.get(nameask).isCurrentlylogged()){
						out.println(clients.get(namegive).getLogincount());
						out.println(clients.get(namegive).isCurrentlylogged());
					}
				}else{out.println("error:notlogged");}
			}
		}
	}
	
}
