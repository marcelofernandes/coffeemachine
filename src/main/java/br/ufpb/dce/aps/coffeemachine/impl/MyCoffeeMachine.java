package br.ufpb.dce.aps.coffeemachine.impl;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InOrder;

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
	private double acucar, poDeCafe, agua, creme;
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
		this.creme = 1.0;

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

	public void select(Drink drink) {
		if(drink.equals(Drink.WHITE)){
			verifyBlackPlan();
			
			factory.getCreamerDispenser().contains(creme);
			
			verifyBlackMix();
			factory.getCreamerDispenser().release(creme);
			
			
			factory.getDisplay().info(Messages.RELEASING);
			factory.getCupDispenser().release(copo);
			factory.getDrinkDispenser().release(agua);
			
			factory.getDisplay().info(Messages.TAKE_DRINK);
			factory.getDisplay().info(Messages.INSERT_COINS);

			this.coins.clear();
			return;

		}
		prepararCafe(drink);
		

	}

	private void prepararCafe(Drink drink){
		if (!(factory.getCupDispenser().contains(copo))) {

			factory.getDisplay().warn("Out of Cup");
			retornarMoedas();
			factory.getDisplay().info(Messages.INSERT_COINS);
			return;
		} else if (!(factory.getWaterDispenser().contains(agua))) {

			factory.getDisplay().warn("Out of Water");
			retornarMoedas();
			factory.getDisplay().info(Messages.INSERT_COINS);
		} else if (!(factory.getCoffeePowderDispenser().contains(poDeCafe))) {

			factory.getDisplay().warn("Out of Coffee Powder");
			retornarMoedas();
			factory.getDisplay().info(Messages.INSERT_COINS);
		} else {

			if (drink == Drink.BLACK_SUGAR
					&& !(factory.getSugarDispenser().contains(acucar))) {
				factory.getDisplay().warn("Out of Sugar");
				retornarMoedas();
				factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}
			verifyBlackMix();
			if (drink == Drink.BLACK_SUGAR) {
				factory.getSugarDispenser().release(acucar);
			}

			factory.getDisplay().info(Messages.RELEASING);
			factory.getCupDispenser().release(copo);
			factory.getDrinkDispenser().release(agua);
			factory.getDisplay().info(Messages.TAKE_DRINK);
			factory.getDisplay().info(Messages.INSERT_COINS);

			this.coins.clear();
		}
	}
	
	private void verifyBlackPlan() {
		factory.getCupDispenser().contains(1);
		factory.getWaterDispenser().contains(2.0);
		factory.getCoffeePowderDispenser().contains(1.0);
	}
	
	private void verifyBlackMix() {
		factory.getDisplay().info(Messages.MIXING);
		factory.getCoffeePowderDispenser().release(poDeCafe);
		factory.getWaterDispenser().release(agua);
	}
}
