package org.bartech.bartech;

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

        Button modifierButton = new Button("Modifier");
        TextField quantiteField = new TextField();
        quantiteField.setPromptText("Quantité");

        // Permet de sélectionner un ingrédient dans la liste
        stockList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Extraire le nom de l'ingrédient de l'élément sélectionné
                String name = newValue.split(":")[0].trim();
                Ingredient ingredient = ingredientMap.get(name);
                if (ingredient != null) {
                    // Pré-remplir le champ quantité avec la quantité actuelle de l'ingrédient
                    quantiteField.setText(String.valueOf(ingredient.getQuantite()));
                }
            }
        });

        // Bouton de modification pour mettre à jour la quantité de l'ingrédient sélectionné
        modifierButton.setOnAction(e -> {
            String selectedItem = stockList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String name = selectedItem.split(":")[0].trim();
                Ingredient ingredient = ingredientMap.get(name);
                int newQuantity = Integer.parseInt(quantiteField.getText());
                stock.definirIngredient(ingredient, newQuantity); // Méthode pour mettre à jour la quantité
                updateStockList(stockList); // Mise à jour de l'affichage
            }
        });

        VBox gestionStockSection = new VBox(10, stockLabel, stockList, quantiteField, modifierButton);
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
