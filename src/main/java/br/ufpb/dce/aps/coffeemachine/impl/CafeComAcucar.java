package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class CafeComAcucar extends Cafe{

	public CafeComAcucar(ComponentsFactory factory) {
		super(factory);
	}
	
	@Override
	public boolean contemIngredientes() {
		if (!(super.contemIngredientes())){
			return false;
		}else{
			if(!(factory.getSugarDispenser().contains(1.5))){
				factory.getDisplay().warn("Out of Sugar");
				return false;
			}
		} 
		return true;
	}
	
	@Override
	public void prepararBebida() {
		super.prepararBebida();
		factory.getSugarDispenser().release(1.0);
	}

}
