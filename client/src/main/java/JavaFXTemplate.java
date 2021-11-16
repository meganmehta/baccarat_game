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
	Scene introScene, gameScene;
	Text welcome;
	ListView actions;
	TextField t1, t2;
	Label portNum, ipAddy;
	VBox allStuff;
	HBox portNumberBox, ipBox, playerActionsBox;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		sceneMap = new HashMap<String,Scene>();
		primaryStage.setTitle("Baccarat (Client)");

		Text welcome = new Text("Welcome to Baccarat!");

		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

		//create HBox for portNumber label + textField
		Label portNum = new Label("Port Number: ");
		t1 = new TextField(); //user entry in center
		t1.setPromptText("Enter port number here");
		HBox portNumberBox = new HBox(portNum, t1);
		portNumberBox.setAlignment(Pos.CENTER);

		//create HBox for ipAddy label + textField
		Label ipAddy = new Label("IP Address: ");
		t2 = new TextField(); //user entry in center
		t2.setPromptText("Enter IP address here");
		HBox ipBox = new HBox(ipAddy, t2);
		ipBox.setAlignment(Pos.CENTER);

		//connect to server button
		startBtn = new Button("Connect to Server");
		startBtn.setStyle("-fx-background-color: yellow; ");

		//when startBtn is clicked, switch to gameActionsScreen
		startBtn.setOnAction(e -> primaryStage.setScene(sceneMap.get("gameScreen")));

		VBox allStuff = new VBox(50, welcome, portNumberBox, ipBox, startBtn);
		allStuff.setAlignment(Pos.CENTER);

		BorderPane welcomePane = new BorderPane();
		welcomePane.setPadding(new Insets(70));
		welcomePane.setCenter(allStuff);

		//add all scenes/screens in hashmap
		sceneMap.put("gameScreen", createGameScene());

		Scene introScene = new Scene(welcomePane, 850, 750);

		//add any styline elements in this function
		introScene.getRoot().setStyle("-fx-background-color: pink;-fx-font-family: 'verdana';");

		primaryStage.setScene(introScene);
		primaryStage.show();
		this.primaryStage = primaryStage;
	}

	public Scene createGameScene() {

		ListView actions = new ListView();
		actions.setPrefWidth(100);
		actions.setPrefHeight(70);

		//create player space

		//create banker space

		//create actions bar


		HBox playerActionsBox = new HBox(ipAddy, t2);

		//listMoves.getItems().add("");

		BorderPane root = new BorderPane();

		Scene gameScene = new Scene(root, 850, 750);
		gameScene.getRoot().setStyle("-fx-background-color: green;-fx-font-family: 'verdana';");

		return gameScene;
	}

}
