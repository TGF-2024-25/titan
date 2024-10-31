package org.pnpl.analysis.pinvariant.analysis;

import java.util.List;
import java.util.Set;

import org.pnpl.analysis.analyzer.AbstractAnalysisProducts;
import org.pnpl.analysis.invariant.analysis.InvariantCalculator;

/**
 * @author Elena Gomez-Martinez
 *
 */

public class AnalysisProducts extends AbstractAnalysisProducts {

	@Override
	protected boolean runProductAnalysis() {
		if (pn == null) return false;

		System.out.println("[pnpl] P-Invariant with Farkas' algorithm");

		Set<List<Integer>> calculated = InvariantCalculator.calcSInvariants(pn,InvariantCalculator.InvariantAlgorithm.FARKAS);
		System.out.println("[pnpl] P-Invariant: The net has " + calculated.size());

		boolean hasInvariant = (calculated.size() > 0);
		if (hasInvariant) {
			for (List<Integer> inv : calculated) {
				System.out.println("[pnpl] P-Invariant: " + inv.toString());
			}	
		}
		return hasInvariant;
	}
}