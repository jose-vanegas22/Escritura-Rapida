package com.example.escriturarapida3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/escriturarapida3/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Escritura Rapida");
        stage.setScene(scene);
        stage.show();


    }






    public static void main(String[] args) {
        launch(args);
    }
}




//git init
//git commit -m ""
//git push origin jdvm