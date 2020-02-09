package org.ml4j.provider.enums;

import org.junit.Assert;
import org.junit.Test;
import org.ml4j.provider.Provider;


public class TypeEnumTest {
	
	@Test
	public void testConstruction() {
		 TypeEnum typeEnum = new TypeEnum(ProviderEnums.RELU_ENUM);
		 ProviderEnum providerEnum = typeEnum.providedBy(Provider.ML4J);
		 Assert.assertNotNull(providerEnum);
		 Assert.assertEquals("ML4J", providerEnum.getProviderName());
		 Assert.assertEquals("RELU", providerEnum.getEnumName());
		 Assert.assertEquals("org.ml4j.nn.activationfunctions.ActivationFunctionBaseType.RELU", providerEnum.getQualifiedEnumName());
	}
}
