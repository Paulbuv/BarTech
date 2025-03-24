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
    private FlowPane buttonFlow;

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
        primaryStage.setMaximized(true);

        Label stockLabel = new Label("Sélectionnez un ingrédient");
        stockLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        quantiteField = new TextField();
        quantiteField.setPromptText("Quantité");
        quantiteField.setStyle("-fx-font-size: 18px;");

        Button modifierButton = new Button("Modifier");
        modifierButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");
        modifierButton.setOnAction(e -> updateStock());
        quantiteField.setOnAction(e -> updateStock());

        // Boutons d'ajustement de la quantité
        Button plus1 = new Button("+1"), moins1 = new Button("-1");
        Button plus10 = new Button("+10"), moins10 = new Button("-10");
        Button plus100 = new Button("+100"), moins100 = new Button("-100");

        Button[] buttons = {plus1, moins1, plus10, moins10, plus100, moins100};
        for (Button btn : buttons) {
            btn.setStyle("-fx-font-size: 14px; -fx-padding: 5px 10px;");
        }

        plus1.setOnAction(e -> modifyQuantity(1));
        moins1.setOnAction(e -> modifyQuantity(-1));
        plus10.setOnAction(e -> modifyQuantity(10));
        moins10.setOnAction(e -> modifyQuantity(-10));
        plus100.setOnAction(e -> modifyQuantity(100));
        moins100.setOnAction(e -> modifyQuantity(-100));

        HBox adjustmentButtons = new HBox(10, moins100, moins10, moins1, plus1, plus10, plus100);
        adjustmentButtons.setAlignment(Pos.CENTER);

        // Conteneur des boutons d'ingrédients avec disposition automatique
        buttonFlow = new FlowPane();
        buttonFlow.setHgap(15);
        buttonFlow.setVgap(15);
        buttonFlow.setPadding(new Insets(20));
        buttonFlow.setAlignment(Pos.CENTER);
        buttonFlow.setPrefWrapLength(800); // Largeur max avant passage à la ligne

        // Ajout dynamique des boutons
        for (String ingredientName : ingredientMap.keySet()) {
            addIngredientButton(ingredientName);
        }

        // Ajout du FlowPane dans un ScrollPane pour permettre le scroll vertical
        ScrollPane scrollPane = new ScrollPane(buttonFlow);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        // Formulaire d'ajout d'ingrédient
        Button showAddFormButton = new Button("Ajouter un ingrédient");
        showAddFormButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");

        Label addIngredientLabel = new Label("Ajouter un ingrédient");
        TextField addNameField = new TextField();
        addNameField.setPromptText("Nom de l'ingrédient");
        TextField addQuantityField = new TextField();
        addQuantityField.setPromptText("Quantité");
        Button addButton = new Button("Ajouter");

        addButton.setOnAction(e -> {
            String name = addNameField.getText();
            String quantityText = addQuantityField.getText();
            try {
                int quantity = Integer.parseInt(quantityText);
                if (!name.isEmpty() && quantity > 0) {
                    Ingredient newIngredient = new Ingredient(name, quantity);
                    ingredientMap.put(name, newIngredient);
                    stock.addIngredient(newIngredient);
                    addIngredientButton(name);
                    addIngredientForm.setVisible(false);
                } else {
                    showAlert("Erreur", "Le nom et la quantité doivent être valides.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Erreur", "Veuillez entrer une quantité valide.");
            }
        });

        addIngredientForm = new VBox(10, addIngredientLabel, addNameField, addQuantityField, addButton);
        addIngredientForm.setSpacing(10);
        addIngredientForm.setPadding(new Insets(10));
        addIngredientForm.setVisible(false);

        showAddFormButton.setOnAction(e -> addIngredientForm.setVisible(!addIngredientForm.isVisible()));

        VBox gestionStockSection = new VBox(20, stockLabel, scrollPane, quantiteField, adjustmentButtons, modifierButton,
                showAddFormButton, addIngredientForm);
        gestionStockSection.setPadding(new Insets(20));
        gestionStockSection.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gestionStockSection, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addIngredientButton(String ingredientName) {
        Ingredient ingredient = ingredientMap.get(ingredientName);
        Button button = new Button(ingredientName + "\n" + ingredient.getQuantite());
        button.setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: #2c3e50; -fx-text-fill: white;");
        button.setMinSize(150, 80);

        button.setOnAction(e -> {
            selectedIngredient = ingredientName;
            quantiteField.setText(String.valueOf(ingredient.getQuantite()));
            updateButtonStyles();
        });

        buttonMap.put(ingredientName, button);
        buttonFlow.getChildren().add(button);
    }

    private void updateButtonStyles() {
        for (Button button : buttonMap.values()) {
            button.setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: #2c3e50; -fx-text-fill: white;");
        }
        if (selectedIngredient != null && buttonMap.containsKey(selectedIngredient)) {
            buttonMap.get(selectedIngredient).setStyle("-fx-font-size: 18px; -fx-padding: 20px; -fx-background-color: #27ae60; -fx-text-fill: white;");
        }
    }

    private void modifyQuantity(int value) {
        if (selectedIngredient != null) {
            try {
                int currentQuantity = Integer.parseInt(quantiteField.getText());
                int newQuantity = Math.max(currentQuantity + value, 0);
                quantiteField.setText(String.valueOf(newQuantity));

                Ingredient ingredient = ingredientMap.get(selectedIngredient);
                stock.definirIngredient(ingredient, newQuantity);

                buttonMap.get(selectedIngredient).setText(selectedIngredient + "\n" + newQuantity);
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer une quantité valide.");
            }
        }
    }

    private void updateStock() {
        if (selectedIngredient != null) {
            try {
                // Récupère la nouvelle quantité entrée dans le champ de texte
                int newQuantity = Integer.parseInt(quantiteField.getText());

                // Mets à jour le stock avec la nouvelle quantité
                Ingredient ingredient = ingredientMap.get(selectedIngredient);
                stock.definirIngredient(ingredient, newQuantity);  // Méthode dans la classe Stock pour mettre à jour le stock

                // Mets à jour le texte sur le bouton pour refléter la nouvelle quantité
                buttonMap.get(selectedIngredient).setText(selectedIngredient + "\n" + newQuantity);

                // Mise à jour de l'interface utilisateur (par exemple, en affichant un message de succès)
                showAlert("Succès", "Stock mis à jour avec succès.");
            } catch (NumberFormatException e) {
                // En cas d'entrée invalide (par exemple, une valeur non numérique)
                showAlert("Erreur", "Veuillez entrer une quantité valide.");
            }
        }
    }


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
