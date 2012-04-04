package de.kaffeezusatz.finiteautomaton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AutomatTest {

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
		
		Automat automat = new Automat(z0);

		Automat.Result result1 = automat.testWord("ptvtvvt");
		assertTrue(result1.isValid());
		assertEquals(result1.getWord().length()+1, result1.getStates().size());
		
		System.out.println(result1.toString());
		
		Automat.Result result2 = automat.testWord("pptt");
		assertFalse(result2.isValid());
		assertEquals(result2.getWord().length()+1, result2.getStates().size());
		
		System.out.println(result2.toString());
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
		
		Automat automat = new Automat(q0);

		Automat.Result result1 = automat.testWord("vptptptp");
		assertTrue(result1.isValid());
		assertEquals(result1.getWord().length()+1, result1.getStates().size());
		
		System.out.println(result1.toString());
		
		Automat.Result result2 = automat.testWord("vpp");
		assertFalse(result2.isValid());
		assertEquals(result2.getWord().length()+1, result2.getStates().size());
		
		System.out.println(result2.toString());
	}
}
