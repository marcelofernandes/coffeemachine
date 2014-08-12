package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class GerenteDeCaixa {
	private int cents, dolar;
	private List<Coin> coins;
	private List<Coin> moedasDoTroco;
	private boolean passarTroco;
	private int valorInserido;
	private boolean daPraComprar;
	private int troco;
	private static final int PRECO_CAFE = 35;
	private ComponentsFactory factory;

	public GerenteDeCaixa(ComponentsFactory factory){
		this.factory = factory;
		this.cents = 0;
		this.dolar = 0;
		this.troco = 0;
		this.passarTroco = false;
		this.valorInserido = 0;
		this.coins = new ArrayList<Coin>();
		this.moedasDoTroco = new ArrayList<Coin>();
		GerenteDeMensagens.setFactory(factory);
		GerenteDeMensagens.mostrarMensagemInserirMoedas();
	}
	
	public void inserirMoeda(Coin coin) {
		if (coin == null) {
			throw new CoffeeMachineException(
					"Por favor, insira uma moeda verdadeira!");
		}
		valorInserido += coin.getValue();
		cents += coin.getValue() % 100;
		dolar += coin.getValue() / 100;
		coins.add(coin);
		if(valorInserido > PRECO_CAFE){
			passarTroco = true;
			daPraComprar = true;
		}else if(valorInserido < PRECO_CAFE){
			daPraComprar = false;
			passarTroco = false;
		}else{
			daPraComprar = true;
			passarTroco = false;
		}
		GerenteDeMensagens.mostrarValorTotal(dolar, cents);

	}
	
	public void cancelar(){
		if ( cents == 0 && dolar == 0) {
			throw new CoffeeMachineException("Sessão cancelada!");
		}
		GerenteDeMensagens.mostrarmensagemCancelar();
		retornarMoedas(factory);
		GerenteDeMensagens.mostrarMensagemInserirMoedas();
	}
	
	public void calcularTroco() {
		this.troco = valorInserido - PRECO_CAFE;
		
	}
	
	public int getDolarValue(){
		return dolar;
	}

	public int getCentsValue(){
		return cents;
	}
	
	public void verificarDinheiroInserido() throws DinheiroInsuficienteException{
		if(!this.daPraComprar){
			throw new  DinheiroInsuficienteException();
		}
	}
	
	public boolean passarTroco(){
		return this.passarTroco;
	}
	
	public boolean caixaTemTroco(ComponentsFactory factory){
		int aux;
		
		for (Coin coin : Coin.reverse()) {
			aux = troco;
			int qtdMoedas = 0;
			
			while ( (coin.getValue() <= aux) && (aux -coin.getValue() >= 0) ) {
				qtdMoedas++;
				aux -= coin.getValue();
			}
			
			if((qtdMoedas > 0) ){
				if(qtdMoedas <= (factory.getCashBox().count(coin))){
					
					
					for(int i = 0; i < qtdMoedas; i++){
						moedasDoTroco.add(coin);
						troco -= coin.getValue();
					}
				
				}
				
			}
			
			if(troco == 0){
				return true;
			}
		}
		
		if(troco == 0){
			return true;
		}
		
		return false;
	}
	
	public void retornarMoedas(ComponentsFactory factory) {
		for (Coin coin : Coin.reverse()) {
			for (int i = 0; i < coins.size(); i++) {
				if (coins.get(i).equals(coin)) {
					factory.getCashBox().release(coins.get(i));
				}
			}
		}
		zerarValores();
	}
	
	public void zerarValores(){
		this.valorInserido = 0;	
		this.coins.clear();
		this.moedasDoTroco.clear();
	}

	public void darOtroco(ComponentsFactory factory) {
		if(passarTroco){
			for (Coin coin : Coin.reverse()) {
				for (int i = 0; i < moedasDoTroco.size(); i++) {
					if (moedasDoTroco.get(i).equals(coin)) {
						factory.getCashBox().release(moedasDoTroco.get(i));
					}
				}
			}
			zerarValores();
		}
	}

	public void verificarSeTemTroco(ComponentsFactory factory) throws CaixaSemTrocoException{
		if(passarTroco()){
			calcularTroco();
			if(!caixaTemTroco(factory)){
				throw new CaixaSemTrocoException();
			}
		}
	}
	
}
