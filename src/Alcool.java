import java.util.ArrayList;

public class Alcool extends Boisson{

    private double degre;

    private static String nom_DEFAULT = "N/A";
    private static int prix_DEFAULT = 0;
    private static double degre_DEFAULT = 0;

    public Alcool(String nom, int prix, double degre, Ingredient... ingredients) {
        super(nom, prix, ingredients);
        this.degre = degre;
    }

    public Alcool(int prix, double degre, Ingredient... ingredients) {
        super(nom_DEFAULT, prix, ingredients);
        this.degre = degre;
    }

    public Alcool(String nom, double degre, Ingredient... ingredients) {
        super(nom_DEFAULT, prix_DEFAULT, ingredients);
        this.degre = degre;
    }

    public Alcool(double degre, Ingredient... ingredients) {
        super(nom_DEFAULT, prix_DEFAULT, ingredients);
        this.degre = degre;
    }

    public Alcool(Ingredient... ingredients){
        super(nom_DEFAULT, prix_DEFAULT, ingredients);
        this.degre = degre;
    }

    public void commanderAlcool(int x, Ingredient... ingredients){
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNom()).append(" , prix : ").append(getPrixBoisson());
        sb.append(" , Taux d'Alcoolémie : ").append(degre).append("\n");
        sb.append("Recette :\n");
        for (Ingredient ingredient : getListeIngredient()) {
            sb.append("- ").append(ingredient.getNom()).append("    quantité restantes : ")
                    .append(ingredient.getQuantite()).append("\n");
        }
        return sb.toString();
    }

}
