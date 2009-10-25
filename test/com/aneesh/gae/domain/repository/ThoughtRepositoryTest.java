package com.aneesh.gae.domain.repository;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

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
		QuickThought randomThought = new QuickThought("Just a random thought");
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
		QuickThought quickThought = new QuickThought("just a random thought");
		
		Iterator<QuickThought> iteratorMock = EasyMock.createMock(Iterator.class);
		expect(iteratorMock.hasNext()).andReturn(true);
		expect(iteratorMock.hasNext()).andReturn(false);
		expect(iteratorMock.next()).andReturn(quickThought);
		
		Extent<QuickThought> extentMock = createMock(Extent.class);
		expect(extentMock.iterator()).andReturn(iteratorMock);
		expect(persistenceManagerMock.getExtent(QuickThought.class)).andReturn(extentMock);
		
		replay(persistenceManagerFactoryMock);
		replay(persistenceManagerMock);
		replay(iteratorMock);
		replay(extentMock);
		
		ThoughtRepository thoughtRepository = new ThoughtRepository(persistenceManagerFactoryMock);
		List<QuickThought> allMyThoughts = thoughtRepository.allMyThoughts();
		
		assertEquals(quickThought, allMyThoughts.get(0));
		
		verify(persistenceManagerFactoryMock);
		verify(persistenceManagerMock);
		verify(iteratorMock);
		verify(extentMock);
	}
}