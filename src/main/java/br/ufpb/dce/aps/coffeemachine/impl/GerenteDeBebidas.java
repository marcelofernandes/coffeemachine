package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class GerenteDeBebidas {
	
	private ComponentsFactory factory;
	private Cafe cafe = null;
	private HashMap <Button, Cafe> bebidas = new HashMap <Button, Cafe>();
	private GerenteDeCaixa caixa;
	private HashMap <Button, Integer> precos = new HashMap <Button, Integer>();
	private Recipe receitas;

	public GerenteDeBebidas(ComponentsFactory factory){
		this.factory = factory;

		bebidas.put(Button.BUTTON_1, new Cafe(factory));
		bebidas.put(Button.BUTTON_3, new CafeComAcucar(factory));
		bebidas.put(Button.BUTTON_2, new CafeComCreme(factory));
		bebidas.put(Button.BUTTON_4, new CafeComCremeEacucar(factory));
		bebidas.put(Button.BUTTON_5, new Bouillon(factory));	
		
		precos.put(Button.BUTTON_1, 35);
		precos.put(Button.BUTTON_3, 35);
		precos.put(Button.BUTTON_2, 35);
		precos.put(Button.BUTTON_4, 35);
		precos.put(Button.BUTTON_5, 25);
	}
	
	public void setPreco(Button drink, int priceCents){
		precos.put(drink, priceCents);
		GerenteDeMensagens.mostrarPrecos(precos);
	}
	
	public HashMap <Button, Integer> getPrecos(){
		return precos;
	}

	public void selecionarBebida(Button drink, GerenteDeCaixa gerenteDeCaixa){
		int preco = precos.get(drink);
		cafe = bebidas.get(drink);
		caixa = gerenteDeCaixa;
		
		try{
			caixa.verificarDinheiroInserido(preco);

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

	public void configurarBebidas(Button drink, Recipe recipe) {
		receitas = recipe;
		if(cafe == null){
			cafe = bebidas.get(drink);
		}
		//cafe.setReceita(recipe);
		precos.put(drink, recipe.getPriceCents());
	}

}
