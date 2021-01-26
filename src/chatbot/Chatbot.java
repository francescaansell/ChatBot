package chatbot;

public class Chatbot {
	
	private String userName = "Francesca Ansell";
	private String botName = "TestBot";
	
	public Chatbot(String userName, String botName) {
		this.userName = userName;
		this.botName = botName;
	}
	
	public String getResponse(String nowInputText) {
		
        //Original response
		if(nowInputText.toUpperCase().contains("HI")||nowInputText.toUpperCase().contains("HELLO")) {
			return "Hi, nice to meet you :) I am "+botName+".";
        }
        
		//First response		
		if(nowInputText.toUpperCase().contains("HOW")||nowInputText.toUpperCase().contains("HOW ARE YOU TODAY")) {
			return "I am doing okay, how are you?" + nowInputText;
        }
        
		//Second response
		if (nowInputText.toUpperCase().contains("SAD")||nowInputText.toUpperCase().contains("UPSET")) {
			return "Don't be sad, be glad :)";
        }
        
		//Third response
		if(nowInputText.toUpperCase().contains("HAPPY")||nowInputText.toUpperCase().contains("GOOD")) {
			return "I am happy your happy!";
        }
        
		else {
			return nowInputText+" (ECHO)";
		}

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

}
