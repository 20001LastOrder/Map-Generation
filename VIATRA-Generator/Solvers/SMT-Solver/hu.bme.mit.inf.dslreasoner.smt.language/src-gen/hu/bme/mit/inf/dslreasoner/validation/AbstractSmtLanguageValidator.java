/*
 * generated by Xtext
 */
package hu.bme.mit.inf.dslreasoner.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;

public class AbstractSmtLanguageValidator extends org.eclipse.xtext.validation.AbstractDeclarativeValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(hu.bme.mit.inf.dslreasoner.smtLanguage.SmtLanguagePackage.eINSTANCE);
		return result;
	}
}
