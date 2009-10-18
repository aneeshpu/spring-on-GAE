package com.aneesh.gae.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class QuickThoughtTest {

	@Test
	public void should_create_a_readable_representation_of_quick_thought() throws Exception {
		
		QuickThought quickThought = new QuickThought("Hmm biriyani was yummy");
		assertEquals("Hmm biriyani was yummy", quickThought.toString());
	}
}