module org.bartech.bartech {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.bartech.bartech to javafx.fxml;
    exports org.bartech.bartech;
}