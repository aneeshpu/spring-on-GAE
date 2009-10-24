package com.aneeshpu.gae.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aneesh.gae.domain.QuickThought;
import com.aneeshpu.gae.domain.repository.ThoughtRepository;

@Component
public class Mind {

	private ThoughtRepository thoughtRepository;

	@Autowired
	public Mind(ThoughtRepository thoughtRepository) {
		this.thoughtRepository = thoughtRepository;
	}

	public QuickThought think(String thought) {
		QuickThought quickThought = new QuickThought(thought);
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