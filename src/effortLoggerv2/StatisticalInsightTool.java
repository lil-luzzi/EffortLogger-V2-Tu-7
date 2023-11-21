//This file was made by Joseph Prainito

package effortLoggerv2;

import javafx.collections.ObservableList;

public class StatisticalInsightTool{
	
	//Takes historical data of user stories and uses that to perform functions on that data
	private ObservableList<UserStory> historicalData;
	
	public StatisticalInsightTool(ObservableList<UserStory> historicalData) {
		this.historicalData = historicalData;
	}
	
	public double calculateAverage() {
		double total = 0;
		int size = 0;
		for(UserStory story : historicalData) {
			total += Integer.parseInt(story.getEstimateStoryPoints());	//Average point scores
			size++;
			if(!story.getActualPointScore().equals("Not Assigned")) {
				total += Integer.parseInt(story.getActualPointScore());
				size++;
			}
			
		}
		return total / size;
	}
	
	public int calculateStandardDeviation() {
		
		int sum = 0;
		int stdDev = 0;
		
		for(UserStory story : historicalData) {
			sum += Integer.parseInt(story.getEstimateStoryPoints());		//Standard deviation
		}	
		
		int average = sum/historicalData.size();
		
		for(UserStory story : historicalData) {
			stdDev += (int) Math.pow(Integer.parseInt(story.getEstimateStoryPoints()) - average,2);
		}
		
		return (int) Math.sqrt(stdDev/historicalData.size());
	}
	
public int calculateStandardDeviationByType(UserStory currentStory) {
		
		int sum = 0;
		int size = 0;
		int stdDev = 0;
		
		for(UserStory story : historicalData) {
			if(currentStory.getTypeOfUser() == story.getTypeOfUser()) {
				sum += Integer.parseInt(story.getEstimateStoryPoints());		//std dev by type
				size++;
			}
		}
		
		int average = sum/size;
		
		for(UserStory story : historicalData) {
			if(currentStory.getTypeOfUser() == story.getTypeOfUser()) {
			stdDev += (int) Math.pow(Integer.parseInt(story.getEstimateStoryPoints()) - average,2);
			}
		}
		
		return (int) Math.sqrt(stdDev/size);
	}
	
	public int calculateAverageByType(UserStory currentStory) {
		double total = 0;
		int size = 0;
		
		for(UserStory story : historicalData) {
			if(currentStory.getTypeOfUser() == story.getTypeOfUser()) {		//Average by type
				total += Integer.parseInt(story.getEstimateStoryPoints());
				size++;
			}
		}
		if(size == 0)
			return Integer.parseInt(currentStory.getEstimateStoryPoints());
		else
			return (int) (total/size);
	}
	
	public int calcEstimate(UserStory currentStory) {
	    int baseEstimate = getBaseEstimateFromPriority(currentStory.getPriority());
	    double averageByPriority = getAverageByPriority(currentStory.getPriority());
	    
	    if(averageByPriority == 0)
	    	return baseEstimate;
	    else
	    	return limitStoryPoints((int) Math.round(averageByPriority));
	}
	
	public int getBaseEstimateFromPriority(String priority) {
	    switch (priority) {
	        case "High":
	            return 500; // Base estimate for high priority
	        case "Medium":
	            return 300; // Base estimate for medium priority
	        default:
	            return 100; // Base estimate for low/default priority
	    }
	}
	
	public double getAverageByPriority(String priority) {
	    int average = 0;
	    int count = 0;

	    for (UserStory story : historicalData) {
	        if(story.getPriority().equals(priority)) {
	        	average += Integer.parseInt(story.getEstimateStoryPoints());
	        	count++;
	        	if(!story.getActualPointScore().equals("Not Assigned")) {
	        		average += Integer.parseInt(story.getActualPointScore());
	        		count++;
	        	}
	        }
	    }
	    if(count == 0)
	    	return 0;
	    else
	    	return average/count;
	}
	public int limitStoryPoints(int points) {
		return Math.min(Math.max(points, 100), 1000);	//Keeps score between 100-1000, fix 1
	}
}