
import java.net.*;
import java.util.Enumeration;
import java.io.*;
public class TestNetw {


	public static void main(String[] args) throws Exception {

		getAddr();
		//readURL("http://www.oracle.com/");

	}

	public static void readURL(String url) throws Exception {
		URL oracle = new URL(url);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(oracle.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}

	public static void getAddr() throws Exception
	{
		System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());  // often returns "127.0.0.1"
		Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
		for (; n.hasMoreElements();)
		{
			NetworkInterface e = n.nextElement();

			Enumeration<InetAddress> a = e.getInetAddresses();
			for (; a.hasMoreElements();)
			{
				InetAddress addr = a.nextElement();
				System.out.println("  " + addr.getHostAddress());
			}
		}
	} 
}
