package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class Cafe {
	
	protected ComponentsFactory factory;

	public Cafe(ComponentsFactory factory){
		this.factory = factory;
	}
	
	protected void servirBebida() {
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
	
	protected void prepararBebida() {
		factory.getDisplay().info(Messages.MIXING);
		factory.getCoffeePowderDispenser().release(2.0);
		factory.getWaterDispenser().release(2.0);
	}

}
