/*
* generated by Xtext
*/
package hu.bme.mit.inf.dslreasoner.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class SmtLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("hu/bme/mit/inf/dslreasoner/parser/antlr/internal/InternalSmtLanguage.tokens");
	}
}
