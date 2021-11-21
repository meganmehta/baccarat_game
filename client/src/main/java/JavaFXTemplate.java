import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.io.Serializable;


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
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextArea;
import javafx.scene.Group;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;




public class JavaFXTemplate extends Application {
	HashMap<String, Scene> sceneMap;
	Button startBtn, connectBtn, exitBtn, playAgainBtn;
	Stage primaryStage;
	Scene introScene, gameScene;
	Text welcome, playerWins, currentWinnings;
	ArrayList<Serializable> clientActions;
	ArrayList<String> items;
	ListView actions;
	TextField t1, t2;
	Label portNum, ipAddy, bidLab, bidDropLab, playSpace, bankSpace;
	VBox allStuff, gameControls, bidStuff, cardSpace;
	HBox portNumberBox, ipBox, playerActionsBox, bidMenu, bidBox;
	ComboBox bidDrop;
	Rectangle playerSpace, bankerSpace;
	Client clientConnection;
	BaccaratInfo recastGame;
	String gameWinner, userBetSelect;
	TextArea results;
	String p1c1Val, p1c1Suit, p1card1FP;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		sceneMap = new HashMap<String,Scene>();
		primaryStage.setTitle("Baccarat Client");

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
		connectBtn = new Button("Connect to Server");
		connectBtn.setStyle("-fx-background-color: yellow; ");

		clientActions = new ArrayList<Serializable>(); //list view with all client variables (from BaccaratInfo)
		//when startBtn is clicked, switch to gameActionsScreen when theres stuff in the text field
		connectBtn.setOnAction(e -> {
			if(t1.getText() != "" && t2.getText() != "") {
				primaryStage.setScene(sceneMap.get("gameScreen"));
				clientConnection = new Client(gameInfo->{
					Platform.runLater(()->{
						//recast
						BaccaratInfo recastGame = (BaccaratInfo)gameInfo;
						clientActions.add(gameInfo); //adds everything from BaccaratInfo once the game has ran
						System.out.println("winner: " + recastGame.gameWinner);
						if (recastGame.gameWinner.equals(recastGame.userBetChoice)){
								results.setText("Player Total: " + recastGame.playerGameWins + " Banker Total: " + recastGame.bankerGameWins +
										"...." + recastGame.gameWinner +
										" wins!" + " Congrats, you bet " + recastGame.userBetChoice + "! You win! ");
							}
							else{
								results.setText("Player Total: "+ recastGame.playerGameWins + " Banker Total: "+ recastGame.bankerGameWins +
										 "...."+ recastGame.gameWinner +
										" wins!" + " Sorry, you bet " + recastGame.userBetChoice + "! You lost your bet!");

							}
						p1c1Val += Integer.toString(recastGame.playerHand.get(0).value);
						p1c1Suit += recastGame.playerHand.get(0).suit;
						currentWinnings.setText("" + recastGame.totalWinnings);
					});
				});
				clientConnection.start();
		}});


		VBox allStuff = new VBox(50, welcome, portNumberBox, ipBox, connectBtn);
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

		//game results
		Label result1 = new Label("Game Results:");
		result1.setStyle("-fx-font-weight: bold");
		result1.setFont(new Font("Verdana", 12));
		result1.setTextFill(Color.WHITE);

		results = new TextArea();
		results.setPrefHeight(50);
		results.setPrefWidth(50);

		VBox resultSpace = new VBox(10,result1, results);
		resultSpace.setAlignment(Pos.TOP_CENTER);

		//create player space (include cards)
		Label playSpace = new Label("Player Cards: ");
		playSpace.setStyle("-fx-font-weight: bold");
		playSpace.setFont(new Font("Verdana", 16));
		playSpace.setTextFill(Color.WHITE);

		//creating player cards - REPLICATE FOR ALL BANKER + PLAYER CARDS
		/*Rectangle rec1 = new Rectangle(75,100);
		p1c1Val = new String();
		p1c1Suit = new String();
		p1card1FP = p1c1Val + p1c1Suit + ".jpg";
		rec1.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/cards/" + p1card1FP))));
		HBox playerCards = new HBox(20, rec1);*/

		//banker cards

