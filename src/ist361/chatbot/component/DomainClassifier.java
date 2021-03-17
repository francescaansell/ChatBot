package ist361.chatbot.component;

public class DomainClassifier {
	
	private static String[] domainDictionary;
	
	public DomainClassifier() {
		initializeDomainDictionary();
	}
	
	/*
	 * Task 1-1: Initialize the domain dictionary (you MUST have an "Other" domain)
	 */
	private void initializeDomainDictionary() {
		//domainDictionary = new String[]{"Other", "Weather", "Food"};
		domainDictionary = new String[]{"Other", "Healthcare", "User"};
		System.out.print("Domains: (");
		for(int i=0;i<domainDictionary.length;i++) {
			System.out.print(domainDictionary[i]);
			if(i!=domainDictionary.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println(")");
	}
	
	/*
	 * Task 1-2: Calculate the score for each domain
	 * 
	 * [Input] One user message
	 * 
	 * [Output] A double array that has the score of each domain. The scores
	 * can simply be 0.0 or 1.0, can be probabilities (from 0.0 to 1.0), or
	 * any scores you design. Later the getLabel() method will select the domain 
	 * with *highest* score. 
	 */
	private Double[] calculateDomainScores(String nowInputText) {
		//do not change the following 4 lines
		Double[] scoreArray = new Double[domainDictionary.length];
		/* 
		for(Double nowValue: scoreArray) {
			nowValue = 0.0;
		}
		*/
		for(int i=0; i<scoreArray.length; i++){
			scoreArray[i] = new Double(0.0);
		}
	
		//The following is the part you need to modify. 

		/* This current version just assign random scores to each domain.
		for(int i=0;i<scoreArray.length;i++) {
			scoreArray[i] = Math.random();
		}
		*/ 

		/*Example from class video 
		String[] weatherDictionary = new String[] {"snow", "rain", "weather"};
		for(String weatherKeyword: weatherDictionary) {
			if (nowInputText.toLowerCase().indexOf(weatherKeyword)>=0) {
				scoreArray[1] = new Double(scoreArray[1])+1;
			}
		}

		String[] foodDictionary = new String[] {"food", "eat", "hungry"};
		for(String foodKeyword: foodDictionary) {
			if (nowInputText.toLowerCase().indexOf(foodKeyword)>=0) {
				scoreArray[2] = new Double(scoreArray[2])+1;
			}
		}
		*/

		String[] healthcareDictionary = new String[] {"appointment", "refill", "prescription", "doctor"};
		for(String healthcareKeyword: healthcareDictionary) {
			if (nowInputText.toLowerCase().indexOf(healthcareKeyword)>=0) {
				scoreArray[1] = new Double(scoreArray[1])+1;
			}
		}

		String[] userDictionary = new String[] {"name", "birthday"};
		for(String userKeyword: userDictionary) {
			if (nowInputText.toLowerCase().indexOf(userKeyword)>=0) {
				scoreArray[2] = new Double(scoreArray[2])+1;
			}
		}
	
		
		//do not change the following lines
		if(scoreArray.length!=domainDictionary.length) {
			System.err.println("The score array size does not equal to the domain array size.");
			System.exit(1);
		}
		for(Double nowValue: scoreArray) {
			if(nowValue==null) {
				System.err.println("The score array contains null values.");
				System.exit(1);
			}
		}
		return scoreArray;
	}
	
	/*
	 * Input: One user message
	 * Output: The intent with the highest score calculated by calculateIntentScores()
	 */
	public String getLabel(String nowInputText) {
		Double[] intentScores = calculateDomainScores(nowInputText);
		Double nowMaxScore = null;
		int nowMaxIndex = -1;
		System.out.print("Domain Scores: (");
		for(int i=0;i<intentScores.length;i++){
			System.out.print(intentScores[i].doubleValue());
			if(i!=intentScores.length-1) {
				System.out.print(", ");
			}
			if(nowMaxScore==null||nowMaxIndex==-1||intentScores[i].doubleValue()>nowMaxScore.doubleValue()) {
				nowMaxIndex = i;
				nowMaxScore = intentScores[i].doubleValue();
			}
		}
		System.out.println(")");
		return domainDictionary[nowMaxIndex];
	}

}
