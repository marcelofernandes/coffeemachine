package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
//import br.ufpb.dce.aps.coffeemachine.Drink;

public class GerenteDeBebidas {
	
	private ComponentsFactory factory;
	private Cafe cafe;
	private HashMap <Button, Cafe> bebidas = new HashMap <Button, Cafe>();
	private HashMap <Button, Integer> precos = new HashMap <Button, Integer>();

	private GerenteDeCaixa caixa;

	public GerenteDeBebidas(ComponentsFactory factory){
		this.factory = factory;
//		bebidas.put(Drink.BLACK, new Cafe(factory));
//		bebidas.put(Drink.BLACK_SUGAR, new CafeComAcucar(factory));
//		bebidas.put(Drink.WHITE, new CafeComCreme(factory));
//		bebidas.put(Drink.WHITE_SUGAR, new CafeComCremeEacucar(factory));
//		bebidas.put(Drink.BOUILLON, new Bouillon(factory));


		bebidas.put(Button.BUTTON_1, new Cafe(factory));
		bebidas.put(Button.BUTTON_3, new CafeComAcucar(factory));
		bebidas.put(Button.BUTTON_2, new CafeComCreme(factory));
		bebidas.put(Button.BUTTON_4, new CafeComCremeEacucar(factory));
		bebidas.put(Button.BUTTON_5, new Bouillon(factory));
		
		
		precos.put(Button.BUTTON_1, 35);
		precos.put(Button.BUTTON_3, 35);
		precos.put(Button.BUTTON_2, 35);
		precos.put(Button.BUTTON_4, 35);
		precos.put(Button.BUTTON_5, 35);
		
	}
	
	
	public void setPreco(Button drink, int priceCents){
		precos.put(drink, priceCents);
	}

//	public void selecionarBebida(Drink drink, GerenteDeCaixa gerenteDeCaixa){
//		cafe = bebidas.get(drink);
//		caixa = gerenteDeCaixa;
//		try{
//			caixa.verificarDinheiroInserido(cafe.getPreco());
//		}catch(DinheiroInsuficienteException e){
//			GerenteDeMensagens.mostrarMensagemDinheiroInsuficiente();
//			caixa.retornarMoedas();
//			GerenteDeMensagens.mostrarMensagemInserirMoedas();
//			return;
//		}
//		
//		if(!cafe.contemIngredientes()){
//			caixa.retornarMoedas();
//			GerenteDeMensagens.mostrarMensagemInserirMoedas();
//			caixa.zerarValores();
//			return;
//		}
//		
//		try {
//			caixa.verificarSeTemTroco(factory);
//		} catch (CaixaSemTrocoException e) {
//			GerenteDeMensagens.mostrarMensagemTrocoInsuficiente();
//			caixa.retornarMoedas();
//			GerenteDeMensagens.mostrarMensagemInserirMoedas();
//			return;
//		}catch(CodigoInexistenteException e){
//			GerenteDeMensagens.mostrarMensagemInserirMoedas();
//			return;
//		}
//		prepararBebida();
//		servirBebida();
//		GerenteDeMensagens.mostrarMensagemDePegarDrink();
//		caixa.darOtroco(factory);
//		GerenteDeMensagens.mostrarMensagemInserirMoedas();
//		caixa.zerarValores();
//	}

	public void selecionarBebida(Button drink, GerenteDeCaixa gerenteDeCaixa){
		
		cafe = bebidas.get(drink);
		caixa = gerenteDeCaixa;
		
		try{
			caixa.verificarDinheiroInserido(cafe.getPreco());
		}catch(DinheiroInsuficienteException e){
			GerenteDeMensagens.mostrarMensagemDinheiroInsuficiente();
			caixa.retornarMoedas();
			GerenteDeMensagens.mostrarMensagemInserirMoedas();
			return;
		}
		
		if(!cafe.contemIngredientes()){
			caixa.retornarMoedas();
			GerenteDeMensagens.mostrarMensagemInserirMoedas();
			caixa.zerarValores();
			return;
		}
		
		try {
			caixa.verificarSeTemTroco(factory);
		} catch (CaixaSemTrocoException e) {
			GerenteDeMensagens.mostrarMensagemTrocoInsuficiente();
			caixa.retornarMoedas();
			GerenteDeMensagens.mostrarMensagemInserirMoedas();
			return;
		}catch(CodigoInexistenteException e){
			GerenteDeMensagens.mostrarMensagemInserirMoedas();
			return;
		}
		prepararBebida();
		servirBebida();
		GerenteDeMensagens.mostrarMensagemDePegarDrink();
		caixa.darOtroco(factory);
		GerenteDeMensagens.mostrarMensagemInserirMoedas();
		caixa.zerarValores();
	}

	public void prepararBebida() {
		cafe.prepararBebida();
	}

	public void servirBebida() {
		cafe.servirBebida();
	}

}
