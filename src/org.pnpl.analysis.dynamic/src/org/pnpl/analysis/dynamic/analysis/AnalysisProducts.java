package org.pnpl.analysis.dynamic.analysis;

import org.pnpl.analysis.analyzer.AbstractAnalysisProducts;
import org.pnpl.analysis.dynamic.reachabilitygraph.ReachabilityGraph;

public class AnalysisProducts extends AbstractAnalysisProducts {

	@Override
	protected boolean runProductAnalysis() {
		if (pn == null) return false;
		
		// Creates the reachability graph from the given PetriNet
		ReachabilityGraph graph = new ReachabilityGraph();
		System.out.println("[pnpl]: Building reachability graph...");
		graph.reachabilityGraph(pn);
		
		// Output for Graphviz
		System.out.println("[pnpl]: Reachability graph for Graphviz");
		String reachability_graph_Graphviz_format = graph.toGraphviz();
		
		System.out.println(reachability_graph_Graphviz_format);
	
		return true;
	}
}
