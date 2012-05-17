package de.kaffeezusatz.automaton.pushdown;
import static org.junit.Assert.*;

import org.junit.Test;

public class PushdownAutomatonTest {

	@Test
	public void test() {
		PushdownState q0 = new PushdownState("q0");

		PushdownState q1 = new PushdownState("q1");
		q1.setFinal();

		q0.addTransition("1", "0", "", q0);
		q0.addTransition("0", "Z", "0Z", q0);
		q0.addTransition("0", "0", "00", q0);
		q0.addTransition("1", "Z", "1Z", q1);
		
		q1.addTransition("0", "2", "", q1);
		q1.addTransition("1", "2", "22", q1);
		q1.addTransition("1", "1", "21", q1);
		q1.addTransition("0", "1", "", q0);
		
		PushdownAutomaton pda = new PushdownAutomaton(q0, '0', '1');
		
		assertTrue(pda.testWord("01101").isValid());
		assertTrue(pda.testWord("10101").isValid());
		assertTrue(pda.testWord("1").isValid());
		assertTrue(pda.testWord("011").isValid());
		
		assertFalse(pda.testWord("0").isValid());
		assertFalse(pda.testWord("01").isValid());
		assertFalse(pda.testWord("10001").isValid());
		assertFalse(pda.testWord("1010101010101010").isValid());
		assertFalse(pda.testWord("10101010010101010").isValid());
	}
}
