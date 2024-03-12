module com.javacourse.courseprojectfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    requires org.kordamp.bootstrapfx.core;

    opens com.vsh8k.mushop to javafx.fxml;
    exports com.vsh8k.mushop;
    opens com.vsh8k.mushop.fxControllers to javafx.fxml;
    exports com.vsh8k.mushop.fxControllers to javafx.fxml;
}