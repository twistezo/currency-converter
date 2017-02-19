import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * Class for pull current exchange rate from ECB XML
 * 
 * @author twistezo
 *
 */

public class DataFromXML {
	private static String dateXML;
	private final static String ECB_DATAS="http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml"; 
	
	public static ArrayList<Double> dataFromXML() {
		ArrayList<Double> currenciesAL = new ArrayList<Double>();		//List with pulled curriences
 
		try {
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(ECB_DATAS);
		
		    doc.getDocumentElement().normalize();
		    
//		    System.out.println("Root element: " +         doc.getDocumentElement().getNodeName());
		    
		    NodeList nList = doc.getElementsByTagName("Cube");
		    
//		    for (int i=0; i < nList.getLength(); i++) {
//		    	Node nNode = nList.item(i);
//		    	Element eElement = (Element) nNode;
//		    	System.out.println(i+" "+nNode.getNodeName()+ " / "
//		    			    +eElement.getAttribute("time")
//		    			 	+" / " +   eElement.getAttribute("rate"));
//		    }
		    
		    Node nNode0 = nList.item(1);
		    Element eElement0 = (Element) nNode0;
		    dateXML = eElement0.getAttribute("time");
		    
		    Node nNode = nList.item(2);
	    	Element eElement = (Element) nNode;
	    	Double EURtoUSD = new Double(eElement.getAttribute("rate"));
		    
		    Node nNode2 = nList.item(9);
	    	Element eElement2 = (Element) nNode2;
	    	Double EURtoPLN = new Double(eElement2.getAttribute("rate"));
		    
		    Node nNode3 = nList.item(7);
	    	Element eElement3 = (Element) nNode3;
	    	Double EURtoGBP = new Double(eElement3.getAttribute("rate"));
		    
	    	/** Add specified curriences from XML to List of curriences */
		    currenciesAL.add(EURtoUSD);
		    currenciesAL.add(EURtoPLN);
		    currenciesAL.add(EURtoGBP);
		}
		catch (Exception e) {
		}
		
	return (currenciesAL);
	}
	
	/** Getter for Date of ECB curriences */
	static String getDateXML() {
		return dateXML;
	}

}