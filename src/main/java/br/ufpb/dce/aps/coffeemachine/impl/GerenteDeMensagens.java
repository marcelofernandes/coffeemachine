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
		factory.getDisplay().info(Messages.INSERT_COINS);

	}

	public static void mostrarValorTotal(int dolarValue, int centsValue) {
		factory.getDisplay().info("Total: US$ " + dolarValue + "." + centsValue);

	}

	public static void mostrarmensagemCancelar() {
		factory.getDisplay().warn(Messages.CANCEL);

	}

	public static void mostrarMensagemDinheiroInsuficiente() {
		factory.getDisplay().warn(Messages.NO_ENOUGHT_MONEY);
	}

	public static void mostrarMensagemTrocoInsuficiente() {
		factory.getDisplay().warn(Messages.NO_ENOUGHT_CHANGE);
	}
	
	public static void mostrarMensagemDePegarDrink() {
		factory.getDisplay().info(Messages.TAKE_DRINK);
	}

	public static void mostrarMensagemCracha() {
		factory.getDisplay().info(Messages.BADGE_READ);

	}
}
