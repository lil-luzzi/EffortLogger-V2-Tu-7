package effortLoggerv2;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

public class PlanningPoker{
	private int players;
	private int round;
	private int playersVoted;
	private UserStory currentStory;
	private ArrayList<Integer> scores;
	private ObservableList<UserStory> userStories;
	
	public PlanningPoker(ObservableList<UserStory> userStories) {
		this.players = 1;
		this.round = 1;
		this.scores = new ArrayList<>();
		this.playersVoted = 0;
		this.userStories = userStories;
	}
	
	public boolean hasUnactionedStories() {
	    for (UserStory story : userStories) {
	        if (!story.isActioned())
	            return true;
	    }
	    return false;
	}
	
	public UserStory findUnactionedStory() {
		for(UserStory story : userStories) {
			if(!story.isActioned())
				return story;
		}
		return null;
	}
	
	public int minScore() {
		int min = scores.get(0);
		
		for(int i = 1; i < scores.size(); i++) {
			if(scores.get(i) < min)
				min = scores.get(i);
		}
		return min;
	}
	
	public int maxScore() {
		int max = scores.get(0);
		
		for(int i = 1; i < scores.size(); i++) {
			if(scores.get(i) > max)
				max = scores.get(i);
		}
		return max;
	}
	
	public int getRoundStdDev() {
		
		int average = getRoundAverage();
		int stdDev = 0;
		
		for(int i = 0; i < scores.size(); i++) {
			stdDev += (int) Math.pow(scores.get(i) - average,2);
		}
		
		return (int) Math.sqrt(stdDev/scores.size());
	}
	
	public int getRoundAverage() {
		int avg = 0;
		
		for(int i = 0; i < scores.size(); i++) {
			avg += scores.get(i);
		}
		
		return (avg/scores.size());
	}
	
	public boolean allTheSame() {
		int comp = scores.get(0);
		for(int i = 1; i < scores.size(); i++) {
			if(comp != scores.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	public void addScores(int score) {
		scores.add(score);
	}
	
	public void clearScores() {
		scores.clear();
	}
	
	public UserStory getUserStory() {
		return currentStory;
	}
	
	public void setUserStory(UserStory currentStory) {
		this.currentStory = currentStory;
	}
	
	public int getPlayers() {
		return players;
	}
	
	public void setPlayers(int players) {
		this.players = players;
	}
	
	public int getPlayersVoted() {
		return playersVoted;
	}
	
	public void setPlayersVoted(int playersVoted) {
		this.playersVoted = playersVoted;
	}
	
	public void setRound(int round) {
		this.round = round;
	}
	
	public int getRound() {
		return round;
	}
};