package org.pnpl.analysis.dynamic.analysis;

import org.pnpl.analysis.analyzer.AbstractAnalysisProducts;
import org.pnpl.analysis.dynamic.reachabilitygraph.ReachabilityGraph;

import PetriNets.PetriNet;

public class TimedAnalysisProducts extends AbstractAnalysisProducts {
	
	@Override
	protected boolean runProductAnalysis() {
		System.out.println("[REACHABILITY GRAPH]: Loading PetriNet...");
		PetriNet pn = vh.getPetriNet();
		
		//IFile tmp_file = vh.getPetriNetFile();
		
		//Creates the reachability grap from the given PetriNet
		ReachabilityGraph graph = new ReachabilityGraph();
		System.out.println("[TIMED REACHABILITY GRAPH]: Building reachability graph...");
		
		graph.timedReachabilityGraph(pn, 100);
		//Output for Graphviz
		System.out.println("[TIMED REACHABILITY GRAPH]: Reachability graph for Graphviz.");
		//String reachability_graph_Graphviz_format = graph.toGraphviz();
		String reachability_graph_Graphviz_format = graph.toTimedGraphviz();
		System.out.println(reachability_graph_Graphviz_format);
	
		return true;
	}
}
