package chatbot.infrastructure;

import java.util.Hashtable;

import chatbot.components.DomainClassifier;
import chatbot.components.HealthcareIntentClassifier;
import chatbot.components.SlotFiller;
import chatbot.components.DialogueManager;

public class Chatbot {
	
	private String userName = "Francesca Ansell";
	private String botName = "HealthBot";
	
	private DomainClassifier nowDomainClassifier;
	
	private HealthcareIntentClassifier healthcareIntentClassifier;

	private SlotFiller nowSlotFiller;

	private DialogueManager nowDialogueManager;
	
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
		
		this.nowDomainClassifier = new DomainClassifier();
		
		this.healthcareIntentClassifier = new HealthcareIntentClassifier();

		this.nowSlotFiller = new SlotFiller();

        this.nowDialogueManager = new DialogueManager();
	
	}
	
	public String getResponse(String nowInputText) {
		
		System.out.println("--------------");
		System.out.println("User Message: "+nowInputText);
		String nowDomain = nowDomainClassifier.getLabel(nowInputText);
		System.out.println("Domain: "+nowDomain);
		String nowIntent = "";
		Hashtable<String, String> extractedSlotValues = null;
        extractedSlotValues = nowSlotFiller.extractSlotValues(nowInputText);

		if(!nowDomain.equals("Other")) {

			if(nowDomain.equals("Healthcare")) {//Healthcare domain

				nowIntent = healthcareIntentClassifier.getLabel(nowInputText);
				
			}else {//this shouldn't happen
				System.err.println("Domain name is incorrect!");
				System.exit(1);
				return null;

			}
		} else {//out-of-domain message
			//return "This message is out of the domains of the chatbot.";
		}

		//Dialogue Management
		String nextState = nowDialogueManager.getNextState(nowDomain, nowIntent, extractedSlotValues);
		String nowResponse = nowDialogueManager.executeStateAndGetResponse(nextState);
		
		return nowResponse;
		
		
	}

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
