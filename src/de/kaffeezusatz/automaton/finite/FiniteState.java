package de.kaffeezusatz.automaton.finite;

import java.util.LinkedList;
import java.util.List;

import de.kaffeezusatz.automaton.State;

public class FiniteState extends State {
	public static final FiniteState STATE_NULL = new FiniteState("null");
	
	private boolean isFinal = false;
	private List<FiniteTransition> transitions;
	
	public FiniteState(String name) {
		super(name);
		this.transitions = new LinkedList<FiniteTransition>();
	}
	
	public void setFinal() {
		this.isFinal = true;
	}
	
	public boolean isFinal() {
		return this.isFinal;
	}
	
	public FiniteState addTransition(FiniteState state, char... cs) {
		for (char c : cs) {
			FiniteTransition transition = new FiniteTransition(state, c);
			if (transitions.contains(transition)) {
				continue;
			}
			
			transitions.add(transition);
		}
		
		return this;
	}
	
	public List<FiniteState> getState(char c) {
		List<FiniteState> states = new LinkedList<FiniteState>();
		for (FiniteTransition transition : this.transitions) {
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
		result = prime * result + (super.hashCode());
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
		if (!(obj instanceof FiniteState)) {
			return false;
		}
		FiniteState other = (FiniteState) obj;
		if (isFinal != other.isFinal) {
			return false;
		}
		if (!super.equals(other)) {
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