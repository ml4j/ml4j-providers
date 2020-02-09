/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.ml4j.provider.enums;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.ml4j.provider.Provider;

/**
 * Default implementation of a representation of an Enum from a Provider without
 * requiring the provider's codebase on the class-path.
 * 
 * Aids decoupling when we are mapping from enums from one provider to another,
 * and in the creation of provider-agnostic enums (eg.
 * ActivationFunctionTypeEnum )
 * 
 * @author Michael Lavelle
 *
 * @param <T> The specific type of ProviderEnum
 */
public class DefaultProviderEnum<T extends DefaultProviderEnum<T>> implements ProviderEnum, Serializable {

	/**
	 * Default serialization id.
	 */
	private static final long serialVersionUID = 1L;

	private final String providerName;
	private final String enumTypeClassName;
	private final String enumName;

	/**
	 * @param providerName      The unique Provider name.
	 * @param enumTypeClassName The class name of the enum within the Provider's
	 *                          codebase
	 * @param enumName          The name of the enum constant
	 */
	protected DefaultProviderEnum(String providerName, String enumTypeClassName, String enumName) {
		this.providerName = providerName;
		this.enumTypeClassName = enumTypeClassName;
		this.enumName = enumName;
	}

	/**
	 * @param provider          The provider
	 * @param enumTypeClassName The class name of the enum within the Provider's
	 *                          codebase
	 * @param enumName          The name of the enum constant
	 */
	public DefaultProviderEnum(Provider provider, String enumTypeClassName, String enumName) {
		this(provider.name(), enumTypeClassName, enumName);
	}

	/**
	 * @param <E>       The type of Enum for which this provider enum is being
	 *                  created.
	 * 
	 * @param provider  The Provider.
	 * @param enumValue The enum constant
	 */
	public <E extends Enum<E>> DefaultProviderEnum(Provider provider, E enumValue) {
		this(provider.name(), enumValue.getDeclaringClass().getName(), enumValue.name());
	}

	/**
	 * @param <E>          The type of Enum for which this provider enum is being
	 *                     created.
	 * 
	 * @param providerName The unique Provider name.
	 * @param enumValue    The enum constant
	 */
	public <E extends Enum<E>> DefaultProviderEnum(String providerName, E enumValue) {
		this(providerName, enumValue.getDeclaringClass().getName(), enumValue.name());
	}

	/**
	 * @return by default ProviderEnums are not placeholders - can be overridden by
	 *         subclasses.
	 */
	@Override
	public boolean isVisible() {
		return false;
	}

	@SuppressWarnings("unchecked")
	private <E> Class<E> getEnumClass() {
		try {
			return (Class<E>) Class.forName(enumTypeClassName);
		} catch (ClassNotFoundException e) {
			throw new ProviderEnumNotFoundException(this);
		}
	}

	public Enum<?> getEnum() {
		for (Object o : getEnumClass().getEnumConstants()) {
			Enum<?> e = (Enum<?>) o;
			if (e.name().equals(enumName)) {
				return e;
			}
		}
		throw new ProviderEnumNotFoundException(this);
	}

	public <E extends Enum<E>> E getEnumAsType(Class<E> type) {
		for (Object o : getEnumClass().getEnumConstants()) {
			@SuppressWarnings("unchecked")
			E e = (E) o;
			if (e.name().equals(enumName)) {
				return e;
			}
		}
		throw new ProviderEnumNotFoundException(this);
	}

	/**
	 * @return the enum constant name.
	 */
	@Override
	public String getEnumName() {
		return enumName;
	}

	/**
	 * @return the Enum type class name.
	 */
	public String getEnumTypeClassName() {
		return enumTypeClassName;
	}

	/**
	 * @return the Provider name.
	 */
	public String getProviderName() {
		return providerName;
	}

	@Override
	public String getQualifiedEnumName() {
		return enumTypeClassName + "." + enumName;
	}

	@Override
	public String toString() {
		return providerName + ":" + getQualifiedEnumName();
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
