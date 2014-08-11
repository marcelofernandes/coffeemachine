package br.ufpb.dce.aps.coffeemachine.impl;

public class IngredientesAcabaramException extends Exception{

	public IngredientesAcabaramException(){
		this("Os ingredientes acabaram");
	}
	
	public IngredientesAcabaramException(String mensagem){
		super(mensagem);
	}
}
