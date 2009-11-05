package com.aneesh.gae.domain.repository;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aneesh.gae.domain.QuickThought;
import com.aneesh.gae.domain.Tag;
import com.aneeshpu.gae.domain.repository.QuickThoughtPersistenceException;
import com.aneeshpu.gae.domain.repository.ThoughtRepository;

public class ThoughtRepositoryTest {
	
	private PersistenceManagerFactory persistenceManagerFactoryMock;
	private PersistenceManager persistenceManagerMock;

	@Before
	public void setup(){
		persistenceManagerFactoryMock = createMock(PersistenceManagerFactory.class);
		persistenceManagerMock = createMock(PersistenceManager.class);
		expect(persistenceManagerFactoryMock.getPersistenceManager()).andReturn(persistenceManagerMock);
		replay(persistenceManagerFactoryMock);
	}

	@Test
	public void ShouldCallPersistenceManagerToPersistAThought() throws Exception {
		QuickThought randomThought = new QuickThought("Just a random thought","random");
		expect(persistenceManagerMock.makePersistent(randomThought)).andReturn(randomThought);
		persistenceManagerMock.close();
		
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		thoughtRepository.persist(randomThought);
		
		verify(persistenceManagerFactoryMock);
		verify(persistenceManagerMock);
	}
	
	@Test(expected=QuickThoughtPersistenceException.class)
	public void should_throw_runtime_exception_when_persist_fails() throws Exception {
		QuickThought randomThought = new QuickThought("Just a random thought","random");
		expect(persistenceManagerMock.makePersistent(randomThought)).andThrow(new RuntimeException());
		
		persistenceManagerMock.close();
		
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		thoughtRepository.persist(randomThought);
		/*
		verify(persistenceManagerFactoryMock);
		verify(persistenceManagerMock);*/
	}
	
	@Test
	public void ShouldFetchAllThoughtsFromStorage() throws Exception {
		QuickThought quickThought = new QuickThought("just a random thought","thought");
		
		Query queryMock = createMock(Query.class);
		ArrayList<QuickThought> allPersistedThoughts = new ArrayList<QuickThought>();
		allPersistedThoughts.add(quickThought);
		expect(queryMock.execute()).andReturn(allPersistedThoughts);
		
		expect(persistenceManagerMock.newQuery(QuickThought.class)).andReturn(queryMock);
		
		replay(persistenceManagerMock);
		replay(queryMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		Collection<QuickThought> allMyThoughts = thoughtRepository.allMyThoughts();
		
		assertTrue(allMyThoughts.contains(quickThought));
		
		verify(persistenceManagerFactoryMock);
		verify(persistenceManagerMock);
		verify(queryMock);
	}
	
	@Test
	public void should_fetch_all_tagged_thoughts() throws Exception {
		
		Query queryMock = createMock(Query.class);
		Tag alleppey = new Tag("alleppey");
		QuickThought quickThought = new QuickThought("Coding quick thought on train to alleppey",alleppey);
		alleppey.add(quickThought);
		
		expect(persistenceManagerMock.newQuery("javax.jdo.query.JDOQL", "SELECT FROM com.aneesh.gae.domain.Tag WHERE tag == \"" + alleppey + "\"")).andReturn(queryMock);
		
		ArrayList<Tag> tags = new ArrayList<Tag>();
		tags.add(alleppey);
		expect(queryMock.execute()).andReturn(tags);
		
		replay(queryMock);
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		Collection<QuickThought> allThoughtsTaggedWithAlleppey = thoughtRepository.allThoughtsTaggedWith(alleppey);
		
		assertTrue(allThoughtsTaggedWithAlleppey.size() == 1);
		assertTrue(allThoughtsTaggedWithAlleppey.contains(quickThought));
		
		verify(queryMock);
		verify(persistenceManagerMock);
	}
	
	@Test
	public void should_persist_a_tag() throws Exception {
		Tag reshmi = new Tag("Reshmi is sitting next to me");
		expect(persistenceManagerMock.makePersistent(reshmi)).andReturn(reshmi);
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		thoughtRepository.persist(reshmi);
		
		verify(persistenceManagerMock);
	}
	
	@Test(expected=QuickThoughtPersistenceException.class)
	public void should_throw_runtime_exception_when_tag_persistence_fails() throws Exception {
		Tag reshmi = new Tag("Reshmi is sitting next to me");
		expect(persistenceManagerMock.makePersistent(reshmi)).andThrow(new RuntimeException());
		
		persistenceManagerMock.close();
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		thoughtRepository.persist(reshmi);
		verify(persistenceManagerMock);
	}
	
	@Test
	public void should_find_tags() throws Exception {
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		
		Tag cashew = new Tag("cashews");
		Query queryMock = createMock(Query.class);
		
		ArrayList<Tag> results = new ArrayList<Tag>();
		results.add(cashew);
		expect(queryMock.execute()).andReturn(results);
		
		expect(persistenceManagerMock.newQuery("SELECT FROM com.aneesh.gae.domain.Tag WHERE tag == \"" + cashew + "\"")).andReturn(queryMock);
		
		replay(queryMock);
		replay(persistenceManagerMock);
		
		Tag tagThatWasFound = thoughtRepository.find(cashew);
		assertEquals(cashew, tagThatWasFound);
		
		verify(queryMock);
		verify(persistenceManagerMock);
	}
	
	@Test
	public void should_return_null_when_no_tag_is_found() throws Exception {

		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		
		Tag cashew = new Tag("cashews");
		Query queryMock = createMock(Query.class);
		
		ArrayList<Tag> results = new ArrayList<Tag>();
		expect(queryMock.execute()).andReturn(results);
		
		expect(persistenceManagerMock.newQuery("SELECT FROM com.aneesh.gae.domain.Tag WHERE tag == \"" + cashew + "\"")).andReturn(queryMock);
		
		replay(queryMock);
		replay(persistenceManagerMock);
		
		Tag tagThatWasFound = thoughtRepository.find(cashew);
		assertNull(tagThatWasFound);
		
		verify(queryMock);
		verify(persistenceManagerMock);

	}
	
	@Test
	public void should_start_a_new_transaction() throws Exception {
		Transaction transaction = createMock(Transaction.class);
		expect(persistenceManagerMock.currentTransaction()).andReturn(transaction);
		
		replay(transaction);
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		thoughtRepository.currentTransaction();
		
		verify(transaction);
		verify(persistenceManagerMock);
	}
	
	@Test
	public void should_commit_current_transaction() throws Exception {
		Transaction transaction = createMock(Transaction.class);
		transaction.commit();
		
		expect(persistenceManagerMock.currentTransaction()).andReturn(transaction);
		
		replay(transaction);
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		thoughtRepository.commitTransaction();
		
		verify(transaction);
		verify(persistenceManagerMock);
		
	}
	
	@Test
	public void should_rollback_transaction() throws Exception {
		Transaction transaction = createMock(Transaction.class);
		transaction.rollback();
		
		expect(persistenceManagerMock.currentTransaction()).andReturn(transaction);
		
		replay(transaction);
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		thoughtRepository.rollbackTransaction();
		
		verify(transaction);
		verify(persistenceManagerMock);
		
	}
	
	@After
	public void tearDown(){
		verify(persistenceManagerFactoryMock);
	}
}