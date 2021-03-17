package ist361.chatbot.infra;

import ist361.chatbot.component.DomainClassifier;
import ist361.chatbot.component.HealthcareIntentClassifier;
import ist361.chatbot.component.UserIntentClassifier;

public class Chatbot {
	
	private String userName = "Francesca Ansell";
	private String botName = "HealthBot";
	
	private DomainClassifier nowDomainClassifier;
	
	private HealthcareIntentClassifier healthcareIntentClassifier;
	private UserIntentClassifier userIntentClassifier;
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
		
		this.nowDomainClassifier = new DomainClassifier();
		
		this.healthcareIntentClassifier = new HealthcareIntentClassifier();
		this.userIntentClassifier = new UserIntentClassifier();	
	}
	
	public String getResponse(String nowInputText) {
		
		System.out.println("--------------");
		System.out.println("User Message: "+nowInputText);
		String nowDomain = nowDomainClassifier.getLabel(nowInputText);
		System.out.println("Domain: "+nowDomain);
		if(!nowDomain.equals("Other")) {//in-domain message
			System.out.println("Check1");
			if(nowDomain.equals("Healthcare")) {//Healthcare domain
				String nowIntent = healthcareIntentClassifier.getLabel(nowInputText);
				String nowResponse = "domain = "+nowDomain+", intent = "+nowIntent;
				return nowResponse;
			}else if(nowDomain.equals("User")) {//User domain
				String nowIntent = userIntentClassifier.getLabel(nowInputText);
				String nowResponse = "domain = "+nowDomain+", intent = "+nowIntent;
				return nowResponse;
				
			}else {//this shouldn't happen
				System.err.println("Domain name is incorrect!");
				System.exit(1);
				return null;
			}
		}else {//out-of-domain message
			return "This message is out of the domains of the chatbot.";
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
