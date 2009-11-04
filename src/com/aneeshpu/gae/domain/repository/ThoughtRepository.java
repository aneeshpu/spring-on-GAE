package com.aneeshpu.gae.domain.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aneesh.gae.domain.QuickThought;
import com.aneesh.gae.domain.Tag;

@Repository
public class ThoughtRepository {

	private final PersistenceManagerFactory persistenceManagerFactory;

	@Autowired
	public ThoughtRepository(final PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;

	}

	public void persist(final QuickThought quickThought) {

		final PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		try {
			persistenceManager.makePersistent(quickThought);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			persistenceManager.close();
		}
	}

	public Collection<QuickThought> allMyThoughts() {
		final List<QuickThought> allMyThoughts = new ArrayList<QuickThought>();
		PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		final Query allMyThoughtsQuery = persistenceManager.newQuery(QuickThought.class);

		return (Collection<QuickThought>) allMyThoughtsQuery.execute();
	}

	public Collection<QuickThought> allThoughtsTaggedWith(Tag tag) {
		PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		String tagName = tag.fooBar();
		Query query = persistenceManager.newQuery("javax.jdo.query.JDOQL", "SELECT FROM com.aneesh.gae.domain.Tag WHERE tag == \"" + tagName + "\"");

		Collection<Tag> retrievedTag = (Collection<Tag>) query.execute();
		ArrayList<QuickThought> thoughts = new ArrayList<QuickThought>();
		System.out.println("found " + thoughts.size() + " tags");
		for (Tag tag2 : retrievedTag) {
			System.out.println("found tag " + tag2);
			thoughts.addAll(tag2.thought());
		}

		return thoughts;
	}

	public Tag find(Tag tag) {
		PersistenceManager persistenceManager = persistenceManagerFactory.getPersistenceManager();
		Query query = persistenceManager.newQuery("SELECT FROM com.aneesh.gae.domain.Tag WHERE tag == \"" + tag.fooBar() + "\"");
		Collection<Tag> tags = (Collection<Tag>) query.execute();
		if(tags.size() == 0)
			return null;

		ArrayList<Tag> arrayList = new ArrayList<Tag>();
		arrayList.addAll(tags);

		return arrayList.get(0);
	}
}