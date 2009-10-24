package com.aneeshpu.gae.domain.repository;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aneesh.gae.domain.QuickThought;

@Repository
public class ThoughtRepository {

	private final PersistenceManagerFactory persistenceManagerFactory;

	@Autowired
	public ThoughtRepository(PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;

	}

	public void persist(QuickThought quickThought) {

		PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		try {
			persistenceManager.makePersistent(quickThought);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			persistenceManager.close();
		}
	}

	public List<QuickThought> allMyThoughts() {
		List<QuickThought> allMyThoughts = new ArrayList<QuickThought>();
		Extent<QuickThought> extent = persistenceManagerFactory.getPersistenceManager().getExtent(QuickThought.class);
		for (QuickThought quickThought : extent) {
			allMyThoughts.add(quickThought);
		}
		
		return allMyThoughts;

	}

}
