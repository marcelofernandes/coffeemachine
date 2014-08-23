package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class SweetCream extends Cafe{

	public SweetCream(ComponentsFactory factory) {
		super(factory);
		precoCafe = 25;
		po = 15.0;
		poRelease = 80.0;
	}
	

	@Override
	public void prepararBebida() {
		super.prepararBebida();
	}
	
	protected boolean contemIngredientes() {
		return super.contemIngredientes() && contemCreme();
	}
	
	public boolean contemCreme(){
		return factory.getCreamerDispenser().contains(25.0);

	
	}
	
	
	
	protected boolean contemPo(){
		if (!(factory.getSugarDispenser().contains(po))) {
			factory.getDisplay().warn("Out of Bouillon Powder");
			return false;
		}
		return true;
	}
	
	@Override
	protected void liberarPo(){
		//factory.getSugarDispenser().release(15.0);
	}
	
	@Override
	protected void liberarAgua(){
		factory.getWaterDispenser().release(100.0);
		factory.getCreamerDispenser().release(25.0);
		factory.getSugarDispenser().release(15.0);

	}

}
