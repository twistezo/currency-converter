import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.UIManager;

/** 
 * Currency Converter
 * 
 * @features
 * -Current exchange date
 * -Data is from http://www.ecb.europa.eu
 * -Date of last update ECB
 * -Working with BigDecimals
 * -Green/Red Light depends of ECB server respose
 * 
 * @author twistezo
 *
 */

public class CurrencyConverterMainClass {
	private static int code;
	private static boolean serverWorking = true;
	private final static String ECB_DATAS="http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml"; 
	
	public static void main(String[] args) {
		 try 
		    { 
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  
//		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		        
		        
		        //* Check ESB server response */
		        try
		        {
		            URL url = new URL(ECB_DATAS);
		            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		            connection.setRequestMethod("GET");
		            connection.connect();
		            code = connection.getResponseCode();  
		            
		            if(code==200) {
		            	serverWorking = true;
		            }
		            else{ 
		            	serverWorking = false;
		            }
		            
			    }
			    catch(Exception e)
			    {
			    	serverWorking = false;
			    }
		        
		        /** Build GUI */
		        new MyFrame();
		        
		    } 
		    catch(Exception e){ 
		    	System.out.println("GUI loading error..");
		    }
	}
	
	/** Getter for serverWorking information */
	public static boolean getServerWorking() {
		return serverWorking;
	}
}














