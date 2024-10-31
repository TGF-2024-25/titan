/*
 * generated by Xtext 2.35.0
 */
package org.pnpl.model.variability.editor.ide

import com.google.inject.Guice
import org.eclipse.xtext.util.Modules2
import org.pnpl.model.variability.editor.PNPL_variabilityRuntimeModule
import org.pnpl.model.variability.editor.PNPL_variabilityStandaloneSetup

/**
 * Initialization support for running Xtext languages as language servers.
 */
class PNPL_variabilityIdeSetup extends PNPL_variabilityStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new PNPL_variabilityRuntimeModule, new PNPL_variabilityIdeModule))
	}
	
}
