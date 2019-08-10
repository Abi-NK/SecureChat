package rsa_ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainGUI extends Application{

  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      
      AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("GUI.fxml"));
      Scene scene = new Scene(root);
      //scene.getStylesheets().add(getClass().getResource("))
      primaryStage.setScene(scene);
      primaryStage.show();
    
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
