/*
 * generated by Xtext 2.12.0
 */
package hu.bme.mit.inf.dslreasoner.application.ui;

import com.google.inject.Injector;
import hu.bme.mit.inf.dslreasoner.application.ui.internal.ApplicationActivator;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ApplicationConfigurationExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ApplicationActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return ApplicationActivator.getInstance().getInjector(ApplicationActivator.HU_BME_MIT_INF_DSLREASONER_APPLICATION_APPLICATIONCONFIGURATION);
	}
	
}
