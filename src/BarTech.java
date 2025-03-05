//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BarTech {
    public static void main(String[] args) {

        Ingredient i1 = new Ingredient("Café",200);
        Ingredient i2 = new Ingredient("Sucre",200);
        Ingredient i3 = new Ingredient("Eau",200);
        Ingredient i4 = new Ingredient("Ananas",394);
        Ingredient i5 = new Ingredient("Rhum Blanc",189);
        Ingredient i6 = new Ingredient("Menthe",46);
        Boisson cafe = new Soft("Café", 5, i1, i2, i3);
        Boisson cocktail = new Alcool("Cocktail", 5,8.6, i4, i5, i6);
        System.out.println(cafe);
        System.out.println(cocktail);

        }
    }