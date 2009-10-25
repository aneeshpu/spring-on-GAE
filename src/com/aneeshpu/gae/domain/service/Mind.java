package com.aneeshpu.gae.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aneesh.gae.domain.QuickThought;
import com.aneeshpu.gae.domain.repository.ThoughtRepository;

@Service
public class Mind {

	private final ThoughtRepository thoughtRepository;

	@Autowired
	public Mind(final ThoughtRepository thoughtRepository) {
		this.thoughtRepository = thoughtRepository;
	}

	public QuickThought think(final String thought, String tags) {
		final QuickThought quickThought = new QuickThought(thought,tags);
		thoughtRepository().persist(quickThought);
		return quickThought;
	}

	private ThoughtRepository thoughtRepository() {
		return thoughtRepository;
	}

	public List<QuickThought> allMyThoughts() {
		return thoughtRepository().allMyThoughts();
	}
}