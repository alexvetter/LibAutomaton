package de.kaffeezusatz.finiteautomaton;

import java.util.LinkedList;
import java.util.List;

public class Automat {
	public static final State NULL_STATE = new State("null");
	
	private final State initial;
	
	public Automat(final State initial) {
		this.initial = initial;
	}
	
	public State getInitial() {
		return this.initial;
	}
	
	public Result testWord(final String word) {
		State current = getInitial();

		final List<State> states = new LinkedList<State>();
		
		for (int i = 0; i < word.length(); i++) {
			states.add(current);
			
			char c = word.charAt(i);
			
			State next = current.getTransition(c);
			if (next == null) {
				next = NULL_STATE;
			}
			
			current = next;
		}
		states.add(current);
		
		return new Result(word, current.isFinal(), states);
	}
	
	protected class Result {
		private String word;
		private Boolean isValid;
		private List<State> states;

		public Result(final String word, final Boolean isValid, final List<State> states) {
			this.word = word;
			this.isValid = isValid;
			this.states = states;
		}

		public Boolean isValid() {
			return isValid;
		}

		public List<State> getStates() {
			return states;
		}

		public String getWord() {
			return word;
		}
		
		public String toString() {
			StringBuffer string = new StringBuffer();
			for (int i = 0; i < word.length(); i++) {
				string.append("[");
				string.append(states.get(i).getName());
				string.append("]");
				string.append(" -- ");
				string.append(word.charAt(i));
				string.append(" --> ");
			}
			
			string.append("[");
			string.append(states.get(word.length()).getName());
			string.append("]");
			
			return string.toString();
		}
	}
}
