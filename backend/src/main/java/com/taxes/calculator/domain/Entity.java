package com.taxes.calculator.domain;

import java.util.Objects;

import com.taxes.calculator.domain.validation.ValidationHandler;

public abstract class Entity<ID extends Identifier>  {
	protected final ID id;
	
	protected Entity(final ID id) {
		Objects.requireNonNull(id,"Id should not be null");
		this.id = id;
	}
	
	public abstract void validate(ValidationHandler handler);

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity<?> other = (Entity<?>) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
