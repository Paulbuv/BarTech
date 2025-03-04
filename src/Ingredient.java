public class Ingredient {
    private String nom;
    private int quantite;
    private static int idIngredient;


    public Ingredient(String name, int quantite) {
        this.nom = name;
        this.quantite = quantite;
    }
    public Ingredient() {
        this.nom = nom;
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return  nom +
                ", quantite : " + quantite;
    }
}
