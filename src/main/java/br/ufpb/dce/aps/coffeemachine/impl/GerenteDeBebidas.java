package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class GerenteDeBebidas {
	
	private ComponentsFactory factory;
	private Cafe cafe;
	private HashMap <Drink, Cafe> bebidas = new HashMap <Drink, Cafe>();
	private GerenteDeCaixa caixa;

	public GerenteDeBebidas(ComponentsFactory factory){
		this.factory = factory;
		bebidas.put(Drink.BLACK, new Cafe(factory));
		bebidas.put(Drink.BLACK_SUGAR, new CafeComAcucar(factory));
		bebidas.put(Drink.WHITE, new CafeComCreme(factory));
		bebidas.put(Drink.WHITE_SUGAR, new CafeComCremeEacucar(factory));
		bebidas.put(Drink.BOUILLON, new Bouillon(factory));

	}

	public void selecionarBebida(Drink drink, GerenteDeCaixa gerenteDeCaixa){
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
