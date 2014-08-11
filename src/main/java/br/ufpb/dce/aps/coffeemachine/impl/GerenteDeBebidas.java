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
	}

	public void selecionarBebida(Drink drink, GerenteDeCaixa gerenteDeCaixa){
		cafe = bebidas.get(drink);
		caixa = gerenteDeCaixa;
		try{
			gerenteDeCaixa.verificarDinheiroInserido();
		}catch(DinheiroInsuficienteException e){
			GerenteDeMensagens.mostrarMensagemDinheiroInsuficiente();
			gerenteDeCaixa.retornarMoedas(factory);
			GerenteDeMensagens.mostrarMensagemInserirMoedas();
			return;
		}	
		try {
			if(!cafe.contemIngredientes()){
				throw new IngredientesAcabaramException();
			}
			try {
				gerenteDeCaixa.verificarSeTemTroco(factory);
			} catch (CaixaSemTrocoException e) {
				GerenteDeMensagens.mostrarMensagemTrocoInsuficiente();
				gerenteDeCaixa.retornarMoedas(factory);
				GerenteDeMensagens.mostrarMensagemInserirMoedas();
				return;
			}
			prepararBebida();
			servirBebida();
			GerenteDeMensagens.mostrarMensagemDePegarDrink();
			gerenteDeCaixa.darOtroco(factory);
			GerenteDeMensagens.mostrarMensagemInserirMoedas();
		} catch (IngredientesAcabaramException e) {
			gerenteDeCaixa.retornarMoedas(factory);
			GerenteDeMensagens.mostrarMensagemInserirMoedas();
		}
		gerenteDeCaixa.zerarValores();
	}

	public void prepararBebida() {
		cafe.prepararBebida();
	}

	public void servirBebida() {
		cafe.servirBebida();
	}

}
