package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class Bouillon extends Cafe{

	public Bouillon(ComponentsFactory factory) {
		super(factory);
		precoCafe = 25;
		po = 10;
		poRelease = 80;
		// TODO Auto-generated constructor stub
	}
	
//	@Override
//	public boolean contemIngredientes() {
//		if (!(super.contemIngredientes())){
//			return false;
//		}else{
//			if(!(factory.getCreamerDispenser().contains(20))){
//				factory.getDisplay().warn("Out of Creamer");
//				return false;
//			}
//		} 
//		return true;
//	}
	
	@Override
	public void prepararBebida() {
		super.prepararBebida();
	}
	
	protected boolean contemPo(){
		if (!(factory.getBouillonDispenser().contains(po))) {
			factory.getDisplay().warn("Out of Coffee Powder");
			return false;
		}
		return true;
	}
	
	@Override
	protected void liberarPo(){
		factory.getBouillonDispenser().release(po);
	}
	
	@Override
	protected void liberarAgua(){
		factory.getWaterDispenser().release(100);
	}

}
