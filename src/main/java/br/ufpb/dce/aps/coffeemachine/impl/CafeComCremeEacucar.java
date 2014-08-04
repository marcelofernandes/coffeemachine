package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class CafeComCremeEacucar extends Cafe{

	public CafeComCremeEacucar(ComponentsFactory factory) {
		super(factory);
	}
	
	@Override
	public boolean contemIngredientes() {
		if(super.contemIngredientes() && (factory.getCreamerDispenser().contains(1.5))
				&& (factory.getSugarDispenser().contains(1.0))){
			factory.getCashBox().count(Coin.dime);
			factory.getCashBox().count(Coin.nickel);
			return true;
		}
		return false;
	}
	
	@Override
	public void verifyBlackMix() {
		super.verifyBlackMix();
		factory.getCreamerDispenser().release(1.0);
		factory.getSugarDispenser().release(1.0);
	}
	
	@Override
	public void passarTroco(){
		factory.getCashBox().release(Coin.dime);
		factory.getCashBox().release(Coin.nickel);
	}

}
