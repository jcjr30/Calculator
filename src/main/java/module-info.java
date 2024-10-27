module com.jcjr30.calculatorvtwo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens com.jcjr30.calculatorvtwo to javafx.fxml;
    exports com.jcjr30.calculatorvtwo;
}