package org.bartech.bartech;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

        stock = new Stock(cafe, sucre, eau, ananas, rhum_Blanc, menthe);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BarTech Management System");
        primaryStage.setMaximized(true); // Plein écran

        // Label principal
        Label stockLabel = new Label("Gestion du Stock");
        stockLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Liste des ingrédients en stock
        ListView<String> stockList = new ListView<>();
        updateStockList(stockList);

        // Champ de texte pour modifier la quantité
        TextField quantiteField = new TextField();
        quantiteField.setPromptText("Quantité");
        quantiteField.setStyle("-fx-font-size: 18px;");

        // Bouton de modification
        Button modifierButton = new Button("Modifier");
        modifierButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");

        // Sélection de l'ingrédient dans la liste
        stockList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String name = newValue.split(":")[0].trim();
                Ingredient ingredient = ingredientMap.get(name);
                if (ingredient != null) {
                    quantiteField.setText(String.valueOf(ingredient.getQuantite()));
                }
            }
        });

        // Action du bouton Modifier
        modifierButton.setOnAction(e -> {
            String selectedItem = stockList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String name = selectedItem.split(":")[0].trim();
                Ingredient ingredient = ingredientMap.get(name);
                int newQuantity = Integer.parseInt(quantiteField.getText());
                stock.definirIngredient(ingredient, newQuantity);
                updateStockList(stockList);
            }
        });

        // Boutons pour chaque ingrédient
        HBox ingredientButtons = new HBox(15);
        ingredientButtons.setAlignment(Pos.CENTER);
        ingredientButtons.setPadding(new Insets(10));

        for (String ingredientName : ingredientMap.keySet()) {
            Button button = new Button(ingredientName);
            button.setStyle("-fx-font-size: 20px; -fx-padding: 15px 30px; -fx-background-color: #2c3e50; -fx-text-fill: white;");
            button.setOnAction(e -> {
                Ingredient ingredient = ingredientMap.get(ingredientName);
                quantiteField.setText(String.valueOf(ingredient.getQuantite()));
            });
            ingredientButtons.getChildren().add(button);
        }

        // Organisation en grille
        VBox gestionStockSection = new VBox(20, stockLabel, stockList, quantiteField, modifierButton, ingredientButtons);
        gestionStockSection.setPadding(new Insets(20));
        gestionStockSection.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gestionStockSection, 800, 600);
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
