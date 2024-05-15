package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Login;
public final class App extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Login login = new Login();
        login.start(stage);
    }

    public static void main (String []args){
        launch(args);
    }
}