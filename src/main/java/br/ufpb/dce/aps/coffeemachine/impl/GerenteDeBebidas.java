package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;
import java.util.Set;

import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class GerenteDeBebidas {
	
	private ComponentsFactory factory;
	private Cafe cafe = null;
	private HashMap <Button, Cafe> bebidas = new HashMap <Button, Cafe>();
	private GerenteDeCaixa caixa;
	private HashMap <Button, Integer> precos = new HashMap <Button, Integer>();
	private HashMap <String, Dispenser> ingredientes = new HashMap <String, Dispenser>();

	public GerenteDeBebidas(ComponentsFactory factory){
		this.factory = factory;

		bebidas.put(Button.BUTTON_1, new Cafe(factory));
		bebidas.put(Button.BUTTON_3, new CafeComAcucar(factory));
		bebidas.put(Button.BUTTON_2, new CafeComCreme(factory));
		bebidas.put(Button.BUTTON_4, new CafeComCremeEacucar(factory));
		bebidas.put(Button.BUTTON_5, new Bouillon(factory));	
		bebidas.put(Button.BUTTON_6, new SweetCream(factory));	

		
		precos.put(Button.BUTTON_1, 35);
		precos.put(Button.BUTTON_3, 35);
		precos.put(Button.BUTTON_2, 35);
		precos.put(Button.BUTTON_4, 35);
		precos.put(Button.BUTTON_5, 25);
		//precos.put(Button.BUTTON_6, 50);

	}
	
	public void setPreco(Button drink, int priceCents){
		precos.put(drink, priceCents);

		//GerenteDeMensagens.mostrarPrecos(precos);

		factory.getButtonDisplay().show("Black: $0." + precos.get(Button.BUTTON_1), "White: $0." + precos.get(Button.BUTTON_2),
				"Black with sugar: $0." + precos.get(Button.BUTTON_3), "White with sugar: $0." + precos.get(Button.BUTTON_4),
				"Bouillon: $0." + precos.get(Button.BUTTON_5), "Sweet cream: $0." + precos.get(Button.BUTTON_6), null);
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
		if(cafe == null){
			cafe = bebidas.get(drink);
		}
		cafe.setReceita(recipe);
		if(recipe.getPriceCents() == 0){
			throw new CoffeeMachineException("Preço inválido");
		}
		precos.put(drink, recipe.getPriceCents());
		if(recipe.getItems().size() == 0){
			throw new CoffeeMachineException("Sem itens");
		}
		if(precos.containsKey(Button.BUTTON_6)){
			factory.getButtonDisplay().show("Black: $0." + precos.get(Button.BUTTON_1), "White: $0." + precos.get(Button.BUTTON_2),
					"Black with sugar: $0." + precos.get(Button.BUTTON_3), "White with sugar: $0." + precos.get(Button.BUTTON_4),
					"Bouillon: $0." + precos.get(Button.BUTTON_5), "Sweet cream: $0.50", null);
		
		}else{
			factory.getButtonDisplay().show("Black: $0." + precos.get(Button.BUTTON_1), "White: $0." + precos.get(Button.BUTTON_2),
					"Black with sugar: $0." + precos.get(Button.BUTTON_3), "White with sugar: $0." + precos.get(Button.BUTTON_4),
					"Bouillon: $0." + precos.get(Button.BUTTON_5), null, null);
		
		}
	}

	public void adicionarDispenser(HashMap <String, Dispenser> d) {
		// TODO Auto-generated method stub
		ingredientes.putAll(d);
		Set<String> k = ingredientes.keySet();
		for(String str: k){
			if(cafe == null){
				cafe = bebidas.get(Button.BUTTON_6);
			}
			cafe.addispenser(str, ingredientes.get(str));
		}
	}

}
