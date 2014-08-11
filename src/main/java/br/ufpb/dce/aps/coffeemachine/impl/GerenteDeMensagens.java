package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class GerenteDeMensagens {
	
	private static ComponentsFactory factory;

	private GerenteDeMensagens(ComponentsFactory factory){
		
	}
	
	public static void setFactory(ComponentsFactory f){
		factory = f;
	}

	public static void mostrarMensagemInserirMoedas() {
		factory.getDisplay().info("Insert coins and select a drink!");

	}

	public static void mostrarValorTotal(int dolarValue, int centsValue) {
		factory.getDisplay().info("Total: US$ " + dolarValue + "." + centsValue);

	}

	public static void mostrarmensagemCancelar() {
		factory.getDisplay().warn("Cancelling drink. Please, get your coins.");

	}

	public static void mostrarMensagemDinheiroInsuficiente() {
		factory.getDisplay().warn(Messages.NO_ENOUGHT_MONEY);
	}

	public static void mostrarMensagemTrocoInsuficiente() {
		factory.getDisplay().warn("I do not have enought change");
	}
	
	public static void mostrarMensagemDePegarDrink() {
		factory.getDisplay().info("Please, take your drink.");
	}
}
