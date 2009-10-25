package com.aneesh.gae.domain.service;

import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
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
		List<QuickThought> allThoughtsKnownToMyMind = mind.allMyThoughts();
		
		assertEquals(firstThought, allThoughtsKnownToMyMind.get(0));
		assertEquals(secondThought, allThoughtsKnownToMyMind.get(1));
		
		verify(thoughtRepositoryMock);
	}
}
