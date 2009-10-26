package com.aneesh.gae.domain.repository;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aneesh.gae.domain.QuickThought;
import com.aneeshpu.gae.domain.repository.ThoughtRepository;

public class ThoughtRepositoryTest {
	
	private PersistenceManagerFactory persistenceManagerFactoryMock;
	private PersistenceManager persistenceManagerMock;

	@Before
	public void setup(){
		persistenceManagerFactoryMock = createMock(PersistenceManagerFactory.class);
		persistenceManagerMock = createMock(PersistenceManager.class);
		expect(persistenceManagerFactoryMock.getPersistenceManager()).andReturn(persistenceManagerMock);
	}

	@Test
	public void ShouldCallPersistenceManagerToPersistAThought() throws Exception {
		QuickThought randomThought = new QuickThought("Just a random thought","random");
		expect(persistenceManagerMock.makePersistent(randomThought)).andReturn(randomThought);
		persistenceManagerMock.close();
		
		replay(persistenceManagerFactoryMock);
		replay(persistenceManagerMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		thoughtRepository.persist(randomThought);
		
		verify(persistenceManagerFactoryMock);
		verify(persistenceManagerMock);
	}
	
	@Test
	public void ShouldFetchAllThoughtsFromStorage() throws Exception {
		QuickThought quickThought = new QuickThought("just a random thought","thought");
		
		Query queryMock = createMock(Query.class);
		ArrayList<QuickThought> allPersistedThoughts = new ArrayList<QuickThought>();
		allPersistedThoughts.add(quickThought);
		expect(queryMock.execute()).andReturn(allPersistedThoughts);
		
		expect(persistenceManagerMock.newQuery(QuickThought.class)).andReturn(queryMock);
		
		replay(persistenceManagerFactoryMock);
		replay(persistenceManagerMock);
		replay(queryMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		Collection<QuickThought> allMyThoughts = thoughtRepository.allMyThoughts();
		
		assertTrue(allMyThoughts.contains(quickThought));
		
		verify(persistenceManagerFactoryMock);
		verify(persistenceManagerMock);
		verify(queryMock);
	}
}