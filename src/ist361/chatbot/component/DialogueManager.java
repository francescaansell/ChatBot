package ist361.chatbot.component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import ist361.chatbot.state.AbstractDialogueState;
import ist361.chatbot.state.HealthcareState;
import ist361.chatbot.state.StartState;


public class DialogueManager {
	
	private List<String> dialogueStateHistory;
	
	private List<String> domainHistory;
	private List<String> intentHistory;
	private List<Hashtable<String, String>> slotHistory;
	
	private Hashtable<String, AbstractDialogueState> dialogueStateTable;
	
	public DialogueManager() {
		
		this.dialogueStateHistory = new ArrayList<String>();
		dialogueStateHistory.add("StartState");
		
		this.domainHistory = new ArrayList<String>();
		domainHistory.add("StartDomain");
		
		this.intentHistory = new ArrayList<String>();
		intentHistory.add("StartIntent");
		
		this.slotHistory = new ArrayList<Hashtable<String, String>>();
		slotHistory.add(new Hashtable<String, String>());
		
		initializeDialogueStateTable();
		
	}
	
	private void initializeDialogueStateTable() {
		
		dialogueStateTable = new Hashtable<String, AbstractDialogueState>();
		
		/*
		 * Task: Put dialogue state in the table 
		 */
		dialogueStateTable.put("HealthcareState", new HealthcareState());
		dialogueStateTable.put("StartState", new StartState());
		
		
	}

	public String getNextState(String nowDomain, String nowIntent, Hashtable<String, String> extractedSlotValues) {
		
		System.out.println("State BEFORE Message: "+dialogueStateHistory.get(dialogueStateHistory.size()-1));
		
		//keep track of domain, intent, slot values
		domainHistory.add(nowDomain);
		intentHistory.add(nowIntent);
		slotHistory.add(extractedSlotValues);
		
		String nowNextStateLabel = calculateNextState(); 
		dialogueStateHistory.add(nowNextStateLabel);
		
		String latestState = dialogueStateHistory.get(dialogueStateHistory.size()-1);
		System.out.println("State AFTER Message: "+latestState);
		
		return latestState;
		
	}

	/*
	 * Task: Use all the information (including dialogueStateHistory, domainHistory,
	 * intentHistory, and slotHistory) to decide the next dialogue state.  
	 * 
	 */
	private String calculateNextState() {
		
		String lastestState = dialogueStateHistory.get(dialogueStateHistory.size()-1);
		
		//String latestIntent = intentHistory.get(intentHistory.size()-1);
		String latestDomain = domainHistory.get(domainHistory.size()-1);
		//Hashtable<String, String> latestSlotValues = slotHistory.get(slotHistory.size()-1);
		
		//modify the following logistics to decide when to go to which dialogue state
		
		if(dialogueStateHistory.size()>1) {
			//String prevIntent = intentHistory.get(intentHistory.size()-2);
			String prevDomain = domainHistory.get(domainHistory.size()-2);
			System.out.println(prevDomain+" -> "+latestDomain);
			if(prevDomain.equals(latestDomain)) {//Other->Other; Weather->Weather; Food->Food
				//just stay at the same state
				return lastestState;
			}else if(latestDomain.equals("Other")){//Weather->Other; Food->Other; etc
				//just stay at the same state
				return lastestState;
			}else {//domain changed to an non-Other state
				if(latestDomain.equals("Weather")) {//Start->Weather; Food->Weather
					return "WeatherState";
				}else {//Start->Food; Weather->Food
					return "FoodState";
				}
			}
		}else {//we only have an initial state, go to whatever the latest state is
			if(latestDomain.equals("Weather")) {
				return "WeatherState";
			}else if(latestDomain.equals("Food")) {
				return "FoodState";
			}else {
				return lastestState;
			}
		}
		
		
	}

	public String executeStateAndGetResponse(String nextState) {
       if(dialogueStateTable.get(nextState)!=null) {
    	   return dialogueStateTable.get(nextState).executeAndGetResponse(domainHistory, intentHistory, slotHistory);
       }else {//null, no dialogue state
    	   System.err.println("executeState error: can't find nextState. Make sure you initialized the state in initializeDialogueStateTable().");
    	   System.exit(1);
       }
       return null;
	}
	
	
	
	
	

}
