package de.kaffeezusatz.automaton.finite;

import java.util.LinkedList;
import java.util.List;

public class FiniteAutomaton {
	private final FiniteState initial;
	
	public FiniteAutomaton(final FiniteState initial) {
		this.initial = initial;
	}
	
	public FiniteState getInitial() {
		return this.initial;
	}
	
	public Result testWord(final String word) {
		return testWord(getInitial(), word, 0, new Result(word));
	}
	
	private Result testWord(final FiniteState state, final String word, final Integer index, final Result result) {
		result.addState(state);
		
		if (index >= word.length()) {
			return result;
		}
		
		final List<FiniteState> states = state.getState(word.charAt(index));
		
		Result nextResult = null;
		for (FiniteState next : states) {
			nextResult = testWord(next, word, index+1, result.clone());
			if (nextResult.isValid()) {
				return nextResult;
			}
		}
		
		return nextResult;
	}
	
	protected class Result implements Cloneable {
		private String word;
		private List<FiniteState> states;

		public Result(final String word) {
			this.word = word;
			this.states = new LinkedList<FiniteState>();
		}

		public Boolean isValid() {
			return states.get(states.size()-1).isFinal();
		}

		public FiniteState addState(FiniteState state) {
			this.states.add(state);
			return state;
		}
		
		public List<FiniteState> getStates() {
			return states;
		}

		public String getWord() {
			return word;
		}
		
		@Override
		public Result clone() {
			Result result = new Result(getWord());
			for (FiniteState state : states) {
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
