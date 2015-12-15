package io.github.mosser.arduinoml.kernel.samples;

import io.github.mosser.arduinoml.kernel.App;
import io.github.mosser.arduinoml.kernel.behavioral.State;
import io.github.mosser.arduinoml.kernel.generator.ToWiring;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.Brick;
import io.github.mosser.arduinoml.kernel.structural.SIGNAL;

/**
 * Created by Quentin on 12/15/2015.
 */
public class SwitchWithDSL {

    public static void main(String[] args) {
        App mySwitch = new App();
        mySwitch.isNamed("The Switch !");

        Brick actuator = mySwitch.declareActuator().named("LED").plugOnPin(12);
        Brick sensor = mySwitch.declareSensor().named("button").plugOnPin(9);

        State on = mySwitch.declareState().named("on");
        on.executing()
                .onActuator(actuator)
                .theValue(SIGNAL.HIGH);


        State off = mySwitch.declareState().named("off");
        off.executing()
                .onActuator(actuator)
                .theValue(SIGNAL.LOW);

        on.declareTransition()
                .toState(off)
                .whenSensor(sensor)
                .isOn(SIGNAL.HIGH);

        off.declareTransition()
                .toState(on)
                .whenSensor(sensor)
                .isOn(SIGNAL.LOW);

        mySwitch.declareInitialState(off);

        // Generating Code
        Visitor codeGenerator = new ToWiring();
        mySwitch.accept(codeGenerator);

        // Printing the generated code on the console
        System.out.println(codeGenerator.getResult());



    }
}
