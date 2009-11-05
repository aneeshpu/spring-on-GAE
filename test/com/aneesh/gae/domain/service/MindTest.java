package com.aneesh.gae.domain.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.jdo.Transaction;

import org.junit.Test;

import com.aneesh.gae.domain.QuickThought;
import com.aneesh.gae.domain.Tag;
import com.aneeshpu.gae.domain.repository.ThoughtRepository;
import com.aneeshpu.gae.domain.service.Mind;

public class MindTest {

	@Test
	public void should_be_capable_of_thinking_new_thoughts_tagged_with_existing_tags() throws Exception {
		
		ThoughtRepository thoughtRepositoryMock = createMock(ThoughtRepository.class);
		String aCapableMind = "a capable mind";
		
		Tag mindTag = new Tag("mind");
		Transaction transactionMock = createMock(Transaction.class);
		transactionMock.begin();
		transactionMock.commit();
		
		expect(thoughtRepositoryMock.currentTransaction()).andReturn(transactionMock);
		
		expect(thoughtRepositoryMock.find(mindTag)).andReturn(mindTag);
		
		replay(thoughtRepositoryMock);
		replay(transactionMock);
		
		Mind mind = new Mind(thoughtRepositoryMock);
		QuickThought thought = mind.think(aCapableMind, "mind");
		
		assertTrue(thought.isTaggedWith(mindTag));
		verify(thoughtRepositoryMock);
		verify(transactionMock);
	}
	
	@Test
	public void should_remember_all_the_thoughts_I_have_had() throws Exception {

		ThoughtRepository thoughtRepositoryMock = createMock(ThoughtRepository.class);
		ArrayList<QuickThought> allMyThoughts = new ArrayList<QuickThought>();
		QuickThought firstThought = new QuickThought("first thought", "first");
		allMyThoughts.add(firstThought);
		QuickThought secondThought = new QuickThought("second thought", "second");
		allMyThoughts.add(secondThought);
		Transaction transactionMock = createMock(Transaction.class);
		
		transactionMock.begin();
		transactionMock.commit();
		
		expect(thoughtRepositoryMock.currentTransaction()).andReturn(transactionMock);
		
		expect(thoughtRepositoryMock.allMyThoughts()).andReturn(allMyThoughts);
		
		replay(thoughtRepositoryMock);
		replay(transactionMock);
		
		Mind mind = new Mind(thoughtRepositoryMock);
		Collection<QuickThought> allThoughtsKnownToMyMind = mind.allMyThoughts();
		
		assertTrue(allThoughtsKnownToMyMind.contains(firstThought));
		assertTrue(allThoughtsKnownToMyMind.contains(secondThought));
		
		verify(thoughtRepositoryMock);
		verify(transactionMock);
	}
	
	@Test
	public void searching_by_tag_should_return_all_thoughts() throws Exception {
		
		QuickThought gaeThought = new QuickThought("Google app engine is the coolest thing I have done all my life", "gae,java");
		QuickThought pythonOnGaeThought = new QuickThought("Google app engine supports both python and java", "gae,python, java");
		
		ThoughtRepository thoughtRepositoryMock = createMock(ThoughtRepository.class);
		String tag = "gae";
		
		ArrayList<QuickThought> thoughts = new ArrayList<QuickThought>();
		
		thoughts.add(gaeThought);
		thoughts.add(pythonOnGaeThought);
		
		Transaction transactionMock = createMock(Transaction.class);
		transactionMock.begin();
		transactionMock.commit();
		
		expect(thoughtRepositoryMock.currentTransaction()).andReturn(transactionMock);
		
		expect(thoughtRepositoryMock.allThoughtsTaggedWith(new Tag(tag))).andReturn(thoughts);
		replay(thoughtRepositoryMock);
		replay(transactionMock);
		
		Mind myMind = new Mind(thoughtRepositoryMock);
		
		Collection<QuickThought> allMyThoughts = myMind.allThoughtTaggedWith(tag);
		
		assertTrue(allMyThoughts.contains(gaeThought));
		assertTrue(allMyThoughts.contains(pythonOnGaeThought));
		
		verify(transactionMock);
		verify(thoughtRepositoryMock);
	}
	
	@Test
	public void should_return_null_if_search_by_tag_throws_exception() throws Exception {
		QuickThought gaeThought = new QuickThought("Google app engine is the coolest thing I have done all my life", "gae,java");
		QuickThought pythonOnGaeThought = new QuickThought("Google app engine supports both python and java", "gae,python, java");
		
		ThoughtRepository thoughtRepositoryMock = createMock(ThoughtRepository.class);
		String tag = "gae";
		
		ArrayList<QuickThought> thoughts = new ArrayList<QuickThought>();
		
		thoughts.add(gaeThought);
		thoughts.add(pythonOnGaeThought);
		
		Transaction transactionMock = createMock(Transaction.class);
		transactionMock.begin();
		
		expect(thoughtRepositoryMock.currentTransaction()).andReturn(transactionMock);
		replay(transactionMock);
		
		expect(thoughtRepositoryMock.allThoughtsTaggedWith(new Tag(tag))).andThrow(new RuntimeException());
		replay(thoughtRepositoryMock);
		
		Mind myMind = new Mind(thoughtRepositoryMock);
		
		Collection<QuickThought> allMyThoughts = myMind.allThoughtTaggedWith(tag);
		assertNull(allMyThoughts);
		
		verify(transactionMock);
		verify(thoughtRepositoryMock);
		
	}
}
