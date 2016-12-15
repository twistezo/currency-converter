import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Class with GUI
 * 
 * @author twistezo
 *
 */

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements KeyListener {
	private int borderOffset = 5;
	private String userInputString;
	
	JTextField userInput;
	JTextField resultField;
	JRadioButton radioButtonPLNto; 
	JRadioButton radioButtonEURto; 
	JRadioButton radioButtonGBPto; 
	JRadioButton radioButtonUSDto; 
	JRadioButton radioButtonPLN; 
	JRadioButton radioButtonEUR;
	JRadioButton radioButtonGBP; 
	JRadioButton radioButtonUSD;
	JButton startButton;
	JButton cleanButton;
	JLabel dateLabel;
	JLabel serverLabel; 
	JLabel todayLabel;
	JLabel onServerLabel;
	
	/** Check server on/off-line */
	boolean serverIsWorking = CurrencyConverterMainClass.getServerWorking();
	
	public MyFrame(){
		
		/** app should look
		 * --------------------------------------
		 * |									|
		 * | amountInput		result			|	
		 * | chooseCurrency1	chooseCurrency2	|
		 * | countButton						|	
		 * |									|
		 * --------------------------------------
		 */
		
		/** Create main frame */
		JFrame mainFrame = new JFrame("Currency Converter");
		
		/** Create panels and offsets from border*/
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder
								(borderOffset, borderOffset, borderOffset, borderOffset));	
								// up, left, down, right
		
		JPanel leftRadioButtonsBoxPanel = new JPanel();
		leftRadioButtonsBoxPanel.setLayout((new BoxLayout(leftRadioButtonsBoxPanel, 
													BoxLayout.PAGE_AXIS)));
		  
		TitledBorder titledLeftRadioButtons = new TitledBorder(null, "from",
			  				TitledBorder.CENTER, TitledBorder.TOP, null, Color.gray);
		leftRadioButtonsBoxPanel.setBorder(titledLeftRadioButtons);

		JPanel rightRadioButtonsBoxPanel = new JPanel();
		rightRadioButtonsBoxPanel.setLayout((new BoxLayout(rightRadioButtonsBoxPanel, 
													BoxLayout.PAGE_AXIS)));
		
		TitledBorder titledRightRadioButtons = new TitledBorder(null, "to", 
				 				TitledBorder.CENTER, TitledBorder.TOP, null, Color.gray);
		rightRadioButtonsBoxPanel.setBorder(titledRightRadioButtons);
		
		JPanel statusServerPanel = new JPanel(); 
		 
		/** Create TextFields - userInput of value */
		userInput = new JTextField();
			userInput.setHorizontalAlignment(JTextField.RIGHT);
			userInput.setPreferredSize( new Dimension( 100, 24 ) );
			userInput.setFocusable(true);
			userInput.addKeyListener(this);
			
		resultField = new JTextField();
			resultField.setHorizontalAlignment(JTextField.LEFT);
			resultField.setPreferredSize( new Dimension( 100, 24 ) );
			resultField.setEditable(false);
		
		/** Create LEFT list of currency comodities */
		radioButtonPLNto = new JRadioButton("PLN");
			radioButtonPLNto.setSelected(true);
		radioButtonEURto = new JRadioButton("EUR");
		radioButtonGBPto = new JRadioButton("GBP");
		radioButtonUSDto = new JRadioButton("USD");
		
		ButtonGroup buttonGroupLeft = new ButtonGroup();
		buttonGroupLeft.add(radioButtonPLNto);
		buttonGroupLeft.add(radioButtonEURto);
		buttonGroupLeft.add(radioButtonGBPto);
		buttonGroupLeft.add(radioButtonUSDto);
		
		/** Create RIGHT list of currency comodities */
		radioButtonPLN = new JRadioButton("PLN");
		radioButtonEUR = new JRadioButton("EUR");
			radioButtonEUR.setSelected(true);
		radioButtonGBP = new JRadioButton("GBP");
		radioButtonUSD = new JRadioButton("USD");
		
		ButtonGroup buttonGroupRight = new ButtonGroup();
		buttonGroupRight.add(radioButtonPLN);
		buttonGroupRight.add(radioButtonEUR);
		buttonGroupRight.add(radioButtonGBP);
		buttonGroupRight.add(radioButtonUSD);
		
		/** Create bottom GO and CLEAN button */
		startButton = new JButton("Calculate");
			startButton.addActionListener(new startButtonListener());
		cleanButton = new JButton("Clear");
			cleanButton.addActionListener(new cleanButtonListener());
			
		/** Create Separator */
		JSeparator separatorH = new JSeparator(JSeparator.HORIZONTAL);
		separatorH.setPreferredSize(new Dimension(2,2));
		
		/** Title */
		JLabel titleLabel = new JLabel("Currency Converter");
		titleLabel.setFont(new Font(null, Font.PLAIN, 20));
		titleLabel.setForeground(Color.GRAY);
		
		/** Label with todays currencies */
	if(serverIsWorking) {
		TodayValues today = new TodayValues();
		today.getTodayValues();
		BigDecimal oneValue = today.getOneValue();
		BigDecimal secondValue = today.getSecondValue();
		BigDecimal thirdValue = today.getThirdValue();
		
		todayLabel = new JLabel("1 PLN = " +oneValue.toString()+ " EUR / "
				+secondValue.toString()+ " GBP / " +thirdValue.toString()+ " USD");
	}else {
		 todayLabel = new JLabel("No internet connection");
	}
		
	/** Create Label with actual currencies date from XML */
	if(serverIsWorking) {
		
        DataFromXML.dataFromXML();
        String dateXML = DataFromXML.getDateXML();
        dateLabel = new JLabel("Last update: " +dateXML);
			dateLabel.setHorizontalAlignment(JLabel.RIGHT);
			dateLabel.setForeground(Color.GRAY);
			serverLabel = new JLabel("Data server: http://www.ecb.europa.eu/");
			
	}else {
		dateLabel = new JLabel("Last update: ----");
		dateLabel.setHorizontalAlignment(JLabel.RIGHT);
		dateLabel.setForeground(Color.GRAY);
	}
	
		/** serverStatusLabel */
		JLabel serverStatusLabel = new JLabel("Data source: ecb.europa.eu");
		serverStatusLabel.setForeground(Color.GRAY);
		
		/** Image icon ON-OFF data server */
		BufferedImage onServerImage = null;
	if (serverIsWorking) {
		try {
			
			onServerImage = ImageIO.read(getClass().getResource("on.png"));
			Image onServerImageSmall = onServerImage.getScaledInstance(12, 12, java.awt.Image.SCALE_SMOOTH);
			onServerLabel = new JLabel(new ImageIcon(onServerImageSmall));
		} catch (IOException e) {
			System.out.println("No File");
		}
		
	} else {
		try {
			onServerImage = ImageIO.read(getClass().getResource("off.png"));
			Image onServerImageSmall = onServerImage.getScaledInstance(12, 12, java.awt.Image.SCALE_SMOOTH);
			onServerLabel = new JLabel(new ImageIcon(onServerImageSmall));
		} catch (IOException e) {
			System.out.println("No File");
		}	
	}
	
		/** Add elements to panels */
		c.insets = new Insets(borderOffset,0,0,borderOffset/2);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2; 
		c.ipady = 15; 
		mainPanel.add(titleLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		mainPanel.add(userInput, c);
		
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(resultField, c);
		
		c.gridx = 0;
		c.gridy = 2;
		leftRadioButtonsBoxPanel.add(radioButtonPLNto);
		leftRadioButtonsBoxPanel.add(radioButtonEURto);
		leftRadioButtonsBoxPanel.add(radioButtonGBPto);
		leftRadioButtonsBoxPanel.add(radioButtonUSDto);
		mainPanel.add(leftRadioButtonsBoxPanel, c);

		c.gridx = 1;
		c.gridy = 2;
		rightRadioButtonsBoxPanel.add(radioButtonPLN);
		rightRadioButtonsBoxPanel.add(radioButtonEUR);
		rightRadioButtonsBoxPanel.add(radioButtonGBP);
		rightRadioButtonsBoxPanel.add(radioButtonUSD);
		mainPanel.add(rightRadioButtonsBoxPanel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		mainPanel.add(startButton, c);
		
		c.gridx = 1;
		c.gridy = 3;
		mainPanel.add(cleanButton, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(separatorH, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.fill = 0;		// reset fill
		mainPanel.add(todayLabel, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;   //2 columns wide
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.insets = new Insets(6,0,0,5);
		mainPanel.add(dateLabel, c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,0,0);
		mainPanel.add(statusServerPanel, c);
		
		statusServerPanel.add(serverStatusLabel);
		statusServerPanel.add(onServerLabel);
		
		/** Activate frame and set window position */
		mainFrame.add(mainPanel);
		mainFrame.setBounds(500, 300, 0, 0);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public class startButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			
			/** Get user text */
			userInputString = userInput.getText();
			
			if(serverIsWorking) {
				
				if(isInteger(userInputString)) {
					
					/** Set text on result field */
					resultField.setForeground(Color.black);
					resultField.setText(userInputString);
				
				
					/** Send MyFrame object to Logic class */
					Logic logic = new Logic();
					logic.logic(MyFrame.this, userInputString);
					
				}else if(isInteger(userInputString)==false) {
					resultField.setForeground(Color.gray);
					resultField.setText("Input digits");
				}
				
			}else {
				resultField.setForeground(Color.gray);
				resultField.setText("-----");
			}
		
		}
	}
	
	public class cleanButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			
			/** Clear user text */
			userInputString = "";
			userInput.setText("");
			resultField.setText(userInputString);
		}
	}
	
	/** Below method checks what user input
	 *  User can type number from 0 to 9 and '.' char
	 *  If user type above, function return true
	 */
	
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if ( (c < '0' || c > '9') && c != '.') {	
	            return false;
	        }
	    }
	    return true;
	}
	
  	@Override
    public void keyPressed(KeyEvent e) {
  		int key = e.getKeyCode();
  		
	  	if(serverIsWorking) {	
	  		
	  	    if (key == KeyEvent.VK_ENTER) {
	           
	            /** Get user text */
				userInputString = userInput.getText();
				
				if(isInteger(userInputString)) {
					
					/** Set text on result field */
					resultField.setForeground(Color.black);
					resultField.setText(userInputString);
					
					/** Send MyFrame object to Logic class */
					Logic logic = new Logic();
					logic.logic(MyFrame.this, userInputString);
					
				}else if(isInteger(userInputString)==false) {
					resultField.setForeground(Color.gray);
					resultField.setText("Input digits");
				}	
	  	    }
	  	    
	  	}else {
	  		resultField.setForeground(Color.gray);
			resultField.setText("-----");
	  	}
  	}
 
    @Override
    public void keyReleased(KeyEvent e) {
    }
 
    @Override
    public void keyTyped(KeyEvent e) {
    }
	
}
