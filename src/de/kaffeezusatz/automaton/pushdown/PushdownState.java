package de.kaffeezusatz.automaton.pushdown;
import java.util.LinkedList;
import java.util.List;


public class PushdownState {
	private String name;
	
	private boolean isFinal = false;
	
	private List<PushdownTransition> transitions;
	
	public PushdownState(String name) {
		this.transitions = new LinkedList<PushdownTransition>();
		this.name = name;
	}
	
	public PushdownState addTransition(String read, String pop, String push, PushdownState next) {
		transitions.add(new PushdownTransition(read, pop, push, next));
		return this;
	}
	
	public PushdownTransition getNext(String read, String pop) {
		for (PushdownTransition transition : transitions) {
			if (transition.getRead().equals(read) && transition.getPop().equals(pop)) {
				return transition;
			}
		}
		
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	public void setFinal() {
		this.isFinal = true;
	}
	
	public boolean isFinal() {
		return this.isFinal;
	}
}