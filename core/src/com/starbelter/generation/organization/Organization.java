package com.starbelter.generation.organization;

import java.util.List;

public abstract class Organization implements Ownable {
	
	private String name;
	private long foundingDate;
	
	private Ownable owner;
	private List<Ownable> ownees;
	
	public Organization(String name, long foundingDate) {
		this.name = name;
		this.foundingDate = foundingDate;
	}
}
