package com.aneesh.gae.domain.service;

import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.aneesh.gae.domain.QuickThought;
import com.aneeshpu.gae.domain.repository.ThoughtRepository;
import com.aneeshpu.gae.domain.service.Mind;

public class MindTest {

	@Test
	public void should_be_capable_of_thinking_new_thoughts() throws Exception {
		
		ThoughtRepository thoughtRepositoryMock = createMock(ThoughtRepository.class);
		String aCapableMind = "a capable mind";
		thoughtRepositoryMock.persist(new QuickThought(aCapableMind, "mind"));
		replay(thoughtRepositoryMock);
		
		Mind mind = new Mind(thoughtRepositoryMock);
		QuickThought thought = mind.think(aCapableMind, "mind");
		
		assertTrue(thought.isTaggedWith("mind"));
		verify(thoughtRepositoryMock);
	}
	
	@Test
	public void should_remember_all_the_thoughts_I_have_had() throws Exception {

		ThoughtRepository thoughtRepositoryMock = createMock(ThoughtRepository.class);
		ArrayList<QuickThought> allMyThoughts = new ArrayList<QuickThought>();
		QuickThought firstThought = new QuickThought("first thought", "first");
		allMyThoughts.add(firstThought);
		QuickThought secondThought = new QuickThought("second thought", "second");
		allMyThoughts.add(secondThought);
		
		expect(thoughtRepositoryMock.allMyThoughts()).andReturn(allMyThoughts);
		replay(thoughtRepositoryMock);
		
		Mind mind = new Mind(thoughtRepositoryMock);
		Collection<QuickThought> allThoughtsKnownToMyMind = mind.allMyThoughts();
		
		assertTrue(allThoughtsKnownToMyMind.contains(firstThought));
		assertTrue(allThoughtsKnownToMyMind.contains(secondThought));
		
		verify(thoughtRepositoryMock);
	}
	
	@Test
	public void searching_by_tag_should_return_all_thoughts() throws Exception {
		
		QuickThought gaeThought = new QuickThought("Google app engine is the coolest thing I have done all my life", "gae,java");
		QuickThought pythonOnGaeThought = new QuickThought("Google app engine supports both python and java", "gae,python, java");
		
		ThoughtRepository thoughtRepository = createMock(ThoughtRepository.class);
		
		Mind myMind = new Mind(thoughtRepository);
		
		Collection<QuickThought> allMyThoughts = myMind.allThoughtTaggedWith("gae");
		
		assertTrue(allMyThoughts.contains(gaeThought));
		assertTrue(allMyThoughts.contains(pythonOnGaeThought));
		
	}
	
}
