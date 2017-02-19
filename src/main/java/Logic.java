import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Class with logic of Currency Converter App
 * 
 * @author twistezo
 *
 */

class Logic {
	private ArrayList<Double> currenciesAL = new ArrayList<Double>();
	private BigDecimal EURtoUSD = new BigDecimal(0);
	private BigDecimal EURtoPLN = new BigDecimal(0);
	private BigDecimal EURtoGBP = new BigDecimal(0);
	
	/** Pull data from MyFrame class */
	public void logic(MyFrame f, String userInput) {
		BigDecimal userB = new BigDecimal(userInput);
		
		// Load actual currencies from WEB XML
		currenciesAL = DataFromXML.dataFromXML();
		EURtoUSD = BigDecimal.valueOf((currenciesAL.get(0)));
		EURtoPLN = BigDecimal.valueOf((currenciesAL.get(1)));
		EURtoGBP = BigDecimal.valueOf((currenciesAL.get(2)));
		
		// Convert PLN to PLN
		if (f.radioButtonPLNto.isSelected() & f.radioButtonPLN.isSelected() ) {
			userB.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " PLN");
		}
		
		// Convert PLN to EUR
		else if (f.radioButtonPLNto.isSelected() & f.radioButtonEUR.isSelected()) {
			userB = userB.divide(EURtoPLN, 2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " EUR");
		}
		
		// Convert PLN to GBP
		else if (f.radioButtonPLNto.isSelected() & f.radioButtonGBP.isSelected()) {
			BigDecimal temp = new BigDecimal(0);
			temp = EURtoGBP.divide(EURtoPLN, 2, BigDecimal.ROUND_HALF_EVEN);
			userB = userB.multiply(temp);
			
			f.resultField.setText(userB+ " GBP");
		}
		
		// Convert PLN to USD
		else if (f.radioButtonPLNto.isSelected() & f.radioButtonUSD.isSelected()) {
			BigDecimal temp = new BigDecimal(0);
			temp = EURtoUSD.divide(EURtoPLN, 2, BigDecimal.ROUND_HALF_EVEN);
			userB = userB.multiply(temp);
			
			f.resultField.setText(userB+ " USD");
		}
		
		/** ---------------------------------------------------------------------------- */
		
		// Convert EUR to PLN
		else if (f.radioButtonEURto.isSelected() & f.radioButtonPLN.isSelected()) {
			userB = userB.multiply(EURtoPLN).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " PLN");
			}
	
		// Convert EUR to EUR
		else if (f.radioButtonEURto.isSelected() & f.radioButtonEUR.isSelected()) {
			userB.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " EUR");
		}
		
		// Convert EUR to GBP
		else if (f.radioButtonEURto.isSelected() & f.radioButtonGBP.isSelected()) {
			userB = userB.multiply(EURtoGBP);
			userB.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " GBP");
		}
		
		// Convert EUR to USD
		else if (f.radioButtonEURto.isSelected() & f.radioButtonUSD.isSelected()) {
			userB.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			userB = userB.multiply(EURtoUSD);
			
			f.resultField.setText(userB+ " GBP");
		}
		
		/** --------------------------------------------------------------------------- */
		
		// Convert GBP to PLN
		else if (f.radioButtonGBPto.isSelected() & f.radioButtonPLN.isSelected()) {
			BigDecimal temp = new BigDecimal(0);
			temp = EURtoPLN.divide(EURtoGBP, 2, BigDecimal.ROUND_HALF_EVEN);
			userB = userB.multiply(temp);
			
			f.resultField.setText(userB+ " PLN");
		}
		
		// Convert GBP to EUR
		else if (f.radioButtonGBPto.isSelected() & f.radioButtonEUR.isSelected()) {
			userB = userB.divide(EURtoGBP, 2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " EUR");
		}
		
		// Convert GBP to GBP
		else if (f.radioButtonGBPto.isSelected() & f.radioButtonGBP.isSelected()) {
			userB.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " GBP");
		}
		
		// Convert GBP to USD
		else if (f.radioButtonGBPto.isSelected() & f.radioButtonUSD.isSelected()) {
			BigDecimal temp = new BigDecimal(0);
			temp = EURtoUSD.divide(EURtoGBP, 2, BigDecimal.ROUND_HALF_EVEN);
			userB = userB.multiply(temp);
			
			f.resultField.setText(userB+ " USD");
		}
		
		/** --------------------------------------------------------------------------- */
		
		// Convert USD to PLN
		else if (f.radioButtonUSDto.isSelected() & f.radioButtonPLN.isSelected()) {
			BigDecimal temp = new BigDecimal(0);
			temp = EURtoPLN.divide(EURtoUSD, 2, BigDecimal.ROUND_HALF_EVEN);
			userB = userB.multiply(temp);
			
			f.resultField.setText(userB+ " PLN");
		}
		
		// Convert USD to EUR
		else if (f.radioButtonUSDto.isSelected() & f.radioButtonEUR.isSelected()) {
			userB = userB.divide(EURtoUSD, 2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " EUR");
		}
		
		// Convert USD to GBP
		else if (f.radioButtonUSDto.isSelected() & f.radioButtonGBP.isSelected()) {
			BigDecimal temp = new BigDecimal(0);
			temp = EURtoGBP.divide(EURtoUSD, 2, BigDecimal.ROUND_HALF_EVEN);
			userB = userB.multiply(temp);
			
			f.resultField.setText(userB+ " GBP");
		}
		
		// Convert USD to USD
		else if (f.radioButtonUSDto.isSelected() & f.radioButtonUSD.isSelected()) {
			userB.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			f.resultField.setText(userB+ " USD");
		}
		
	}
}
	
	
	
	

