module org.example.graphics3cube {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.graphics3cube to javafx.fxml;
    exports org.example.graphics3cube;
}