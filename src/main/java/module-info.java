module com.example.jihanki3000 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens com.example.jihanki3000 to javafx.fxml;
    exports com.example.jihanki3000;
}