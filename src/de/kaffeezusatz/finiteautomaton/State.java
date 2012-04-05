package de.kaffeezusatz.finiteautomaton;

import java.util.LinkedList;
import java.util.List;

public class State {
	public static final State STATE_NULL = new State("null");
	
	private String name;
	private boolean isFinal = false;
	private List<Transition> transitions;
	
	public State(String name) {
		this.name = name;
		this.transitions = new LinkedList<Transition>();
	}
	
	public void setFinal() {
		this.isFinal = true;
	}
	
	public boolean isFinal() {
		return this.isFinal;
	}
	
	public String getName() {
		return this.name;
	}
	
	public State addTransition(State state, char... cs) {
		for (char c : cs) {
			Transition transition = new Transition(state, c);
			if (transitions.contains(transition)) {
				continue;
			}
			
			transitions.add(transition);
		}
		
		return this;
	}
	
	public List<State> getState(char c) {
		List<State> states = new LinkedList<State>();
		for (Transition transition : this.transitions) {
			if (transition.getChar() == c) {
				states.add(transition.getState());
			}
		}
		
		if (states.isEmpty()) {
			states.add(STATE_NULL);
		}
		
		return states;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isFinal ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((transitions == null) ? 0 : transitions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof State)) {
			return false;
		}
		State other = (State) obj;
		if (isFinal != other.isFinal) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (transitions == null) {
			if (other.transitions != null) {
				return false;
			}
		} else if (!transitions.equals(other.transitions)) {
			return false;
		}
		return true;
	}
}