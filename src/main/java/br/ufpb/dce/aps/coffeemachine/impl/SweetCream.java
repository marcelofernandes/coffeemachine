package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class SweetCream extends Cafe{

	public SweetCream(ComponentsFactory factory) {
		super(factory);
	}
	
	protected boolean contemIngredientes() {
		factory.getCupDispenser().contains(1);
		if(receita.isSteamed()){
			dispensers.get("Milk").contains(150.0);
			return true;
		}
		if(!dispensers.isEmpty()){
			dispensers.get("Milk").contains(120.0);
			dispensers.get("Chocolate Powder").contains(20.0);
			factory.getSugarDispenser().contains(5.0);
			return true;
		}
		factory.getWaterDispenser().contains(100.0);
		factory.getSugarDispenser().contains(15.0);
		factory.getCreamerDispenser().contains(25.0);
		return true;
	}
	
	@Override
	protected void liberarPo(){
		if(!dispensers.isEmpty()){
			if(receita.isSteamed()){
				dispensers.get("Milk").release(150.0);
				factory.getSteamer().steam();
				return;
			}
			dispensers.get("Milk").release(120.0);
			dispensers.get("Chocolate Powder").release(20.0);
			factory.getSugarDispenser().release(5.0);	
		}
	}
	
	@Override
	protected void liberarAgua(){
		if(!dispensers.isEmpty()) return;
		factory.getWaterDispenser().release(100.0);
		factory.getCreamerDispenser().release(25.0);
		factory.getSugarDispenser().release(15.0);
	}

}
