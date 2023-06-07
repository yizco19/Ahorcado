module yang.ahorcado {
    requires javafx.controls;
    requires javafx.fxml;

    opens yang.ahorcado to javafx.fxml;
    exports yang.ahorcado;
    exports yang.ahorcado.clase;
    opens yang.ahorcado.clase to javafx.fxml;
}
