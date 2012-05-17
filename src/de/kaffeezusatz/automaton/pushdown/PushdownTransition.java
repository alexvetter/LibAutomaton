package de.kaffeezusatz.automaton.pushdown;

import de.kaffeezusatz.automaton.Transition;

class PushdownTransition extends Transition {
	private final String read;
	private final String pop;
	private final String push;
	private final PushdownState next;

	public PushdownTransition(final String read, final String pop, final String push, final PushdownState next) {
		this.read = read;
		this.pop = pop;
		this.push = push;
		this.next = next;
	}

	public String getRead() {
		return read;
	}

	public String getPop() {
		return pop;
	}

	public String getPush() {
		return push;
	}

	public PushdownState getNext() {
		return next;
	}
}
