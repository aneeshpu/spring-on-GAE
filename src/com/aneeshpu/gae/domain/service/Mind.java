package com.aneeshpu.gae.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aneesh.gae.domain.QuickThought;

@Component
public class Mind {

	private PersistenceManagerFactory persistenceManagerFactory;
	
	@Autowired
	public Mind(PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;

	}

	public QuickThought think(String thought) {
		QuickThought quickThought = new QuickThought(thought);

		PersistenceManager persistenceManager = persistenceManager();
		try {
			persistenceManager.makePersistent(quickThought);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			persistenceManager.close();
		}

		return quickThought;
	}

	private PersistenceManager persistenceManager() {
		return persistenceManagerFactory.getPersistenceManager();
	}

	public List<QuickThought> allMyThoughts() {

		ArrayList<QuickThought> allMyThoughts = new ArrayList<QuickThought>();

		PersistenceManager persistenceManager = persistenceManager();
		try {
			Extent<QuickThought> extent = persistenceManager.getExtent(QuickThought.class);
			for (QuickThought quickThought : extent) {
				allMyThoughts.add(quickThought);
			}

			return allMyThoughts;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			persistenceManager.close();
		}

		return Collections.emptyList();

	}
}