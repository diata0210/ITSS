module app.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.reflections;


    opens app.controller to javafx.fxml;
    opens app.project to javafx.fxml;
    exports app.project;
}