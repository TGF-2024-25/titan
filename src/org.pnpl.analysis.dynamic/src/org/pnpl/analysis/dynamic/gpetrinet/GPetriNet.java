package org.pnpl.analysis.dynamic.gpetrinet;

import java.util.ArrayList;
import java.util.List;

import org.pnpl.analysis.dynamic.reachabilitygraph.State;

import PetriNets.PTArc;
import PetriNets.PetriNet;
import PetriNets.Place;
import PetriNets.TPArc;
import PetriNets.Transition;

//Derived from PetriNets
public class GPetriNet {
	
	private List<GPlace> places = null;
	private List<GTransition> transitions = null;
	private int global_time;
	
	public GPetriNet(PetriNet pn){
		this.places = new ArrayList<GPlace>();
		for (Place place : pn.getPlaces()) {
			this.places.add(new GPlace(place));
		}
		this.transitions = new ArrayList<GTransition>();
		for (Transition trans : pn.getTrans()) {
			this.transitions.add(new GTransition(trans));
		}
		
		//System.out.println(this.toString());
		
		this.global_time = 0;
		
		this.updateReferences(pn);
	}
	
	public GPetriNet(GPetriNet gpn) {
		this.places = new ArrayList<GPlace>();
		for (GPlace place : gpn.getPlaces()) {
			this.places.add(new GPlace(place));
		}
		this.transitions = new ArrayList<GTransition>();
		for (GTransition trans : gpn.getTrans()) {
			this.transitions.add(new GTransition(trans));
		}
		
		this.global_time = gpn.getTime();
		
		this.updateReferences(gpn);
	}
	
	public void updateState(State state) {
		
		for (GPlace place : this.places) {
			if (state.placeIsIn(place)) {
				place.createTokens(state.getTokensPlace(place));
			}
			else {
				place.deleteTokens();
			}
		}
		
		this.global_time = state.getTime();
		
	}
	
	private void updateReferences(PetriNet pn) {
		for (Place place : pn.getPlaces()) {
			List<GPTArc> nOutputs = new ArrayList<GPTArc>();
			for (PTArc arc : place.getOutputs()) {
				GPTArc nArc = new GPTArc(arc, this.getTransition(arc.getOutput()), this.getPlace(place));
				nOutputs.add(nArc);
			}
			this.getPlace(place).setOutputs(nOutputs);
			
			List<GTPArc> nInputs = new ArrayList<GTPArc>();
			for (TPArc arc : place.getInputs()) {
				GTPArc nArc = new GTPArc(arc, this.getPlace(place), this.getTransition(arc.getInput()));
				nInputs.add(nArc);
			}
			this.getPlace(place).setInputs(nInputs);
		}
		
		for (Transition trans : pn.getTrans()) {
			List<GTPArc> nOutputs = new ArrayList<GTPArc>();
			for (TPArc arc : trans.getOutputs()) {
				GTPArc nArc = new GTPArc(arc, this.getPlace(arc.getOutput()), this.getTransition(trans));
				nOutputs.add(nArc);
			}
			this.getTransition(trans).setOutputs(nOutputs);
			
			List<GPTArc> nInputs = new ArrayList<GPTArc>();
			for (PTArc arc : trans.getInputs()) {
				GPTArc nArc = new GPTArc(arc, this.getTransition(trans), this.getPlace(arc.getInput()));
				nInputs.add(nArc);
			}
			this.getTransition(trans).setInputs(nInputs);
		}
	}
	
	private void updateReferences(GPetriNet pn) {
		for (GPlace place : pn.getPlaces()) {
			List<GPTArc> nOutputs = new ArrayList<GPTArc>();
			for (GPTArc arc : place.getOutputs()) {
				GPTArc nArc = new GPTArc(arc, this.getTransition(arc.getOutput()), this.getPlace(place));
				nOutputs.add(nArc);
			}
			this.getPlace(place).setOutputs(nOutputs);
			
			List<GTPArc> nInputs = new ArrayList<GTPArc>();
			for (GTPArc arc : place.getInputs()) {
				GTPArc nArc = new GTPArc(arc, this.getPlace(place), this.getTransition(arc.getInput()));
				nInputs.add(nArc);
			}
			this.getPlace(place).setInputs(nInputs);
		}
		
		for (GTransition trans : pn.getTrans()) {
			List<GTPArc> nOutputs = new ArrayList<GTPArc>();
			for (GTPArc arc : trans.getOutputs()) {
				GTPArc nArc = new GTPArc(arc, this.getPlace(arc.getOutput()), this.getTransition(trans));
				nOutputs.add(nArc);
			}
			this.getTransition(trans).setOutputs(nOutputs);
			
			List<GPTArc> nInputs = new ArrayList<GPTArc>();
			for (GPTArc arc : trans.getInputs()) {
				GPTArc nArc = new GPTArc(arc, this.getTransition(trans), this.getPlace(arc.getInput()));
				nInputs.add(nArc);
			}
			this.getTransition(trans).setInputs(nInputs);
		}
	}
	
	public boolean equals(GPetriNet other) {
		if (this.places.equals(other.getPlaces())) {
			if (this.transitions.equals(other.getTrans())) {
				if (this.global_time == other.getTime()) {
					return true;
				}
			}
		}
		return false;
		//return (this.places.equals(other.getPlaces()) && this.transitions.equals(other.getTrans()));
	}
	
	public void updateTime(int new_time) {
		this.global_time = new_time;
		for (GPlace place : this.places) {
			place.updateTime(new_time);
		}
	}
	
	public int getTime() {
		return this.global_time;
	}
	
	public void setTime(int time) {
		this.global_time = time;
	}
	
	public void addTime(int time) {
		this.global_time += time;
	}
	
	private GTransition getTransition(Transition find) {
		for (GTransition trans : this.transitions) {
			if (trans.getName().equals(find.getName()))
				return trans;
		}
		return null;
	}
	
	private GTransition getTransition(GTransition find) {
		for (GTransition trans : this.transitions) {
			if (trans.getName().equals(find.getName()))
				return trans;
		}
		return null;
	}
	
	public int getTransitionIndex(GTransition find) {
		for (int i = 0; i < this.transitions.size(); i++) {
			if (this.transitions.get(i).equals(find)) {
				return i;
			}
		}
		
		return -1;
	}
	
	private GPlace getPlace(Place find) {
		for (GPlace place: this.places) {
			if (place.getName().equals(find.getName()))
				return place;
		}
		return null;
	}
	
	private GPlace getPlace(GPlace find) {
		for (GPlace place: this.places) {
			if (place.getName().equals(find.getName()))
				return place;
		}
		return null;
	}
	
	public List<GPlace> getPlaces(){
		return this.places;
	}
	
	public List<GTransition> getTrans(){
		return this.transitions;
	}
	
	public void setPlaces(List<GPlace> places) {
		this.places = places;
	}
	
	public void setTrans(List<GTransition> transitions) {
		this.transitions = transitions;
	}
	
	public String getLabel() {
		String rtr = "{";
		
		if (this.global_time > 0)
		{
			rtr = this.global_time + "-{";
		}
		
		for (GPlace place : this.places) {
			if (!place.getTokens().isEmpty()) {
				rtr += place.getLabel() + ", ";
			}
		}
		
		rtr = rtr.substring(0, rtr.length() - 2);
		rtr += "}";
		
		return rtr;
	}
	
	public String toString() {
		String rtr = "";
		
		rtr += "[PLACES:]\n";
		for (GPlace place : this.places) {
			rtr += place.toString() + "\n";
		}
		
		return rtr;		
	}

}
