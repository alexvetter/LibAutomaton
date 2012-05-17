# LibAutomaton

A simple object-oriented Java library for **PDA**, **NFA** and **DFA**.

* **p**ush**d**own **a**utomaton
* **n**ondeterministic **f**inite **a**utomaton
* **d**eterministic **f**inite **a**utomaton

## FiniteAutomaton
```java
FiniteState z0 = new FiniteState("z0");
FiniteState z1 = new FiniteState("z1");
FiniteState z2 = new FiniteState("z2");

z0.setFinal();

// this finite automaton is deterministic
z0.addTransition(z1, 'p');
z0.addTransition(z2, 'v');
		
z1.addTransition(z2, 't');
		
z2.addTransition(z0, 't');
z2.addTransition(z2, 'v');
		
FiniteAutomaton automat = new FiniteAutomaton(z0);

assertTrue(automat.testWord("ptvtvvt").isValid());
```

## PushdownAutomaton
```java
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
```