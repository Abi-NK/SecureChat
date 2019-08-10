package messenger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MessengerMainGUI extends Application{
  private double xOffset = 0;
  private double yOffset = 0;

  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      
      AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MessengerGUI.fxml"));
      
      //from https://stackoverflow.com/questions/13206193/how-to-make-an
      //-undecorated-window-movable-draggable-in-javafx
      //Makes the UI dragable since there is no exit button.
      primaryStage.initStyle(StageStyle.UNDECORATED);
      root.setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
              xOffset = event.getSceneX();
              yOffset = event.getSceneY();
          }
      });
      root.setOnMouseDragged(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
              primaryStage.setX(event.getScreenX() - xOffset);
              primaryStage.setY(event.getScreenY() - yOffset);
          }
      });
      
      //scene.getStylesheets().add(getClass().getResource("))
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
    
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}