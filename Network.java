import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Network implements Runnable {

	
	private Socket socket;
	private ObjectInputStream input_stream;
	private ObjectOutputStream output_stream;
	private Server_Protocol serv_protocol;
	
	
	public Network()
	{
		
	}
	
	public Server_Protocol getServProtocol()
	{
		return serv_protocol;
		
	}
	
	public void newConnection()
	{
		String hostName = "127.0.0.1";
		int portNumber = 4444;
		
		try
		{
		    socket = new Socket(hostName, portNumber);
		    System.out.println("Connection established");
		    socket.setKeepAlive(true);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		
		new Thread(this).start();
	}
	

	@Override
	public void run() {
		
			
			while (true) {
				
			try { 	
				
				
				
					input_stream = new ObjectInputStream(socket.getInputStream());
					serv_protocol = (Server_Protocol) input_stream.readObject();
	
					
					
				} catch (Exception e) {
				
					
				System.out.println(e + " Network");
				
			}
			
			}
	}	
}
