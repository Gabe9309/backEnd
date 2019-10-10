import java.util.ArrayList;
import java.util.Iterator;

public class Storage {

	
	private String question;
	private ArrayList<String> answers;
	private boolean visited;
	
	Storage(){
		this.question = null;
		this.answers = new ArrayList<String>();
		this.visited = false;
	}
	
	@Override
	public String toString() {
		
		return get_Question();
	}
	public void add_Question(String question) {
		
		this.question = question;
	}
	
	public String get_Question() {
		
		visited = true; 
		
		if(this.question != null) 
			return this.question;
		else
			return " ";
	}
	
	public void add_Answer(String answer) {
		answers.add(answer);
	}
	
	public Iterator<String> get_Answers_Iterator() {
		return answers.iterator();
	}
	
	public String merge_Answers() {
		StringBuffer mergedAnswers = new StringBuffer();
		
		Iterator<String> iterator = get_Answers_Iterator();
		while(iterator.hasNext()) {
			mergedAnswers.append(iterator.next());
		}
		
		return mergedAnswers.toString();
	}
	
	public String get_Answer(int index) {
		if(index < answers.size())
			return answers.get(index);
		else 
			return " ";
	}
	
	public boolean isVisited() {
		return visited;
	}

}
