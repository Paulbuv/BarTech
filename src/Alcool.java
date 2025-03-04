public class Alcool extends Boisson{

    private int degre;
    private static int degre_DEFAULT = 0;

    public Alcool(String nom, int prix, int degre, Ingredient... ingredients) {
        super(nom, prix, ingredients);
        this.degre = degre;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNom()).append(" , prix : ").append(getPrix());
        sb.append(" , Taux d'Alcoolémie : ").append(degre).append("\n");
        sb.append("Recette :\n");
        for (Ingredient ingredient : getListeIngredient()) {
            sb.append("- ").append(ingredient.getNom()).append("    quantité restantes : ")
                    .append(ingredient.getQuantite()).append("\n");
        }
        return sb.toString();
    }

}
