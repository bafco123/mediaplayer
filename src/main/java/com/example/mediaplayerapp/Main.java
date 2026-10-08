package com.example.mediaplayerapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MediaPlayerController controller = new MediaPlayerController();

        Scene scene = new Scene(controller.getRoot(), 1000, 600);

        
        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> controller.handleKeyPress(event.getCode()));

        primaryStage.setTitle("Bafco Sounds");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(400);
        primaryStage.setOnCloseRequest(e -> controller.shutdown());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}