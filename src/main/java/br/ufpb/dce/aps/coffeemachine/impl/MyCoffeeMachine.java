package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class MyCoffeeMachine implements CoffeeMachine {
	
	private GerenteDeCaixa gerenteDeCaixa;
	private GerenteDeBebidas gerenteDeBebidas;

	public MyCoffeeMachine(ComponentsFactory factory) {
		gerenteDeCaixa = new GerenteDeCaixa(factory);
		gerenteDeBebidas = new GerenteDeBebidas(factory);
	}

	public void insertCoin(Coin coin) {
		gerenteDeCaixa.inserirMoeda(coin);
	}

	public void cancel() {
		gerenteDeCaixa.cancelar();
	}

	public void select(Drink drink) {
		gerenteDeBebidas.selecionarBebida(drink, gerenteDeCaixa);
	}

}