		//create banker space (include spaces for cards)
		Label bankSpace = new Label("Banker Cards: ");
		bankSpace.setStyle("-fx-font-weight: bold");
		bankSpace.setFont(new Font("Verdana", 16));
		bankSpace.setTextFill(Color.WHITE);
		//Number and letter arrays for the image location path
		/*String number[] = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13"};
		String letter[] = new String[] {"C", "D", "H", "S"};
		StackPane pics = new StackPane();
		int posX = 0;
		int posY = 0;
		try{
			for(int i = 0; i < number.length; i++) {
				for(int j = 0; j < letter.length; j++) {
					FileInputStream inputstream = 
							new FileInputStream("C:\\Users\\rezar\\Downloads\\baccarat_game\\client\\src\\main\\resources\\cards\\" + number[i] + letter[j] + ".jpg");
					Image image = new Image(inputstream); 
					ImageView imageView = new ImageView(image);
					//Setting the position of the image 
					imageView.setX(posX+=50); 
					imageView.setY(posY+=25); 
		    
					//setting the fit height and width of the image view 
					imageView.setFitHeight(100); 
					imageView.setFitWidth(100); 
		      
					//Setting the preserve ratio of the image view 
					imageView.setPreserveRatio(true);  
					pics.getChildren().add(imageView);
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Something caught");
		}*/
		
		VBox cardSpace = new VBox(30, playSpace, bankSpace); //pics

		//------BID STUFF-------
		//bid text field
		Label bidLab = new Label("Bid Amount: ");
		TextField bid = new TextField();
		bid.setPromptText("Enter bit amount here");
		bidLab.setTextFill(Color.web("#FFFFFF"));
		HBox bidBox = new HBox(bidLab, bid);
		bidBox.setAlignment(Pos.TOP_CENTER);

		//bidding on selection - drop down menu
		Label bidDropLab = new Label("Bidding on: ");
		bidDropLab.setTextFill(Color.web("#FFFFFF"));
		ComboBox bidDrop = new ComboBox();

		bidDrop.getItems().add("Player");
		bidDrop.getItems().add("Banker");
		bidDrop.getItems().add("Draw");
		HBox bidMenu = new HBox(bidDropLab, bidDrop);

		//*create vertical box for bid amount + bidding on selection
		VBox bidStuff = new VBox(10, bidBox, bidMenu);
		bidStuff.setAlignment(Pos.TOP_CENTER);

		//create actions bar at bottom of game screen
		//-----GAME CONTROLS------
		startBtn = new Button("Start Game");
		startBtn.setOnAction(event -> {
			double betA = Double.valueOf(bid.getText());
			BaccaratInfo gameInformation = new BaccaratInfo(betA, (String) bidDrop.getValue(),
					null, null, null, null, null, false, 0, 0, 0, 0);
			clientConnection.send(gameInformation); //sends the choice of who the user bet on
		});

		exitBtn = new Button("Exit");
		exitBtn.setOnAction(event -> {
			Platform.exit();
		});

		playAgainBtn = new Button("Play Again!");
		playAgainBtn.setOnAction(event -> {
			this.primaryStage.setScene(createGameScene());
			this.primaryStage.show();
			//sends the choice of another game or not
			BaccaratInfo gameInformation = new BaccaratInfo(0, null, null,
					null, null, null, null, true, 0, 0, 0, 0);
			clientConnection.send(gameInformation);
		});

		VBox gameControls = new VBox(10, startBtn, exitBtn, playAgainBtn);
		gameControls.setAlignment(Pos.TOP_CENTER);

		//-----DISPLAY PLAYER WINNINGS------
		//*player winnings here - have to update with winnings number from Game functions
		Text playerWins = new Text("Player Wins:");
		playerWins.setFill(Color.WHITE);
		playerWins.setStyle("-fx-font-weight: bold");
		
		currentWinnings = new Text();
		currentWinnings.setFill(Color.WHITE);
		currentWinnings.setStyle("-fx-font-weight: bold; -fx-padding: 50 0 0 0;");

		VBox stats = new VBox(5, playerWins, currentWinnings);

		//create horizontal box - game actions
		HBox playerActionsBox = new HBox(100, stats, bidStuff, gameControls);
		playerActionsBox.setAlignment(Pos.TOP_CENTER);

		BorderPane root = new BorderPane();
		root.setPadding(new Insets(50));
		root.setTop(resultSpace);
		root.setCenter(cardSpace);
		root.setBottom(playerActionsBox);

		Scene gameScene = new Scene(root, 850, 750);
		gameScene.getRoot().setStyle("-fx-text-fill: white; -fx-background-color: green;-fx-font-family: 'verdana';");


		return gameScene;
	}

}
