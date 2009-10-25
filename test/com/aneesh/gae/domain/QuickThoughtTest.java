package com.aneesh.gae.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class QuickThoughtTest {

	@Test
	public void should_create_a_readable_representation_of_quick_thought() throws Exception {
		QuickThought quickThought = new QuickThought("Hmm biriyani was yummy");
		assertEquals("Hmm biriyani was yummy", quickThought.toString());
	}
	
	@Test
	public void should_remember_the_time_of_thought() throws Exception {
		
		QuickThought quickThought = new QuickThought("Hmm not a bad weekend");
		assertNotNull(quickThought.when());
	}
}