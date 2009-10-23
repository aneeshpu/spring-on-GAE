package com.aneeshpu.gae.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.Extent;
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

	public List<QuickThought> allMyThoughts() {

		PersistenceManager persistenceManager = PMF.get().getPersistenceManager();

		ArrayList<QuickThought> allMyThoughts = new ArrayList<QuickThought>();

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
