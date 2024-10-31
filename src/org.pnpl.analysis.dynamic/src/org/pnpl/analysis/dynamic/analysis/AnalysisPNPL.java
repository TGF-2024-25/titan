package org.pnpl.analysis.dynamic.analysis;

import java.io.File;

import org.pnpl.analysis.analyzer.AbstractAnalysisPNPL;
import org.pnpl.export.cpntools.exporters.ExportPNPL;
import org.pnpl.export.exporters.AbstractExport;
import org.pnpl.solvers.cpntools.handlers.ColouredPetriNetsHandler;

public class AnalysisPNPL extends AbstractAnalysisPNPL {
	private String cpnFileName;
	
	@Override
	protected boolean buildCondition() {
		System.out.println("[petri nets analysis] Analyzing Reachability Graph");
		
		if(!transformPNPLtoCPN()) return false;		
		return runReachability();
	}

	private boolean transformPNPLtoCPN() {
		AbstractExport exporter = (AbstractExport) new ExportPNPL();	
		exporter.isExportation = false;
		boolean result = exporter.run(vrbFile);
		cpnFileName = exporter.getExportedFile();
		return result;
	}
	
	private boolean runReachability() {
		File file = new File(cpnFileName);
		if (!(file.exists()))
			return false;
		
		try {
			ColouredPetriNetsHandler cpn = new ColouredPetriNetsHandler();
			cpn.stateExploration(file);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
