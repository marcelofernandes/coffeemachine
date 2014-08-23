package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class MyCoffeeMachine implements CoffeeMachine {
	
	private GerenteDeCaixa gerenteDeCaixa;
	private GerenteDeBebidas gerenteDeBebidas;

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
		gerenteDeBebidas.configurarBebidas(drink, recipe);
		//GerenteDeMensagens.mostrarPrecos(gerenteDeBebidas.getPrecos());

	}

}
