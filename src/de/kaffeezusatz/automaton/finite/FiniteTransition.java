package de.kaffeezusatz.automaton.finite;

class FiniteTransition {
	private FiniteState state;
	private char c;

	public FiniteTransition(final FiniteState state, final char c) {
		super();
		this.state = state;
		this.c = c;
	}

	public FiniteState getState() {
		return state;
	}

	public char getChar() {
		return c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + c;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		if (!(obj instanceof FiniteTransition)) {
			return false;
		}
		FiniteTransition other = (FiniteTransition) obj;
		if (c != other.c) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		return true;
	}
}