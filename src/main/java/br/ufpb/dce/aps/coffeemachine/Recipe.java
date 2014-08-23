package br.ufpb.dce.aps.coffeemachine;

import java.util.HashMap;
import java.util.Map;

public class Recipe {

	public static final String COFFEE_POWDER = "Coffee Powder";
	public static final String WATER = "Water";
	public static final String SUGAR = "Sugar";
	public static final String CREAMER = "Creamer";
	public static final String BOUILLON = "Bouillon Powder";
	public static final String MILK = "Milk";
	public static final String CHOCOLATE = "Chocolate Powder";

	private String name;
	private Map<String,Double> items = new HashMap<String, Double>();
	private int priceCents;
	private String[] planSequence;
	private String[] mixSequence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriceCents() {
		return priceCents;
	}

	public void setPriceCents(int priceCents) {
		this.priceCents = priceCents;
	}

	public void setItem(String ingredient, Double quantity) {
		items.put(ingredient, quantity);
	}

	public Double getIngredientQuantity(String ingredient) {
		return items.get(ingredient);
	}

	public void setPlanSequence(String... planSequence) {
		this.planSequence = planSequence;
	}

	public void setMixSequence(String... mixSequence) {
		this.mixSequence = mixSequence;
	}

	public String[] getPlanSequence() {
		return planSequence;
	}

	public String[] getMixSequence() {
		return mixSequence;
	}
	
}
