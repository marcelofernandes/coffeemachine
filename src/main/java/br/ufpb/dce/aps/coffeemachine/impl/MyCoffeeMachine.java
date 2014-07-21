package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine {

	private ComponentsFactory factory;
	private int cents, dolar;

	public MyCoffeeMachine(ComponentsFactory factory) {
		this.cents = 0;
		this.dolar = 0;
		this.factory = factory;
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

		factory.getDisplay().info("Total: US$ " + dolar + "." + cents);
	}

	public void cancel() {
		
		if(cents == 0 && dolar == 0) {
			
			throw new CoffeeMachineException("Sessão cancelada!");
			
		}
		
		factory.getDisplay().warn("Cancelling drink. Please, get your coins.");
		factory.getCashBox().release(Coin.halfDollar);
		factory.getDisplay().info(Messages.INSERT_COINS_MESSAGE);
		
		
	}

}
