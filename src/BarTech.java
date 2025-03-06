//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BarTech {
    public static void main(String[] args) {

        Ingredient i1 = new Ingredient("Café",100);
        Ingredient i2 = new Ingredient("Sucre",100);
        Ingredient i3 = new Ingredient("Eau",100);
        Ingredient i4 = new Ingredient("Ananas",100);
        Ingredient i5 = new Ingredient("Rhum Blanc",100);
        Ingredient i6 = new Ingredient("Menthe",100);
        Soft cafe = new Soft("Café", 5, i1, i2, i3);
        Alcool cocktail = new Alcool("Cocktail", 5,8.6, i4, i5, i6);
        Stock stock = new Stock(i1, i2, i3, i4, i5, i6);
        System.out.println(cafe);
        System.out.println(cocktail);
        System.out.println(stock);
        stock.incrementerIngredient(i3, 17);
        stock.incrementerIngredient(i1, 36);
        stock.incrementerIngredient(i3, 37);
        System.out.println(stock);
        cafe.commanderSoft(1, i1);
        cocktail.commanderAlcool(4, i4, i5);
        System.out.println(stock);

        }
    }