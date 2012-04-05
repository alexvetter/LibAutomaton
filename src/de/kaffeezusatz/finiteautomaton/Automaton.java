package de.kaffeezusatz.finiteautomaton;

import java.util.LinkedList;
import java.util.List;

public class Automaton {
	private final State initial;
	
	public Automaton(final State initial) {
		this.initial = initial;
	}
	
	public State getInitial() {
		return this.initial;
	}
	
	public Result testWord(final String word) {
		return testWord(getInitial(), word, 0, new Result(word));
	}
	
	private Result testWord(final State state, final String word, final Integer index, final Result result) {
		result.addState(state);
		
		if (index >= word.length()) {
			return result;
		}
		
		final List<State> states = state.getState(word.charAt(index));
		
		Result nextResult = null;
		for (State next : states) {
			nextResult = testWord(next, word, index+1, result.clone());
			if (nextResult.isValid()) {
				return nextResult;
			}
		}
		
		return nextResult;
	}
	
	protected class Result implements Cloneable {
		private String word;
		private List<State> states;

		public Result(final String word) {
			this.word = word;
			this.states = new LinkedList<State>();
		}

		public Boolean isValid() {
			return states.get(states.size()-1).isFinal();
		}

		public State addState(State state) {
			this.states.add(state);
			return state;
		}
		
		public List<State> getStates() {
			return states;
		}

		public String getWord() {
			return word;
		}
		
		@Override
		public Result clone() {
			Result result = new Result(getWord());
			for (State state : states) {
				result.addState(state);
			}
			return result;
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
