package org.bartech.bartech;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Boisson {
    private String nom;
    private ArrayList<Ingredient> listeIngredient;
    private int prix;
    private static int cptCommande;

    private static String nom_DEFAULT = "N/A";
    private static int prix_DEFAULT = 0;

    public Boisson(String nom, int prix, Ingredient... ingredients) {
        this.nom = nom;
        this.prix = prix;
        this.listeIngredient = new ArrayList<Ingredient>(Arrays.asList(ingredients));
    }

    public Boisson(String nom, Ingredient... ingredients) {
        this.nom = nom;
        this.prix = prix_DEFAULT;
        this.listeIngredient = new ArrayList<Ingredient>(Arrays.asList(ingredients));
    }

    public Boisson(int prix, Ingredient... ingredients) {
        this.nom = nom_DEFAULT;
        this.prix = prix;
        this.listeIngredient = new ArrayList<Ingredient>(Arrays.asList(ingredients));
    }

    public Boisson() {
        this.nom = nom_DEFAULT;
        this.prix = prix;
        this.listeIngredient = new ArrayList<Ingredient>();
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Ingredient> getListeIngredient() {
        return listeIngredient;
    }

    public void setListeIngredientBoisson(ArrayList<Ingredient> listeIngredient) {
        this.listeIngredient = listeIngredient;
    }

    public int getPrixBoisson() {
        return prix;
    }

    public void setPrixBoisson(int prix) {
        this.prix = prix;
    }

    public static int getCptCommande() {
        return cptCommande;
    }

    public static void setCptCommande(int cptCommande) {
        Boisson.cptCommande = cptCommande;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nom).append(" , prix : ").append(prix).append("\n");
        sb.append("Recette :\n");
        for (Ingredient ingredient : listeIngredient) {
            sb.append("- ").append(ingredient.getNom()).append("    quantité restantes : ")
                    .append(ingredient.getQuantite()).append("\n");
        }
        return sb.toString();
    }
}

