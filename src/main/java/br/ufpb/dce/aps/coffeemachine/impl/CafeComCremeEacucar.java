package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class CafeComCremeEacucar extends Cafe{

	public CafeComCremeEacucar(ComponentsFactory factory) {
		super(factory);
		agua = 80;
		po = 15;
		poRelease = 80;
	}
	
	@Override
	public boolean contemIngredientes() {
		if(super.contemIngredientes() && (factory.getCreamerDispenser().contains(20))
				&& (factory.getSugarDispenser().contains(5))){
			return true;
		}
		return false;
	}
	
	@Override
	public void prepararBebida() {
		super.prepararBebida();
		factory.getCreamerDispenser().release(20);
		factory.getSugarDispenser().release(5);
	}

}
