package com.mikasa.enums;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

abstract public class EnumType {

	private int type;
	private String name;
	
	public EnumType(){}
	protected EnumType(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
