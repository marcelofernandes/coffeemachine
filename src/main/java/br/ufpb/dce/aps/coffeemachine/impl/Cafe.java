package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class Cafe{
	
	protected ComponentsFactory factory;
	protected double agua;
	protected double po;
	protected double poRelease;
	protected double precoCafe;
//	protected Recipe receita;
	

	public Cafe(ComponentsFactory factory){
		this.factory = factory;
//		receita = new Recipe();
		agua = 100.0;
		po = 15.0;
		poRelease = 100.0;
		precoCafe = 35.0;
	}
	
	protected void setReceita(Recipe recipe){
		agua = recipe.getIngredientQuantity("Water");
		//po = recipe.getIngredientQuantity("Coffee Powder");
	}
	
	protected void servirBebida() {
		factory.getDisplay().info(Messages.RELEASING);
		liberarCopo();
		liberarBebida();
	}
	
	protected boolean contemIngredientes() {
		return contemCopo() && contemAgua() && contemPo();
	}
	
	protected boolean contemCopo(){
		if (! ( factory.getCupDispenser().contains(1) ) ) {
			factory.getDisplay().warn("Out of Cup");
			return false;	
		}
		return true;
	}
	
	protected boolean contemAgua(){
		if (!(factory.getWaterDispenser().contains(agua))) {
			factory.getDisplay().warn("Out of Water");
			return false;
		}
		return true;
	}
	
	protected boolean contemPo(){
		if (!(factory.getCoffeePowderDispenser().contains(po))) {
			factory.getDisplay().warn("Out of Coffee Powder");
			return false;
		}
		return true;
	}
	
	protected void prepararBebida() {
		factory.getDisplay().info(Messages.MIXING);
		liberarPo();
		liberarAgua();
	}
	
	protected void liberarAgua(){
		factory.getWaterDispenser().release(agua);
	}

	protected void liberarPo(){
		factory.getCoffeePowderDispenser().release(15.0);
	}
	
	protected void liberarCopo(){
		factory.getCupDispenser().release(1);
	}
	
	protected void liberarBebida(){
		factory.getDrinkDispenser().release();
	}

	public double getPreco() {
		return precoCafe;
	}
}
