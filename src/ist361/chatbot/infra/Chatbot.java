package ist361.chatbot.infra;

import java.util.Hashtable;

import ist361.chatbot.component.DomainClassifier;
import ist361.chatbot.component.HealthcareIntentClassifier;
import ist361.chatbot.component.SlotFiller;
public class Chatbot {
	
	private String userName = "Francesca Ansell";
	private String botName = "HealthBot";
	
	private DomainClassifier nowDomainClassifier;
	
	private HealthcareIntentClassifier healthcareIntentClassifier;

	private SlotFiller nowSlotFiller;
	
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
		
		this.nowDomainClassifier = new DomainClassifier();
		
		this.healthcareIntentClassifier = new HealthcareIntentClassifier();

		this.nowSlotFiller = new SlotFiller();
	
	}
	
	public String getResponse(String nowInputText) {
		
		System.out.println("--------------");
		System.out.println("User Message: "+nowInputText);
		String nowDomain = nowDomainClassifier.getLabel(nowInputText);
		System.out.println("Domain: "+nowDomain);
		if(!nowDomain.equals("Other")) {//in-domain message

			Hashtable<String, String> extractedSlotValues = nowSlotFiller.extractSlotValues(nowInputText);

			if(nowDomain.equals("Healthcare")) {//Healthcare domain

				String nowIntent = healthcareIntentClassifier.getLabel(nowInputText);
				String nowResponse = "domain = "+nowDomain+", intent = "+nowIntent;
				nowResponse += slotTableToString(extractedSlotValues);
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

	/*
	 * new for assignment 5
	 * 
	 * [Input] A Hashtable<String, String> returned by extractSlotValues() in
	 * SlotFiller.java
	 * 
	 * [Output] A string that list all the extracted slot values
	 * 
	 */
	private String slotTableToString(Hashtable<String, String> extractedSlotValues) {
		
		String result = " (";
		
		for(String nowKey: extractedSlotValues.keySet()) {
			
			String nowValue = extractedSlotValues.get(nowKey);
			System.out.println(nowKey+"="+nowValue);
			result += nowKey+"="+nowValue+"; ";
		}
		
		result += ")";
		
		return result;
		
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
