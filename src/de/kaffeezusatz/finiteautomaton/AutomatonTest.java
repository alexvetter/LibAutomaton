package de.kaffeezusatz.finiteautomaton;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AutomatonTest {

	@Test
	public void testA() {
		State z0 = new State("z0");
		State z1 = new State("z1");
		State z2 = new State("z2");

		z0.setFinal();
		
		z0.addTransition(z1, 'p');
		z0.addTransition(z2, 'v');
		
		z1.addTransition(z2, 't');
		
		z2.addTransition(z0, 't');
		z2.addTransition(z2, 'v');
		
		Automaton automat = new Automaton(z0);

		assertTrue(automat.testWord("ptvtvvt").isValid());
		
		System.out.println(automat.testWord("ptvtvvt").toString());
		
		assertFalse(automat.testWord("pptt").isValid());
	}
	
	@Test
	public void testB() {
		State q0 = new State("q0");
		State q1 = new State("q1");
		State q2 = new State("q2");
		State q3 = new State("q3");
		State q4 = new State("q4");

		q1.setFinal();
		q2.setFinal();
		
		q0.addTransition(q4, 't', 'p');
		q0.addTransition(q3, 'v');
		q1.addTransition(q2, 't');
		q1.addTransition(q1, 'v');
		q2.addTransition(q3, 't');
		q3.addTransition(q2, 'p');
		q4.addTransition(q1, 'p');
		q4.addTransition(q4, 'v');
		
		Automaton automat = new Automaton(q0);

		assertTrue(automat.testWord("vptptptp").isValid());
		
		assertFalse(automat.testWord("vpp").isValid());
	}
	
	@Test
	public void testC() {
		/*
		 * NFA
		 */
		State q0 = new State("q0");
		State q1 = new State("q1");
		
		q1.setFinal();
		
		q0.addTransition(q0, '0');
		q0.addTransition(q1, '0', '1');
		q1.addTransition(q1, '1');
		q1.addTransition(q0, '1');
		
		Automaton automat = new Automaton(q0);
		
		assertTrue(automat.testWord("011000").isValid());
		assertTrue(automat.testWord("0110010").isValid());
	}
}
