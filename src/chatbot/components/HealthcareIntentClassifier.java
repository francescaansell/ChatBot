package chatbot.components;

import java.util.ArrayList;

public class HealthcareIntentClassifier {
	
	private static String[] intentDictionary;
	
	public HealthcareIntentClassifier() {
		initializeIntentDictionary();
	}
	
	/*
	 * Initialize the intent dictionary
	 */
	private void initializeIntentDictionary() {
		intentDictionary = new String[]{"Appointment", "Refill","FindPhysican" };
        
		System.out.print("Intents: (");
		for(int i=0;i<intentDictionary.length;i++) {
			System.out.print(intentDictionary[i]);
			if(i!=intentDictionary.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println(")");
	}
	
	/*
	 * Calculate the score for each intent
	 * 
	 * [Input] One user message
	 * 
	 * [Output] A Double array that has the score of each intent. The scores
	 * can simply be 0.0 or 1.0, can be probabilities (from 0.0 to 1.0), or
	 * any scores you design. Later the getLabel() method will select the intent 
	 * with *highest* score. 
	 */
	private Double[] calculateIntentScores(String nowInputText) {
		Double[] scoreArray = new Double[intentDictionary.length];
        for(int i=0; i < scoreArray.length; i++){
            scoreArray[i] = new Double(0.0);
        }

		//Intent 1 appointment
		ArrayList<String> appointmentDictionary = generateDates();
        //String[] appointmentDictionary  = new String[] {"appointment", "schedule"};
		appointmentDictionary.add("appointment");
		appointmentDictionary.add("schedule");
		for(String appointmentKeyword: appointmentDictionary) {
			if (nowInputText.toLowerCase().indexOf(appointmentKeyword.toLowerCase())>=0) {
				scoreArray[0] = new Double(scoreArray[0])+1;
			}
		}

		//Intent 2 refill
		String[] refillDictionary = new String[] {"refill", "prescription","Levothyroxine", "Lisinopril", "Atorvastatin", "Metformin", "Amlodipine", "Metoprolol", 
		"Omeprazole", "Simvastatin", "Losartan", "Albuterol", "Gabapentin", "Hydrochlorothiazide", "Hydrocodone", "Zoloft", "Furosemide", "Celexa", 
		"Xanax", "Acetaminophen", "Insulin glargine", "Trazodone", "Montelukast", "Protonix", "Lexapro", "Pravachol", "Bupropion", "Prozac", "Carvedilol", 
		"Prednisone", "Flomax", "Potassium", "Clopidogrel", "Klonopin", "Zolpidem", "Tramadol", "Rosuvastatin", "Meloxicam", "Propranolol", "Glipizide", "Zantac", 
		"Zithromax", "Cymbalta" , "Methylphenidate" , "Philadelphia" , "Pittsburgh", "Allentown", "Erie", "Reading", "Bethlehem", "Scranton", 
		"Lancaster", "Harrisburgh", "York", "Altoona", "Wildes-Barre", "Chester", "Williamsport", "McCandless", "Easton", "Lebanon", "Hazleton", 
		"New Castle", "Johnstown", "McKeesport", "Hermitage", "Hermitage", "Bloomsburg", "Greensburg", "Washington", "Pottsvilla", "Sharon", "Coatesvilla", 
		"Butler", "Meadville", "New Kensington", "St. Marys", "Lower Burrell", "Nanticoke", "Oil City", "Uniontown", "Sunburry", "Lock Haven", "Warren", 
		"Jeannette", "Aligquippa", "Du Bois", "Carbondale", "Latrobe", "Beaver Falls", "Bradford", "Pittston", "connellsville", "Monessen", "Shamokin", 
		"Clariton", "Corry", "Franklin", "Duqeusne", "Titusville", "Arnold", "Farrell", "Monongahela", "Parker"};
		for(String refillKeyword: refillDictionary) {
			if (nowInputText.toLowerCase().indexOf(refillKeyword.toLowerCase())>=0) {
				scoreArray[1] = new Double(scoreArray[1])+1;
			}
		}
		
		//Intent 3 findPhysican
        String[] findPhysicanDictionary = new String[] {"doctor", "find a doctor", "Family", "Primary Care", "Internal Medicine", "Pediatrician", "Gynecologist", "Surgeon", "Psychiatrist", "Cardiologist", 
		"Dermatologist", "Endocrinologist", "Gastroenterologist", "Infectious Disease", "Nephrologist", "Ophthalmaologist", "Otaolargyngologist", "Pulmonologist", 
		"Neurologist", "Oncologist", "Philadelphia" , "Pittsburgh", "Allentown", "Erie", "Reading", "Bethlehem", "Scranton", 
		"Lancaster", "Harrisburgh", "York", "Altoona", "Wildes-Barre", "Chester", "Williamsport", "McCandless", "Easton", "Lebanon", "Hazleton", 
		"New Castle", "Johnstown", "McKeesport", "Hermitage", "Hermitage", "Bloomsburg", "Greensburg", "Washington", "Pottsvilla", "Sharon", "Coatesvilla", 
		"Butler", "Meadville", "New Kensington", "St. Marys", "Lower Burrell", "Nanticoke", "Oil City", "Uniontown", "Sunburry", "Lock Haven", "Warren", 
		"Jeannette", "Aligquippa", "Du Bois", "Carbondale", "Latrobe", "Beaver Falls", "Bradford", "Pittston", "connellsville", "Monessen", "Shamokin", 
		"Clariton", "Corry", "Franklin", "Duqeusne", "Titusville", "Arnold", "Farrell", "Monongahela", "Parker"};
		for(String findPhyiscanKeyword: findPhysicanDictionary) {
			if (nowInputText.toLowerCase().indexOf(findPhyiscanKeyword.toLowerCase())>=0) {
				scoreArray[2] = new Double(scoreArray[2])+1;
			}
		}

		if(scoreArray.length!=intentDictionary.length) {
			System.err.println("The score array size does not equal to the intent array size.");
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
		Double[] intentScores = calculateIntentScores(nowInputText);
		Double nowMaxScore = null;
		int nowMaxIndex = -1;
		System.out.print("Intent Scores: (");
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
		return intentDictionary[nowMaxIndex];
	}
	public ArrayList<String> generateDates() {
		ArrayList<String> dates = new ArrayList<>();
	
		//A date is month + "/" + day + "/" + year
		for(int tempYear= 2021; tempYear <= 2023; tempYear ++) {
			for(int tempMonth=1; tempMonth<=12; tempMonth++){
				if(tempMonth == 2){
					//Febuary has 28 days
					for(int tempDay=1; tempDay<=28; tempDay ++){
						String tempDate = tempMonth + "/" + tempDay + "/" + tempYear;
						dates.add(tempDate);
					}
				} else if(tempMonth % 2 != 0 ){
					//if a month is odd it has 31 days
					for(int tempDay=1; tempDay<= 31; tempDay++){
						String tempDate = tempMonth + "/" + tempDay + "/" + tempYear;
						dates.add(tempDate);
					}
				} else if (tempMonth % 2 ==0){
					//if a month is even it has 30 days
					for(int tempDay = 1; tempDay<=30; tempDay++){
						String tempDate = tempMonth + "/" + tempDay + "/" + tempYear;
						dates.add(tempDate);
					}
				}
				/*
				for(String date: dates){
					System.out.println("DATE: " + date);
				}
				*/
			}
	
		}
		return dates;
	}
}

