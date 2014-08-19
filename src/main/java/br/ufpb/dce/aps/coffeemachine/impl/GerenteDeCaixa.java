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
	private int troco;
	private int precoCafe;
	private boolean leuCracha;
	private ComponentsFactory factory;

	public GerenteDeCaixa(ComponentsFactory factory){
		this.factory = factory;
		this.cents = 0;
		this.dolar = 0;
		leuCracha = false;
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
		if(leuCracha){
			GerenteDeMensagens.leucrachaMensagem();
			retornarMoedas();
			return;
		}
		GerenteDeMensagens.mostrarValorTotal(dolar, cents);
	}
	
	public void cancelar(){
		if ( cents == 0 && dolar == 0) {
			throw new CoffeeMachineException("Sessão cancelada!");
		}
		GerenteDeMensagens.mostrarmensagemCancelar();
		retornarMoedas();
		GerenteDeMensagens.mostrarMensagemInserirMoedas();
	}
	
	private void calcularTroco() {
		this.troco = valorInserido - precoCafe;
		
	}
	
	public void verificarDinheiroInserido(int preco) throws DinheiroInsuficienteException{
		precoCafe = preco;
		if(valorInserido > precoCafe){
			passarTroco = true;
		}else if(valorInserido < precoCafe && !leuCracha){
			passarTroco = false;
			throw new  DinheiroInsuficienteException();

		}else{
			passarTroco = false;
		}
	}
	
	private boolean passarTroco(){
		return this.passarTroco;
	}
	
	private boolean caixaTemTroco(ComponentsFactory factory){
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
		}
		if(troco == 0){
			return true;
		}
		return false;
	}
	
	public void retornarMoedas() {
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
		if(passarTroco() && !leuCracha){
			calcularTroco();
			if(!caixaTemTroco(factory)){
				throw new CaixaSemTrocoException();
			}
		}else if(!passarTroco() && !leuCracha){
			return;
		}else{
			factory.getPayrollSystem().debit(35, 123456);
		}
		
		
	}

	public void lerCracha() {
		if(valorInserido > 0){
			GerenteDeMensagens.mostrarMensagemInseriuMoeda();
			return;
		}
		GerenteDeMensagens.mostrarMensagemCracha();
		leuCracha = true;
	}
	
}
