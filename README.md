LibAutomaton
============
A simple object-oriented Java library for **PDA**, **NFA** and **DFA**.

* **p**ush**d**own **a**utomaton
* **n**ondeterministic **f**inite **a**utomaton
* **d**eterministic **f**inite **a**utomaton

```java
FiniteState z0 = new FiniteState("z0");
FiniteState z1 = new FiniteState("z1");
FiniteState z2 = new FiniteState("z2");

z0.setFinal();
		
z0.addTransition(z1, 'p');
z0.addTransition(z2, 'v');
		
z1.addTransition(z2, 't');
		
z2.addTransition(z0, 't');
z2.addTransition(z2, 'v');
		
FiniteAutomaton automat = new FiniteAutomaton(z0);

assertTrue(automat.testWord("ptvtvvt").isValid());
```