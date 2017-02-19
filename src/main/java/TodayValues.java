import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Helper class for TodayValues Getter
 * 
 * @author twistezo
 *
 */

public class TodayValues {
	private ArrayList<Double> todayCurrenciesAL = new ArrayList<Double>();
	private BigDecimal EURtoUSD = new BigDecimal(0);
	private BigDecimal EURtoPLN = new BigDecimal(0);
	private BigDecimal EURtoGBP = new BigDecimal(0);
	private BigDecimal oneValue = new BigDecimal(1);
	private BigDecimal secondValue = new BigDecimal(1);
	private BigDecimal thirdValue = new BigDecimal(1);
	private BigDecimal temp = new BigDecimal(0);
	private BigDecimal temp2 = new BigDecimal(0);
	
	public void getTodayValues() {
		
		// Load actual currencies from WEB XML
		todayCurrenciesAL = DataFromXML.dataFromXML();
		EURtoUSD = BigDecimal.valueOf((todayCurrenciesAL.get(0)));
		EURtoPLN = BigDecimal.valueOf((todayCurrenciesAL.get(1)));
		EURtoGBP = BigDecimal.valueOf((todayCurrenciesAL.get(2)));
		
		// Convert EUR to PLN
		oneValue = oneValue.multiply(EURtoPLN).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		// Convert GBP to PLN
		temp = EURtoPLN.divide(EURtoGBP, 2, BigDecimal.ROUND_HALF_EVEN);
		secondValue = secondValue.multiply(temp);
			
		// Convert USD to PLN
		temp2 = EURtoPLN.divide(EURtoUSD, 2, BigDecimal.ROUND_HALF_EVEN);
		thirdValue = thirdValue.multiply(temp2);
		
	}
	
	public BigDecimal getOneValue() {return oneValue;}
	public BigDecimal getSecondValue() {return secondValue;}
	public BigDecimal getThirdValue() {return thirdValue;}
	

}
