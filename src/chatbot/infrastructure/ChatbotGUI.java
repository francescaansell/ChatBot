/**
 * @author Francesca Ansell
 * Sources: 
 * https://stackoverflow.com/questions/42855224/how-to-add-rgb-values-into-setcolor-in-java
 * https://stackoverflow.com/questions/15393385/how-to-change-text-color-of-a-jbutton
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
 * https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/text/StyleConstants.html
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/BorderFactory.html
 */
package chatbot.infrastructure; 

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ChatbotGUI extends JFrame {

	static private Chatbot nowChatbot;
	
	static private JFrame nowGUIFrame;
	
	static private JTextField inputTextBox;
	static private JTextPane chatHistoryPane;
	//static private JScrollPane scroll;
	
	static private JButton psuButton;

	
	public ChatbotGUI(Chatbot nowChatbot) {
		
		this.nowChatbot = nowChatbot;

		Color blueCrayola = new Color(62, 146, 204);
		Color royalBlue = new Color(10, 36, 99);
		Color magnolia = new Color(255, 250, 255);
		Color cerise = new Color(216, 49, 91);
		Color blackChocolate = new Color(30, 27, 24);
	
		
		//create the frame of chatbot
		nowGUIFrame = new JFrame();
		nowGUIFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		nowGUIFrame.setVisible(true);
		nowGUIFrame.setResizable(false);
		nowGUIFrame.setLayout(null);
		nowGUIFrame.setSize(640, 900);
		nowGUIFrame.setTitle("Chatbot "+nowChatbot.getBotName()+"");
		nowGUIFrame.getContentPane().setBackground(blueCrayola);

		//TODO: icon image
		//ImageIcon icon = new ImageIcon("resources\sunflower.ico");
		//nowGUIFrame.setIconImage(icon.getImage());

		
		//create JTextPane
		chatHistoryPane = new JTextPane();
		nowGUIFrame.add(chatHistoryPane);
		chatHistoryPane.setSize(600, nowGUIFrame.getHeight()-100);
		chatHistoryPane.setLocation(10, 5);
		chatHistoryPane.setBackground(magnolia);
		chatHistoryPane.setBorder(BorderFactory.createMatteBorder(2, 2, 8, 8, blackChocolate));
	

		//TODO: scrollable
	
		//scroll = new JScrollPane(chatHistoryPane);
		//scroll.setSize(600, nowGUIFrame.getHeight()-100);
		//scroll.setLocation(10, 5);
		//scroll.setBackground(magnolia);
		//scroll.setBorder(BorderFactory.createMatteBorder(2, 2, 8, 8, blackChocolate));
		//nowGUIFrame.add(scroll);
		
		
		//create JTextField
		inputTextBox = new JTextField();
		nowGUIFrame.add(inputTextBox);
		inputTextBox.setSize(450, 30);
		inputTextBox.setLocation(10, chatHistoryPane.getHeight() + 10);
		inputTextBox.addActionListener(new InputTextListener(inputTextBox, chatHistoryPane, this));
		inputTextBox.setBackground(magnolia);
		inputTextBox.setBorder(BorderFactory.createLoweredBevelBorder());
		inputTextBox.setBackground(magnolia);
	
		
		
		//create JButton
		psuButton = new JButton("Send!", null);
		nowGUIFrame.add(psuButton);
		psuButton.setSize(140, 30);
		psuButton.setLocation(inputTextBox.getWidth() + 20, chatHistoryPane.getHeight() + 10);
		psuButton.addActionListener(new ButtonListener(inputTextBox, chatHistoryPane, this));
		psuButton.setBackground(royalBlue);
		psuButton.setForeground(magnolia);
		psuButton.setBorder(BorderFactory.createRaisedBevelBorder());   	
	}

	public ChatbotGUI() {	}
	
	public Chatbot getChatbot() {
		return nowChatbot;
	}
	
	public static void appendToPane(JTextPane nowPane, String senderName, String message, Color color){
		
		String nowMsg = senderName+": "+message+"\n";
		
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "MONOSPACED");
		aset = sc.addAttribute(aset, StyleConstants.FontSize, 16);
		aset = sc.addAttribute(aset, StyleConstants.Bold, true);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = nowPane.getDocument().getLength();
        nowPane.setCaretPosition(len);
        nowPane.setCharacterAttributes(aset, false);
        nowPane.replaceSelection(nowMsg);
        
    }
	
}

class ButtonListener implements ActionListener{
	
	private ChatbotGUI chatbotUtil;
	private JTextField nowInputTextBox;
	
	//private JTextField nowInputTextBox;
	private JTextPane nowChatHistoryPane;

	Color blueCrayola = new Color(62, 146, 204);
	Color royalBlue = new Color(10, 36, 99);
	Color magnolia = new Color(255, 250, 255);
	Color cerise = new Color(216, 49, 91);
	Color blackChocolate = new Color(30, 27, 24);
	
	public ButtonListener(JTextField inputTextBox, JTextPane chatHistoryPane, ChatbotGUI chatbotUtil) {
		this.chatbotUtil = chatbotUtil;
		nowInputTextBox = inputTextBox;
		nowChatHistoryPane = chatHistoryPane;
		//nowUserName = userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//Used same logic from InputTextListener class  so that when send button is clicked the same action is "enter"
		String nowInputText = nowInputTextBox.getText();
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), nowInputText, cerise );
		
		String nowChatbotResponse = chatbotUtil.getChatbot().getResponse(nowInputText);
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), nowChatbotResponse, blackChocolate);
		
		//Reset text box. You can keep this line of code here. 
		nowInputTextBox.setText("");
	}
}

class InputTextListener implements ActionListener{
	
	private ChatbotGUI chatbotUtil;
	
	private JTextField nowInputTextBox;
	private JTextPane nowChatHistoryPane;

	Color blueCrayola = new Color(62, 146, 204);
	Color royalBlue = new Color(10, 36, 99);
	Color magnolia = new Color(255, 250, 255);
	Color cerise = new Color(216, 49, 91);
	Color blackChocolate = new Color(30, 27, 24);
	
	public InputTextListener(JTextField inputTextBox, JTextPane chatHistoryPane, ChatbotGUI chatbotUtil) {
		this.chatbotUtil = chatbotUtil;
		nowInputTextBox = inputTextBox;
		nowChatHistoryPane = chatHistoryPane;
		//nowUserName = userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nowInputText = nowInputTextBox.getText();
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), nowInputText, cerise);
		
		String nowChatbotResponse = chatbotUtil.getChatbot().getResponse(nowInputText);
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), nowChatbotResponse, blackChocolate);
		
		nowInputTextBox.setText("");
		
	}
}
	


