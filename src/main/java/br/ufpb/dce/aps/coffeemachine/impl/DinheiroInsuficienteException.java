package br.ufpb.dce.aps.coffeemachine.impl;

public class DinheiroInsuficienteException extends Exception{

	public DinheiroInsuficienteException(){
		this("Dinheiro inserido é insuficiente");
	}
	
	public DinheiroInsuficienteException(String mensagem){
		super(mensagem);
	}
	
}
