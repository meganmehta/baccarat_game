import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.ListView;
import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;
import javafx.geometry.Pos;

public class JavaFXTemplate extends Application {

	HashMap<String, Scene> sceneMap;
	Button startBtn;
	Stage primaryStage;
	Scene introScene, gameActionsScene;
	Text welcome;
	ListView gameActions;
	TextField t1;
	Label portNum;
	VBox allStuff;
	HBox portNumberBox;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		sceneMap = new HashMap<String,Scene>();

		primaryStage.setTitle("Baccarat Server");

		Text welcome = new Text("Welcome to Baccarat!");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

		//create HBox for portNumber label + textField
		Label portNum = new Label("Port Number: ");
		t1 = new TextField(); //user entry in center
		t1.setPromptText("Enter port number here");
		HBox portNumberBox = new HBox(portNum, t1);
		portNumberBox.setAlignment(Pos.CENTER);

		startBtn = new Button("Start Server");
		startBtn.setStyle("-fx-background-color: yellow; ");

		//when startBtn is clicked, switch to gameActionsScreen
		startBtn.setOnAction(e -> primaryStage.setScene(sceneMap.get("gameActionsScreen")));

		VBox allStuff = new VBox(50, welcome, portNumberBox, startBtn);
		allStuff.setAlignment(Pos.CENTER);

		BorderPane welcomePane = new BorderPane();
		welcomePane.setPadding(new Insets(70));
		welcomePane.setCenter(allStuff);

		//add all scenes/screens in hashmap
		sceneMap.put("gameActionsScreen", createGameActionsScene());

		Scene introScene = new Scene(welcomePane, 850, 750);

		//add any styline elements in this function
		introScene.getRoot().setStyle("-fx-background-color: linear-gradient(to right, green, yellow);-fx-font-family: 'verdana';");

		primaryStage.setScene(introScene);
		primaryStage.show();
		this.primaryStage = primaryStage;

	}

	public Scene createGameActionsScene() {

		//list has to inclue:
		//- how many clients are connected to the server.
		//- The results of each game played by any client.
		//- how much the a client bet on each game
		//- how much a client won or lost on each game
		//- if a client drops off the server.
		//- if a new client joins the server.
		//- is the client playing another hand.


		ListView listMoves = new ListView();
		listMoves.setPrefWidth(100);
		listMoves.setPrefHeight(70);

		//while server is on? then...
		//when client joins, output "client X has joined. there are now Y clients connected to the server"

		listMoves.getItems().add("");

		BorderPane root = new BorderPane();
		root.setCenter(gridPane);

		Scene gameActionsScene = new Scene(root, 850, 750);
		gameActionsScene.getRoot().setStyle("-fx-background-color: green;-fx-font-family: 'verdana';");

		return gameActionsScene;
	}

}
