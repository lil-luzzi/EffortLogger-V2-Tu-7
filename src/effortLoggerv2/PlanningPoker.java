package effortLoggerv2;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

public class PlanningPoker{
	private int players;
	private UserStory currentStory;
	private List<Integer> scores;
	private boolean aggreed;
	private ObservableList<UserStory> userStories;
	
	public PlanningPoker(int players, ObservableList<UserStory> userStories) {
		this.players = players;
		this.scores = new ArrayList<>();
		this.aggreed = false;
	}
	
	public void startRound() {
		UserStory story = findUnactionedStory();
		if(story != null) {
			this.currentStory = story;
			this.scores.clear();
			this.aggreed = false;
		}
		else {
			
		}
	}
	
	private UserStory findUnactionedStory() {
		for(UserStory story : userStories) {
			if(!story.isActioned())
				return story;
		}
		return null;
	}
}