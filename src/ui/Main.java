package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.CardsBook;


public class Main extends Application {
	
	private CardsBook cardsbook;
	private CardsBookGUI cardsbookgui;
	
	public Main() {
		cardsbook = new CardsBook();
		cardsbookgui = new CardsBookGUI(cardsbook);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		fxmlLoader.setController(cardsbookgui);
		Parent root = fxmlLoader.load();
		cardsbookgui.setStage(primaryStage);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cards Book");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon-white.png")));
		cardsbookgui.loadLogin();
	}

}