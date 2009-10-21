package com.aneeshpu.gae.domain.service;

import javax.jdo.PersistenceManager;

import com.aneesh.gae.domain.PMF;
import com.aneesh.gae.domain.QuickThought;

public class Mind {

	public QuickThought think(String thought) {
		QuickThought quickThought = new QuickThought(thought);

		PersistenceManager persistenceManager = PMF.get().getPersistenceManager();
		try {
			persistenceManager.makePersistent(quickThought);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			persistenceManager.close();
		}

		return quickThought;
	}
}
