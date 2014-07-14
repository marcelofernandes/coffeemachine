package br.ufpb.dce.aps.coffeemachine;

import br.ufpb.dce.aps.coffeemachine.impl.MyCoffeeMachine;

public class MyCoffeeMachineTest extends CoffeeMachineTest{
	Display display;

	@Override
	protected CoffeeMachine createFacade(ComponentsFactory factory) {
		display = factory.getDisplay();
		display.info("Insert coins and select a drink!");
		
		return new MyCoffeeMachine(factory);
	}
	

}
