package br.ufpb.dce.aps.coffeemachine.impl;

import static org.mockito.Matchers.anyDouble;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dce.aps.coffeemachine.CashBox;
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
		
		for(Coin coin: Coin.reverse()){
			for(int i = 0; i < coins.size(); i++){
				if(coins.get(i).equals(coin) ){
					factory.getCashBox().release(coins.get(i));
				}
			}
		}
		
		this.coins.clear();
		
		factory.getDisplay().info(Messages.INSERT_COINS);

	}
	
	
//	Selecionar o um café preto e não retornar troco. 
//	Ou seja, o valor inserido foi exatamante o valor do café. 
//	Garanta que ao termino da seleção, seja iniciada uma nova sessão, 
//	mostrando a mensagem de inserir moedas.

	public void select(Drink drink) {
		int copo = 1;
		double agua = 1.5;
		double poDeCafe = 2.0;
		
			
		
		if(drink.equals(Drink.BLACK)){
//			if(factory.getCoffeePowderDispenser().contains(Drink.BLACK)){
//				factory.getDisplay().info(Messages.INSERT_COINS);
//			}
			factory.getCupDispenser().contains(copo);
			factory.getWaterDispenser().contains(agua);
			factory.getCoffeePowderDispenser().contains(poDeCafe);
			
			factory.getDisplay().info(Messages.MIXING);
			factory.getCoffeePowderDispenser().release(poDeCafe);
			factory.getWaterDispenser().release(agua);
			
			factory.getDisplay().info(Messages.RELEASING);
			factory.getCupDispenser().release(copo);
			factory.getDrinkDispenser().release(agua);
			factory.getDisplay().info(Messages.TAKE_DRINK);	
			
			factory.getDisplay().info(Messages.INSERT_COINS);	
		}
	}

}
