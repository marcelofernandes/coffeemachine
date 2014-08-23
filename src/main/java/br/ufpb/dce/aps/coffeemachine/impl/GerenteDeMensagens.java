package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Button;
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

	public static void leucrachaMensagem() {
		factory.getDisplay().warn(Messages.CAN_NOT_INSERT_COINS);

	}

	public static void mostrarMensagemInseriuMoeda() {
		factory.getDisplay().warn(Messages.CAN_NOT_READ_BADGE);

	}
	
	
	
	public static void mostrarPrecos(HashMap<Button, Integer> precos){
		factory.getButtonDisplay().show("Black: $0." + precos.get(Button.BUTTON_1), "White: $0." + precos.get(Button.BUTTON_2),
				"Black with sugar: $0." + precos.get(Button.BUTTON_3), "White with sugar: $0." + precos.get(Button.BUTTON_4),
				"Bouillon: $0." + precos.get(Button.BUTTON_5), null, null);
	}
}
