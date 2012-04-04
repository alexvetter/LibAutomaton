package de.kaffeezusatz.finiteautomaton;

import java.util.HashMap;
import java.util.Map;

public class State {
	private String name;
	private boolean isFinal = false;
	private Map<String, State> transitions;
	
	public State(String name) {
		this.name = name;
		this.transitions = new HashMap<String, State>();
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
			transitions.put(String.valueOf(c), state);
		}
		return this;
	}
	
	public State getTransition(char c) {
		if (transitions.containsKey(String.valueOf(c))) {
			return transitions.get(String.valueOf(c));
		}
		
		return null;
	}
}