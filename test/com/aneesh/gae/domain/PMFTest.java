package com.aneesh.gae.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PMFTest {

	@Test
	public void ShouldReturnAnInstanceOfPersistenceManager() throws Exception {
//		assertThat("Expected a non null persistence manager", PMF.get(), equalTo(PMF.get()));
		assertTrue(PMF.get() == PMF.get());
	}
}
