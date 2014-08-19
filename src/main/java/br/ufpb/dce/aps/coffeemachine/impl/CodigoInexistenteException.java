package br.ufpb.dce.aps.coffeemachine.impl;

public class CodigoInexistenteException extends Exception{

	public CodigoInexistenteException(){
		this("Código inexistente");
	}
	
	public CodigoInexistenteException(String mensagem){
		super(mensagem);
	}
}
