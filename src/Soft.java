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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNom()).append(" , prix : ").append(getPrix()).append(", ");
        sb.append("Recette :\n");
        for (Ingredient ingredient : getListeIngredient()) {
            sb.append("- ").append(ingredient.getNom()).append("    quantité restantes : ")
                    .append(ingredient.getQuantite()).append("\n");
        }
        return sb.toString();
    }

}
