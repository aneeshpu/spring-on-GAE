package com.aneesh.gae.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class QuickThought {

	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	private String quickThought;

	private Date when;
	
	@SuppressWarnings("unused")
	private QuickThought(){
	}
	
	public QuickThought(String quickThought) {
		this.quickThought = quickThought;
		when = new Date();
	}

	@Override
	public String toString() {
		return quickThought;
	}
	
	public Date when(){
		return when;
	}
}