
import java.io.*;
import java.net.*;

public class EchoClient {
	public static void main(String[] args) throws IOException {
		openClient("10.72.14.53",80);
	}
	public static void openClient(String hostName,int portNumber) throws IOException {
		         

        Socket echoSocket = new Socket(hostName, portNumber);
        PrintWriter out =   new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(  new InputStreamReader(System.in));
        try {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}
