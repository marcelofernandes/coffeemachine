package br.ufpb.dce.aps.coffeemachine.impl;

public class CaixaSemTrocoException extends Exception{

	public CaixaSemTrocoException(){
		this("Caixa sem dinheiro suficiente para o troco");
	}
	
	public CaixaSemTrocoException(String mensagem){
		super(mensagem);
	}
}
