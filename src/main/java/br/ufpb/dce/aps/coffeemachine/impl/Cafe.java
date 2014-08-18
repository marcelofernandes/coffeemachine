package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class Cafe {
	
	protected ComponentsFactory factory;
	protected int agua;
	protected int po;
	protected int poRelease;
	protected int precoCafe;
	

	public Cafe(ComponentsFactory factory){
		this.factory = factory;
		agua = 100;
		po = 15;
		poRelease = 100;
		precoCafe = 35;
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
		factory.getWaterDispenser().release(poRelease);
	}

	protected void liberarPo(){
		factory.getCoffeePowderDispenser().release(15);
	}
	
	protected void liberarCopo(){
		factory.getCupDispenser().release(1);
	}
	
	protected void liberarBebida(){
		factory.getDrinkDispenser().release(100);
	}

	public int getPreco() {
		return precoCafe;
	}
}
