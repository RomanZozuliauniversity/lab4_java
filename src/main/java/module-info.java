module ua.lpnu.lab4_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens ua.lpnu.lab4_java to javafx.fxml;
    exports ua.lpnu.lab4_java;
    exports ua.lpnu.lab4_java.methods;
    opens ua.lpnu.lab4_java.methods to javafx.fxml;
}