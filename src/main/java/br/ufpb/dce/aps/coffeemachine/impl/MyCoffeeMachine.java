package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
	private int cents, dolar;
	private List<Coin> coins;

	public MyCoffeeMachine(ComponentsFactory factory) {
		this.cents = 0;
		this.dolar = 0;
		this.factory = factory;
		this.coins = new ArrayList<Coin>();
		factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin coin) {
		if (coin == null) {
			throw new CoffeeMachineException(
					"Por favor, insira uma moeda verdadeira!");
		}
		
		int total = 0;
		total += coin.getValue();
		
		cents += total % 100;
		dolar += total / 100;
		coins.add(coin);
		factory.getDisplay().info("Total: US$ " + dolar + "." + cents);
	}

	public void cancel() {
		if (cents == 0 && dolar == 0) {
			throw new CoffeeMachineException("Sessão cancelada!");
		}
		
		factory.getDisplay().warn("Cancelling drink. Please, get your coins.");
		retornarMoedas();
		factory.getDisplay().info(Messages.INSERT_COINS);
	}

	private void retornarMoedas() {
		for (Coin coin : Coin.reverse()) {
			for (int i = 0; i < coins.size(); i++) {
				if (coins.get(i).equals(coin)) {
					factory.getCashBox().release(coins.get(i));
				}
			}
		}
	}

	public void select(Drink drink) {
		Cafe cafe = null;
		
		switch (drink) {
		case BLACK:
			cafe = new Cafe(factory);
			break;
		case WHITE:
			cafe = new CafeComCreme(factory);
			break;
		case BLACK_SUGAR:
			cafe = new CafeComAcucar(factory);
			break;
		case WHITE_SUGAR:
			cafe = new CafeComCremeEacucar(factory);
			break;
		}
		
		if (cafe.contemIngredientes()) {
			cafe.verifyBlackMix();
			cafe.release();
			cafe.displayMessage();
		} else {
			retornarMoedas();
			factory.getDisplay().info(Messages.INSERT_COINS);
		}
		
		this.coins.clear();
	}

}
