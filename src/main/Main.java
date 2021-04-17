package main;

import chatbot.infrastructure.ChatbotGUI;
import chatbot.infrastructure.Chatbot;

public class Main {

	public static void main(String[] args)  {
		
		//create your chatbot (user name, bot name)
		Chatbot nowChatbot = new Chatbot("Kenneth", "TestBot");
		
		//create a GUI that connects to your chatbot
		ChatbotGUI nowChatbotGUI = new ChatbotGUI(nowChatbot);

	}

}
