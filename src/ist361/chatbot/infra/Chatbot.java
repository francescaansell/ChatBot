package ist361.chatbot.infra;

import ist361.chatbot.component.DomainClassifier;
import ist361.chatbot.component.FoodIntentClassifier;
import ist361.chatbot.component.WeatherIntentClassifier;

public class Chatbot {
	
	private String userName = "Francesca Ansell";
	private String botName = "HealthBot";
	
	//one domain classifier
	private DomainClassifier nowDomainClassifier;
	
	//each domain has one intent classifier 
	private WeatherIntentClassifier weatherIntentClassifier;
	private FoodIntentClassifier foodIntentClassifier;
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
		
		this.nowDomainClassifier = new DomainClassifier();
		
		this.weatherIntentClassifier = new WeatherIntentClassifier();
		this.foodIntentClassifier = new FoodIntentClassifier();
		
	}
	
	public String getResponse(String nowInputText) {
		
		System.out.println("--------------");
		System.out.println("User Message: "+nowInputText);
		String nowDomain = nowDomainClassifier.getLabel(nowInputText);
		System.out.println("Domain: "+nowDomain);
		if(!nowDomain.equals("Other")) {//in-domain message
			if(nowDomain.equals("Food")) {//Food domain
				String nowIntent = foodIntentClassifier.getLabel(nowInputText);
				String nowResponse = "domain = "+nowDomain+", intent = "+nowIntent;
				return nowResponse;
			}else if(nowDomain.equals("Weather")) {//Weather domain
				String nowIntent = weatherIntentClassifier.getLabel(nowInputText);
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
