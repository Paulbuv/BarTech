

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class BarTechGUI extends Application {

    private Stock stock; // Instance de la classe Stock
    private Map<String, Ingredient> ingredientMap;
    public BarTechGUI() {

        // Initialisation du stock avec les ingrédients
        Ingredient cafe = new Ingredient("Café", 100);
        Ingredient sucre = new Ingredient("Sucre", 100);
        Ingredient eau = new Ingredient("Eau", 100);
        Ingredient ananas = new Ingredient("Ananas", 100);
        Ingredient rhum_Blanc = new Ingredient("Rhum Blanc", 100);
        Ingredient menthe = new Ingredient("Menthe", 100);
        ingredientMap = new HashMap<>();
        ingredientMap.put("Café", cafe);
        ingredientMap.put("Sucre", sucre);
        ingredientMap.put("Eau", eau);
        ingredientMap.put("Ananas", ananas);
        ingredientMap.put("Rhum Blanc", rhum_Blanc);
        ingredientMap.put("Menthe", menthe);

        stock = new Stock(cafe,sucre,eau,ananas,rhum_Blanc,menthe);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BarTech Management System");

        // Section pour afficher les ingrédients en stock
        Label stockLabel = new Label("Gestion du Stock");
        ListView<String> stockList = new ListView<>();
        updateStockList(stockList); // Mise à jour initiale de la liste

        Button ajouterButton = new Button("Ajouter");
        Button diminuerButton = new Button("Diminuer");
        TextField ingredientField = new TextField();
        ingredientField.setPromptText("Nom de l'ingrédient");
        TextField quantiteField = new TextField();
        quantiteField.setPromptText("Quantité");

        // Boutons pour ajouter / diminuer un ingrédient
        ajouterButton.setOnAction(e -> {
            String name = ingredientField.getText();
            Ingredient ingredient = ingredientMap.get(name);
            int qty = Integer.parseInt(quantiteField.getText());
            stock.incrementerIngredient(ingredient, qty); // Utilisation de la méthode Stock
            updateStockList(stockList); // Mise à jour de l'affichage
        });

        diminuerButton.setOnAction(e -> {
            String name = ingredientField.getText();
            Ingredient ingredient = ingredientMap.get(name);
            int qty = Integer.parseInt(quantiteField.getText());
            stock.decrementerIngredient(ingredient, qty); // Méthode personnalisée à implémenter
            updateStockList(stockList);
        });

        VBox gestionStockSection = new VBox(10, stockLabel, stockList, ingredientField, quantiteField, ajouterButton, diminuerButton);
        gestionStockSection.setPadding(new Insets(10));
        gestionStockSection.setStyle("-fx-border-color: black;");

        // Mise en page principale
        Scene scene = new Scene(gestionStockSection, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateStockList(ListView<String> stockList) {
        stockList.getItems().clear();
        for (Ingredient ingredient : stock.getListeIngredients()) {
            stockList.getItems().add(ingredient.getNom() + ": " + ingredient.getQuantite());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

