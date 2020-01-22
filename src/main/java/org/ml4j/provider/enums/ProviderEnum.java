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

/**
 * Represents an Enum from a particular provider without requiring that the Enum
 * be on the classpath.
 * 
 * Aids decoupling.
 * 
 * @author Michael Lavelle
 */
public interface ProviderEnum {

	String getProviderName();

	default boolean isVisible() {
		return true;
	}

	default String getEnumName() {
		return getEnum().name();
	}

	default String getQualifiedEnumName() {
		return getEnum().getDeclaringClass().getName() + "." + getEnumName();
	}

	Enum<?> getEnum();

	<E extends Enum<E>> E getEnumAsType(Class<E> type);
}
