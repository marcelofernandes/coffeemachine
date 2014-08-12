package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class CafeComCreme extends Cafe{

	public CafeComCreme(ComponentsFactory factory) {
		super(factory);
	}
	
	@Override
	public boolean contemIngredientes() {
		if (!(super.contemIngredientes())){
			return false;
		}else{
			if(!(factory.getCreamerDispenser().contains(1.5))){
				factory.getDisplay().warn("Out of Creamer");
				return false;
			}
		} 
		return true;
	}
	
	@Override
	public void prepararBebida() {
		super.prepararBebida();
		factory.getCreamerDispenser().release(1.0);
	}
	
}
