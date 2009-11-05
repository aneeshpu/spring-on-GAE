package com.aneeshpu.gae.domain.service;

import java.util.Collection;

import javax.jdo.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aneesh.gae.domain.QuickThought;
import com.aneesh.gae.domain.Tag;
import com.aneeshpu.gae.domain.repository.ThoughtRepository;

@Service
public class Mind {

	private final ThoughtRepository thoughtRepository;

	@Autowired
	public Mind(final ThoughtRepository thoughtRepository) {
		this.thoughtRepository = thoughtRepository;
	}

	public QuickThought think(final String thought, final String tags) {
		try {
			Transaction transaction = thoughtRepository.currentTransaction();
			transaction.begin();
			Tag tag = new Tag(tags);
			Tag existingTag = thoughtRepository.find(tag);
			tag = existingTag == null ? thoughtRepository().persist(tag) : existingTag;
			final QuickThought quickThought = new QuickThought(thought, tag);
			tag.add(quickThought);
			transaction.commit();
			return quickThought;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private ThoughtRepository thoughtRepository() {
		return thoughtRepository;
	}

	public Collection<QuickThought> allMyThoughts() {
		Transaction currentTransaction = thoughtRepository.currentTransaction();
		currentTransaction.begin();
		Collection<QuickThought> allMyThoughts = thoughtRepository().allMyThoughts();
		currentTransaction.commit();
		return allMyThoughts;

	}

	public Collection<QuickThought> allThoughtTaggedWith(String tagName) {
		Tag tag = new Tag(tagName);

		try {
			Transaction currentTransaction = thoughtRepository.currentTransaction();
			currentTransaction.begin();
			
			Collection<QuickThought> allTaggedThoughts = thoughtRepository.allThoughtsTaggedWith(tag);
			currentTransaction.commit();
			return allTaggedThoughts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}