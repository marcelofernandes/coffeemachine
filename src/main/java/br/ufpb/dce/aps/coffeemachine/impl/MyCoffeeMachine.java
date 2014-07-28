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
	List<Coin> coins;
	private double acucar, poDeCafe, agua;
	private int copo;

	public MyCoffeeMachine(ComponentsFactory factory) {
		this.cents = 0;
		this.dolar = 0;
		this.factory = factory;
		this.coins = new ArrayList<Coin>();

		this.copo = 1;
		this.agua = 1.5;
		this.poDeCafe = 2.0;
		this.acucar = 1.0;

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
		this.coins.clear();
	}

	// Ao tentar selecionar uma bebida, a máquina vai estar sem pó de café.
	// Garanta que o sistema mande a mensagem “out_of_coffePowder” e retorne as
	// moedas inseridas, além de iniciar uma nova sessão.

	public void select(Drink drink) {
		if (factory.getCupDispenser().contains(copo)
				&& factory.getWaterDispenser().contains(agua)
				&& factory.getCoffeePowderDispenser().contains(poDeCafe)) {

			if (drink == Drink.BLACK_SUGAR ) factory.getSugarDispenser().contains(acucar);
			//		&& !(factory.getSugarDispenser().contains(acucar)) ){
//				factory.getDisplay().warn("Out of Coffee Powder");
//				retornarMoedas();
//				factory.getDisplay().info(Messages.INSERT_COINS);
		//	}

			
			factory.getDisplay().info(Messages.MIXING);
			factory.getCoffeePowderDispenser().release(poDeCafe);
			factory.getWaterDispenser().release(agua);

			if (drink == Drink.BLACK_SUGAR) {
				factory.getSugarDispenser().release(acucar);
			}

			factory.getDisplay().info(Messages.RELEASING);
			factory.getCupDispenser().release(copo);
			factory.getDrinkDispenser().release(agua);
			factory.getDisplay().info(Messages.TAKE_DRINK);
			factory.getDisplay().info(Messages.INSERT_COINS);

			this.coins.clear();
		}else{
			factory.getDisplay().warn("Out of Coffee Powder");
			retornarMoedas();
			factory.getDisplay().info(Messages.INSERT_COINS);
		}
		
		
	}

}
