module com.javacourse.courseprojectfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    requires mysql.connector.j;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.apache.commons.codec;

    opens com.vsh8k.mushop to javafx.fxml;
    exports com.vsh8k.mushop;
    opens com.vsh8k.mushop.fxControllers to javafx.fxml;
    exports com.vsh8k.mushop.fxControllers to javafx.fxml;
}