package com.aneesh.gae.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

	private String[] tags;

	@Persistent
	private List<Tag> tagObjects;
	
	
	
	@SuppressWarnings("unused")
	private QuickThought(){
	}
	
	public QuickThought(String quickThought, String... tags) {
		this.quickThought = quickThought;
		this.tags = tags;
		
		when = new Date();
	}
	
	public QuickThought(String quickThought, Tag... tags){
		this.quickThought = quickThought;
		this.tagObjects = Arrays.asList(tags);
		when = new Date();
	}

	@Override
	public String toString() {
		return quickThought;
	}
	
	public Date when(){
		return when;
	}

	public boolean isTaggedWith(String tag) {
		for (String myTag : tags) {
			if(myTag.equals(tag)) 
				return true;
		}
		
		return false;
	}
	
	public boolean isTaggedWith(Tag tag){
		for(Tag tempTag:tagObjects){
			if(tempTag.equals(tag))
				return true;
		}
		
		return false;
	}
	
	public List<Tag> tags(){
		return tagObjects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((quickThought == null) ? 0 : quickThought.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuickThought other = (QuickThought) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quickThought == null) {
			if (other.quickThought != null)
				return false;
		} else if (!quickThought.equals(other.quickThought))
			return false;
		return true;
	}
	
}