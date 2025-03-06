public class Ingredient {
    private String nom;
    private int quantite;
    private static int idIngredient;

    private static String nom_DEFAULT = "N/A";
    private static int quantite_DEFAULT = 0;

    public Ingredient(String name, int quantite) {
        this.nom = name;
        this.quantite = quantite;
    }

    public Ingredient(int quantite) {
        this.nom = nom_DEFAULT;
        this.quantite = quantite;
    }

    public Ingredient(String nom) {
        this.nom = nom;
        this.quantite = quantite_DEFAULT;
    }

    public Ingredient() {
        this.nom = nom_DEFAULT;
        this.quantite = quantite_DEFAULT;
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

    public boolean quantiteFaible() {
        if (this.quantite <= 25 && this.quantite >= 10) {
            System.out.println("Quantite de "+ this.nom + " faible");
            return true;
        }
        return false;
    }

    public boolean quantiteTresFaible() {
        if(this.quantite < 10) {
            System.out.println("Quantite de "+ this.nom + " très faible");
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return  nom +
                ", quantite : " + quantite;
    }
}
