package chatbot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import chatbot.Chatbot;

public class ChatbotGUI extends JFrame {

	static private Chatbot nowChatbot;
	
	static private JFrame nowGUIFrame;
	
	static private JTextField inputTextBox;
	static private JTextPane chatHistoryPane;
	static private JScrollPane scroll;
	
	static private JButton psuButton;
	
	public ChatbotGUI(Chatbot nowChatbot) {
		
		this.nowChatbot = nowChatbot;
		
		/*
		 * == Task 1: Make the interface prettier! ==
		 * 
		 * === Description ===
		 * As you can see, this graphical interface (GUI) is not quite pretty. Please try to
		 * modify the following code to move the components around or change their appearance (
		 * color, size, etc.) to make this interface looks nicer. Please explain what you did in
		 * your video.
		 * 
		 */
		
		//create the frame of chatbot
		nowGUIFrame = new JFrame();
		nowGUIFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		nowGUIFrame.setVisible(true);
		nowGUIFrame.setResizable(false);
		nowGUIFrame.setLayout(null);
		nowGUIFrame.setSize(600, 600);
		nowGUIFrame.setTitle("Chatbot "+nowChatbot.getBotName()+"");
		
	
		
		//create JTextPane
		chatHistoryPane = new JTextPane();
		nowGUIFrame.add(chatHistoryPane);
		chatHistoryPane.setSize(500, 400);
		chatHistoryPane.setLocation(2, 2);
		chatHistoryPane.setBackground(Color.orange);

		
		//create JTextField
		inputTextBox = new JTextField();
		nowGUIFrame.add(inputTextBox);
		inputTextBox.setSize(540, 30);
		inputTextBox.setLocation(2, 410);
		inputTextBox.addActionListener(new InputTextListener(inputTextBox, chatHistoryPane, this));
		inputTextBox.setBackground(Color.cyan);
		
		/*
		DONE
		 * == Task 2: Connect the "Send" button to the chatbot and show the response on the chat
		 * pane. == 
		 * 
		 * === Description ===
		 * For now, clicking the "Send" button shows a message "Send" to the chat pane. Please
		 * modify the actionPerformed() method in the ButtonListener class so that clicking the
		 * button will:
		 * (1) pass the message that the user typed in the input box (the "user message") to the 
		 * chatbot and receive its response (the "bot response"),
		 * (2) display "[USER NAME]: [user message]" in the chat pane, and 
		 * (3) display "[BOT NAME]: [bot response]" in the chat pane.
		 * 
		 */
		
		//create JButton
		psuButton = new JButton("Send", null);
		nowGUIFrame.add(psuButton);
		psuButton.setBounds(2, 500, 140, 40);
		psuButton.addActionListener(new ButtonListener(inputTextBox, chatHistoryPane, this));
		
		/*
		 * == Task 3: Add a response in Chatbot.java to respond to user message. == 
		 * 
		 * === Description ===
		 * Please modify the getResponse() method in the Chatbot class to respond to at least
		 * three different user messages meaningfully. I already provided two examples in the
		 * getResponse().
		 *   
		 */
	    	
	}

	public ChatbotGUI() {
		
	}
	
	public Chatbot getChatbot() {
		return nowChatbot;
	}
	
	public static void appendToPane(JTextPane nowPane, String senderName, String message, Color color){
		
		String nowMsg = senderName+": "+message+"\n";
		
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "MONOSPACED");
        aset = sc.addAttribute(aset, StyleConstants.FontSize, 16);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = nowPane.getDocument().getLength();
        nowPane.setCaretPosition(len);
        nowPane.setCharacterAttributes(aset, false);
        nowPane.replaceSelection(nowMsg);
        
    }
	
	
}//end ChatbotGUI

class ButtonListener implements ActionListener{
	
	private ChatbotGUI chatbotUtil;
	private JTextField nowInputTextBox;
	
	//private JTextField nowInputTextBox;
	private JTextPane nowChatHistoryPane;
	
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
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), nowInputText, Color.BLUE);
		
		String nowChatbotResponse = chatbotUtil.getChatbot().getResponse(nowInputText);
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), nowChatbotResponse, Color.BLACK);
		
		//Reset text box. You can keep this line of code here. 
		nowInputTextBox.setText("");
		
	}
	
}

class InputTextListener implements ActionListener{
	
	private ChatbotGUI chatbotUtil;
	
	private JTextField nowInputTextBox;
	private JTextPane nowChatHistoryPane;
	
	public InputTextListener(JTextField inputTextBox, JTextPane chatHistoryPane, ChatbotGUI chatbotUtil) {
		this.chatbotUtil = chatbotUtil;
		nowInputTextBox = inputTextBox;
		nowChatHistoryPane = chatHistoryPane;
		//nowUserName = userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nowInputText = nowInputTextBox.getText();
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), nowInputText, Color.BLUE);
		
		String nowChatbotResponse = chatbotUtil.getChatbot().getResponse(nowInputText);
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), nowChatbotResponse, Color.BLACK);
		
		nowInputTextBox.setText("");
		
	}
	
	
}

