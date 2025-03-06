package org.bartech.bartech;

import java.util.ArrayList;

public class Soft extends Boisson{
    private static final String nom_DEFAULT = "N/A";
    private static final int prix_DEFAULT = 0;

    public Soft(String nom, int prix, Ingredient... ingredients) {
        super(nom, prix, ingredients);
    }

    public Soft(int prix, Ingredient... ingredients) {
        super(nom_DEFAULT, prix, ingredients);
    }

    public Soft(String nom, Ingredient... ingredients) {
        super(nom, prix_DEFAULT, ingredients);
    }

    public Soft(Ingredient... ingredients) {
        super(nom_DEFAULT, prix_DEFAULT, ingredients);
    }

    public void commanderSoft(int x, Ingredient... ingredients){
        this.setCptCommande(getCptCommande() + x);
        ArrayList<Ingredient> listeTemp = new ArrayList<>();
        listeTemp = this.getListeIngredient();
        for (Ingredient ing : ingredients) {
            listeTemp.remove(ing);
        }
        for (Ingredient ing : listeTemp) {
            ing.setQuantite(ing.getQuantite() - x);
        }
    }

    public void commandeSoft(Soft soft) {}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNom()).append(" , prix : ").append(getPrixBoisson()).append(", ");
        sb.append("Recette :\n");
        for (Ingredient ingredient : getListeIngredient()) {
            sb.append("- ").append(ingredient.getNom()).append("    quantité restantes : ")
                    .append(ingredient.getQuantite()).append("\n");
        }
        return sb.toString();
    }

}
