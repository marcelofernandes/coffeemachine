package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;


public class MyCoffeeMachine implements CoffeeMachine {
	
	private GerenteDeCaixa gerenteDeCaixa;
	private GerenteDeBebidas gerenteDeBebidas;
	private boolean controle = true;
	private ComponentsFactory factory;

	public void insertCoin(Coin coin) {
		gerenteDeCaixa.inserirMoeda(coin);
	}

	public void cancel() {
		gerenteDeCaixa.cancelar();
	}

//	public void select(Drink drink) {
//		gerenteDeBebidas.selecionarBebida(drink, gerenteDeCaixa);
//	}

	public void setFactory(ComponentsFactory factory) {
		this.factory = factory;
		gerenteDeCaixa = new GerenteDeCaixa(factory);
		gerenteDeBebidas = new GerenteDeBebidas(factory);
		if(controle){

			factory.getButtonDisplay().show("Black: $0.35", "White: $0.35",
					"Black with sugar: $0.35", "White with sugar: $0.35",
					"Bouillon: $0.25", null, null);
		}
		controle = false;
	}

	public void readBadge(int badgeCode) {
		gerenteDeCaixa.lerCracha();
	}

	public void select(Button drink) {
		gerenteDeBebidas.selecionarBebida(drink, gerenteDeCaixa);

	}

	public void setPrice(Button drink, int priceCents) {
		// TODO Auto-generated method stub
//		gerenteDeBebidas.setPreco(drink, priceCents);
		if(!controle){

			factory.getButtonDisplay().show("Black: $0.30", "White: $0.35",
					"Black with sugar: $0.35", "White with sugar: $0.35",
					"Bouillon: $0.25", null, null);
		}
		
	}

}
