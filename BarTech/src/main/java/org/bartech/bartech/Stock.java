package org.bartech.bartech;

import java.util.ArrayList;
import java.util.Arrays;

public class Stock {
    private ArrayList<Ingredient> listeIngredients;

    public Stock(Ingredient ... ingredients) {
        this.listeIngredients = new ArrayList<Ingredient>(Arrays.asList(ingredients));
    }

    public Stock() {
        this.listeIngredients = new ArrayList<Ingredient>();
    }

    public ArrayList<Ingredient> getListeIngredients() {
        return listeIngredients;
    }

    public void setListeIngredients(ArrayList<Ingredient> listeIngredients) {
        this.listeIngredients = listeIngredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.listeIngredients.add(ingredient);
    }

    public void retirerIngredient(Ingredient ingredient) {
        this.listeIngredients.remove(ingredient);
    }

    public void decrementerIngredient(Ingredient ingredient, int quantite) {
        ingredient.setQuantite(ingredient.getQuantite() - quantite);
    }
    public void incrementerIngredient(Ingredient ingredient, int quantite) {
        ingredient.setQuantite(ingredient.getQuantite() + quantite);
    }
    public void reinitialiserIngredient(Ingredient ingredient) {
        ingredient.setQuantite(0);
    }



    @Override
    public String toString() {
        return "Stock{ " +
                "listeIngredients=" + listeIngredients +
                '}';
    }
}
