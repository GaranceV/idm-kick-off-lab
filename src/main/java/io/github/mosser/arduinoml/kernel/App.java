package io.github.mosser.arduinoml.kernel;

import io.github.mosser.arduinoml.kernel.behavioral.State;
import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.Actuator;
import io.github.mosser.arduinoml.kernel.structural.Brick;
import io.github.mosser.arduinoml.kernel.structural.Sensor;

import java.util.ArrayList;
import java.util.List;

public class App implements NamedElement, Visitable {

	private String name;
	private List<Brick> bricks = new ArrayList<Brick>();
	private List<State> states = new ArrayList<State>();
	private State initial;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public List<Brick> getBricks() {
		return bricks;
	}

	public void setBricks(List<Brick> bricks) {
		this.bricks = bricks;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public State getInitial() {
		return initial;
	}

	public void setInitial(State initial) {
		this.initial = initial;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}


	/**
	 * all the method below are for the DSL
     */

	/**
	 *
	 *
	 * @param name
	 * @return
     */
	public App isNamed(String name) {
		this.name = name;
		return this;
	}


	public Actuator declareActuator() {
		Actuator actuator = new Actuator();
		this.bricks.add(actuator);
		return actuator;
	}

	public Sensor declareSensor() {
		Sensor sensor = new Sensor();
		this.bricks.add(sensor);
		return sensor;
	}

	public State declareState() {
		State state = new State();
		this.states.add(state);
		return state;
	}

	public void declareInitialState(State state) {
		this.initial = state;
	}
}
