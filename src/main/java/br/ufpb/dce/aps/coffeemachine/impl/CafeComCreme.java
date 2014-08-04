package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class CafeComCreme extends Cafe{

	public CafeComCreme(ComponentsFactory factory) {
		super(factory);
	}
	
	@Override
	public boolean contemIngredientes() {
		return super.contemIngredientes() && (factory.getCreamerDispenser().contains(1.5));
	}
	
	@Override
	public void verifyBlackMix() {
		super.verifyBlackMix();
		factory.getCreamerDispenser().release(1.0);
	}
	
}