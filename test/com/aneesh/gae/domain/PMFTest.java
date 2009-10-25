package com.aneesh.gae.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PMFTest {

	@Test
	public void shouldReturnAnInstanceOfPersistenceManager() throws Exception {
		assertTrue(PMF.get() == PMF.get());
	}
}
