package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class Cafe {
	
	protected ComponentsFactory factory;

	public Cafe(ComponentsFactory factory){
		this.factory = factory;
	}
	
	protected void release() {
		factory.getDisplay().info(Messages.RELEASING);
		factory.getCupDispenser().release(1);
		factory.getDrinkDispenser().release(1.5);
	}
	
	public boolean contemIngredientes() {
		if (! ( factory.getCupDispenser().contains(1) ) ) {

			factory.getDisplay().warn("Out of Cup");
			return false;
			
		} if (!(factory.getWaterDispenser().contains(2.0))) {

			factory.getDisplay().warn("Out of Water");
			return false;
			
		} if (!(factory.getCoffeePowderDispenser().contains(1.0))) {
			
			factory.getDisplay().warn("Out of Coffee Powder");
			return false;
			
		} 
		return true;
	}
	
	protected void verifyBlackMix() {
		factory.getDisplay().info(Messages.MIXING);
		factory.getCoffeePowderDispenser().release(2.0);
		factory.getWaterDispenser().release(2.0);
	}
	
	protected void displayMessage() {
		factory.getDisplay().info(Messages.TAKE_DRINK);
		passarTroco();
		factory.getDisplay().info(Messages.INSERT_COINS);
	}
	
	public void passarTroco(){
		//TODO
	}

}
