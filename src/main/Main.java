package main;

import chatbot.ChatbotGUI;
import chatbot.Chatbot;

public class Main {

	public static void main(String[] args)  {
		
		//create your chatbot (user name, bot name)
		Chatbot nowChatbot = new Chatbot("Francesca", "TestBot");
		
		//create a GUI that connects to your chatbot
		ChatbotGUI nowChatbotGUI = new ChatbotGUI(nowChatbot);

	}

}