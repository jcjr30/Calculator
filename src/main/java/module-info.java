module com.jcjr30.calculatorv2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.jcjr30.calculatorv2 to javafx.fxml;
    exports com.jcjr30.calculatorv2;
}