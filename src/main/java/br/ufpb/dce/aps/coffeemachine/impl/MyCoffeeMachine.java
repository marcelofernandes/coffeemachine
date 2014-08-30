package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class MyCoffeeMachine implements CoffeeMachine {
	
	private GerenteDeCaixa gerenteDeCaixa;
	private GerenteDeBebidas gerenteDeBebidas;
	private HashMap <String, Dispenser> ingredientes = new HashMap <String, Dispenser>();


	public void insertCoin(Coin coin) {
		gerenteDeCaixa.inserirMoeda(coin);
	}

	public void cancel() {
		gerenteDeCaixa.cancelar();
	}

	public void setFactory(ComponentsFactory factory) {
		gerenteDeCaixa = new GerenteDeCaixa(factory);
		gerenteDeBebidas = new GerenteDeBebidas(factory);
		GerenteDeMensagens.mostrarPrecos(gerenteDeBebidas.getPrecos());
	}

	public void readBadge(int badgeCode) {
		gerenteDeCaixa.lerCracha();
	}

	public void select(Button drink) {
		gerenteDeBebidas.selecionarBebida(drink, gerenteDeCaixa);
	}

	public void setPrice(Button drink, int priceCents) {
		gerenteDeBebidas.setPreco(drink, priceCents);
	}

	public void configuteDrink(Button drink, Recipe recipe) {
		if(ingredientes.size()>0){
			gerenteDeBebidas.adicionarDispenser(ingredientes);
		}
		gerenteDeBebidas.configurarBebidas(drink, recipe);
	}

	public void addDispenser(String ingredient, Dispenser dispenser) {
		// TODO Auto-generated method stub
		//gerenteDeBebidas.adicionarDispenser(ingredient, dispenser);
		ingredientes.put(ingredient, dispenser);
	}

}
