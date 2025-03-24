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

    private Stock stock;
    private Map<String, Ingredient> ingredientMap;
    private Map<String, Button> buttonMap;
    private String selectedIngredient = null;
    private TextField quantiteField;

    private VBox addIngredientForm;

    public BarTechGUI() {
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
        buttonMap = new HashMap<>();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BarTech Management System");
        primaryStage.setMaximized(true); // Plein écran

        // Label principal
        Label stockLabel = new Label("Sélectionnez un ingrédient");
        stockLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Champ de texte pour modifier la quantité
        quantiteField = new TextField();
        quantiteField.setPromptText("Quantité");
        quantiteField.setStyle("-fx-font-size: 18px;");

        // Bouton de modification
        Button modifierButton = new Button("Modifier");
        modifierButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");

        // Boutons pour modifier la quantité par paliers
        Button plus1 = new Button("+1");
        Button moins1 = new Button("-1");
        Button plus10 = new Button("+10");
        Button moins10 = new Button("-10");
        Button plus100 = new Button("+100");
        Button moins100 = new Button("-100");

        Button[] buttons = {plus1, moins1, plus10, moins10, plus100, moins100};

        for (Button btn : buttons) {
            btn.setStyle("-fx-font-size: 14px; -fx-padding: 5px 10px;");
        }

        // Ajout des actions pour ajuster les quantités
        plus1.setOnAction(e -> modifyQuantity(1));
        moins1.setOnAction(e -> modifyQuantity(-1));
        plus10.setOnAction(e -> modifyQuantity(10));
        moins10.setOnAction(e -> modifyQuantity(-10));
        plus100.setOnAction(e -> modifyQuantity(100));
        moins100.setOnAction(e -> modifyQuantity(-100));

        // Conteneur des boutons d'ajustement
        HBox adjustmentButtons = new HBox(10, moins100, moins10, moins1, plus1, plus10, plus100);
        adjustmentButtons.setAlignment(Pos.CENTER);

        // Créer le bouton pour afficher le formulaire d'ajout
        Button showAddFormButton = new Button("Ajouter un ingrédient");
        showAddFormButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");

        // Créer le formulaire d'ajout (initialement masqué)
        Label addIngredientLabel = new Label("Ajouter un ingrédient");
        TextField addNameField = new TextField();
        addNameField.setPromptText("Nom de l'ingrédient");
        TextField addQuantityField = new TextField();
        addQuantityField.setPromptText("Quantité");
        Button addButton = new Button("Ajouter");

        // Action pour ajouter un ingrédient
        addButton.setOnAction(e -> {
            String name = addNameField.getText();
            String quantityText = addQuantityField.getText();
            try {
                int quantity = Integer.parseInt(quantityText);
                if (!name.isEmpty() && quantity > 0) {
                    Ingredient newIngredient = new Ingredient(name, quantity);
                    ingredientMap.put(name, newIngredient);
                    stock.addIngredient(newIngredient);  // Ajouter à la classe Stock
                    updateButtonGrid(); // Mettre à jour les boutons
                    addIngredientForm.setVisible(false); // Cacher le formulaire après ajout
                } else {
                    showAlert("Erreur", "Le nom et la quantité doivent être valides.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Erreur", "Veuillez entrer une quantité valide.");
            }
        });

        // Formulaire d'ajout d'ingrédient
        addIngredientForm = new VBox(10, addIngredientLabel, addNameField, addQuantityField, addButton);
        addIngredientForm.setSpacing(10);
        addIngredientForm.setPadding(new Insets(10));
        addIngredientForm.setVisible(false); // Formulaire caché au départ

        // Action du bouton pour afficher ou masquer le formulaire
        showAddFormButton.setOnAction(e -> {
            boolean isVisible = addIngredientForm.isVisible();
            addIngredientForm.setVisible(!isVisible); // Inverser la visibilité
        });

        // Créer une grille avec les boutons d'ingrédients
        HBox buttonGrid = new HBox(15);
        buttonGrid.setPadding(new Insets(20));
        buttonGrid.setAlignment(Pos.CENTER_LEFT);

        // Ajouter la grille des boutons dans un ScrollPane pour permettre le défilement horizontal
        ScrollPane scrollPane = new ScrollPane(buttonGrid);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Barre de défilement horizontale visible tout le temps
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Pas de barre de défilement verticale

        // Mettre à jour la grille des boutons d'ingrédients
        updateButtonGrid(buttonGrid);

        // Organisation en grille
        VBox gestionStockSection = new VBox(20, stockLabel, scrollPane, quantiteField, adjustmentButtons, modifierButton,
                showAddFormButton, addIngredientForm);
        gestionStockSection.setPadding(new Insets(20));
        gestionStockSection.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gestionStockSection, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Mettre à jour la grille des boutons après l'ajout d'un ingrédient
    private void updateButtonGrid(HBox buttonGrid) {
        buttonGrid.getChildren().clear(); // Vider la grille existante

        for (String ingredientName : ingredientMap.keySet()) {
            Ingredient ingredient = ingredientMap.get(ingredientName);

            Button button = new Button(ingredientName + "\n" + ingredient.getQuantite());
            button.setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: #2c3e50; -fx-text-fill: white;");
            button.setMinSize(200, 100); // Fixe la taille minimale
            button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Permet au bouton de s'étirer

            button.setOnAction(e -> {
                selectedIngredient = ingredientName;
                quantiteField.setText(String.valueOf(ingredient.getQuantite()));
                updateButtonStyles();
            });

            buttonMap.put(ingredientName, button);
            buttonGrid.getChildren().add(button);
        }
    }

    // Méthode pour mettre à jour le stock et rafraîchir les boutons
    private void updateStock() {
        if (selectedIngredient != null) {
            try {
                int newQuantity = Integer.parseInt(quantiteField.getText());
                if (newQuantity < 0) newQuantity = 0; // Empêcher des valeurs négatives
                Ingredient ingredient = ingredientMap.get(selectedIngredient);
                stock.definirIngredient(ingredient, newQuantity);

                // Mise à jour de l'affichage des boutons
                buttonMap.get(selectedIngredient).setText(selectedIngredient + "\n" + newQuantity);
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer un nombre valide.");
            }
        }
    }

    // Méthode pour modifier la quantité via les boutons (+1, -1, +10, -10, ...)
    private void modifyQuantity(int value) {
        if (selectedIngredient != null) {
            try {
                int currentQuantity = Integer.parseInt(quantiteField.getText());
                int newQuantity = currentQuantity + value;
                if (newQuantity < 0) newQuantity = 0; // Empêcher des valeurs négatives

                quantiteField.setText(String.valueOf(newQuantity));
                updateStock();
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer un nombre valide.");
            }
        }
    }

    // Méthode pour mettre à jour les styles des boutons sélectionnés
    private void updateButtonStyles() {
        for (Button button : buttonMap.values()) {
            button.setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: #2c3e50; -fx-text-fill: white;");
        }
        if (selectedIngredient != null) {
            buttonMap.get(selectedIngredient).setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: #27ae60; -fx-text-fill: white;");
        }
    }

    // Méthode pour afficher une alerte en cas d'erreur
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
