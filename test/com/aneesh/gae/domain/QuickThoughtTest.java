package com.aneesh.gae.domain;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class QuickThoughtTest {

	@Test
	public void should_create_a_readable_representation_of_quick_thought() throws Exception {
		QuickThought quickThought = new QuickThought("Hmm biriyani was yummy","food");
		assertEquals("Hmm biriyani was yummy", quickThought.toString());
	}
	
	@Test
	public void should_remember_the_time_of_thought() throws Exception {
		
		QuickThought quickThought = new QuickThought("Hmm not a bad weekend", "weekend");
		assertNotNull(quickThought.when());
	}
	
	@Test
	public void should_be_able_to_tag_my_thought() throws Exception {
		
		QuickThought taggedThought = new QuickThought("C# has some cool features over java", "java", "c#");
		assertTrue(taggedThought.isTaggedWith("java"));
	}
	
	@Test
	public void should_be_able_tag_my_thought_with_more_than_one_tag() throws Exception {
		QuickThought taggedThought = new QuickThought("C# has some cool features over java", "java", "c#");
		assertTrue(taggedThought.isTaggedWith("c#"));
		
	}
	
	@Test
	public void should_not_be_tagged() throws Exception {
		QuickThought taggedThought = new QuickThought("C# has some cool features over java", "java", "c#");
		assertFalse(taggedThought.isTaggedWith("ruby"));
	}
	
	@Test
	public void thoughts_should_be_equatable() throws Exception {
		QuickThought randomThought = new QuickThought("random thought", (String)null);
		QuickThought anotherRandomThought = new QuickThought("random thought", (String)null);
		
		assertEquals(randomThought, anotherRandomThought);
	}
	
	@Test
	public void thoughts_should_be_same_irrespective_of_tags() throws Exception {
		QuickThought randomThought = new QuickThought("random thought", "random");
		QuickThought anotherRandomThought = new QuickThought("random thought", "another");
		
		assertEquals(randomThought, anotherRandomThought);
	}
	
	@Test
	public void different_thoughts_should_be_identified_as_different() throws Exception {
		QuickThought randomThought = new QuickThought("random thought", "random");
		QuickThought anotherRandomThought = new QuickThought("another random thought", "another");
		
		assertNotSame(randomThought, anotherRandomThought);
	}
	
	@Test
	public void same_thoughts_should_produce_same_hashcode() throws Exception {
		QuickThought randomThought = new QuickThought("random thought", "random");
		QuickThought sameRandomThoughtAtADifferentTime = new QuickThought("random thought", "random");
		
		assertEquals(randomThought.hashCode(), sameRandomThoughtAtADifferentTime.hashCode());
	}
	
	@Test
	public void different_thoughts_should_produce_different_hashcodes() throws Exception {
		QuickThought randomThought = new QuickThought("random thought", "random");
		QuickThought anotherRandomThought = new QuickThought("random thought...but different", "random");
		
		assertNotSame(randomThought.hashCode(), anotherRandomThought.hashCode());
	}
	
}