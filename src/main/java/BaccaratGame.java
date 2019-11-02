/* Prog.: Baccarat JavaFX GUI
 * Desc : This project implements a one player version of the popular casino game Baccarat. 
 *        This is a somewhat simple game to understand and play.
 *
 * Name : Marcin Perkowski
 * NetID: mperko6
 * 
 * Name : Jonathan Vega
 * NetID: jvega30
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BaccaratGame extends Application
{
	Scene scene;
	MenuBar menu;
	
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;
	double currentBet;
	double totalWinnings;
	double totalMoney;
	double winnings;
	private Button buttonPlay;
	private Button buttonConfirmBet;
	private Button buttonNextRound;
	String betted = "";
	String winner = "";
	PauseTransition pause = new PauseTransition(Duration.seconds(2));

//------------------------------------------------------------------
	public double evaluateWinnings()
    {
		if(betted.equals(winner))
        {
            if(betted.equals("Player"))
            {
                return currentBet;
            }
            else if(betted.equals("Banker"))
            {
                return currentBet *  0.90;
            }
            else if(betted.equals("Draw"))
            {
                return currentBet * 7;
            }
        }
        else
        {
            return 0 - currentBet;
        }
        
        return 0;
    }
//------------------------------------------------------------------	
	public static void main(String[] args)
	{
		launch(args); //needed to launch the GUI window
		
		System.out.println("Bye-bye!");
	}
	
		@Override
//------------------------------------------------------------------

		public void start(Stage primaryStage) throws Exception
		{	    
			primaryStage.setTitle("Baccarat - v1.00 (beta)");
			
			DecimalFormat df2 = new DecimalFormat("#.##"); //allows to format into $XX.XX format

			BaccaratGame b = new BaccaratGame();
			
			b.totalMoney = 1000.00;
			b.totalWinnings = 0.00;
			b.winnings = 0.00;
			
			b.theDealer = new BaccaratDealer();
			b.theDealer.generateDeck();
			b.theDealer.shuffleDeck();
			
			/* Menu -> Sets the menu bar and options up */
			MenuBar menu = new MenuBar();
			//1st menu bar and sub-categories//
			Menu mOne = new Menu("Options");
			MenuItem iOne = new MenuItem("Fresh Start");
			MenuItem iTwo = new MenuItem("Exit");
			iTwo.setOnAction(e->Platform.exit()); //on clicking, "Exit", the application terminates
			//2nd menu bar -> shows current money available to spend//
			Menu mTwo = new Menu("Total Money: $" + df2.format(b.totalMoney));
			//3rd menu bar -> shows total winnings and losses so far//
			Menu mThree = new Menu("Total Winnings: $" + df2.format(b.totalWinnings));

			mOne.getItems().addAll(iOne, iTwo);
			menu.getMenus().addAll(mOne, mTwo, mThree);
			
			/* Radio Button */
	        ToggleGroup tg = new ToggleGroup(); //create a toggle group
	        VBox paneForRadioButtons = new VBox(250);
	        paneForRadioButtons.setPadding(new Insets(5, 5, 5, 5)); 

	        //create radio buttons//
	        RadioButton r1 = new RadioButton("Player"); 
	        RadioButton r2 = new RadioButton("Banker"); 
	        RadioButton r3 = new RadioButton("Draw");
	        
	        // set radio buttons to toggle group//
	        r1.setToggleGroup(tg);
	        r2.setToggleGroup(tg);
	        r3.setToggleGroup(tg);
	        
	        paneForRadioButtons.getChildren().addAll(r1, r2, r3); //add the radio buttons to toggle group (allows choice separation)
			
	        /* Labels */
			Label about = new Label("Programmed by Marcin Perkowski and Jonathan Vega");
			Label error = new Label("Error - Insuffiecient");
			Label playerAreaName = new Label("PLAYER");
			Label dealerAreaName = new Label("BANKER");
			Label pTotal = new Label();
			Label dTotal = new Label();
			
			/* Buttons */
			buttonPlay = new Button("Play"); //for 1st scene, allows to move to 2nd scene on click
			buttonConfirmBet = new Button("OK"); //for 2nd scene, allows to move to 3nd scene on click
			buttonNextRound = new Button("Next Round"); //for 3rd scene, allows to move to 2nd scene on click
			
			/* Text Field */
			TextField textfieldHowMuchToBet = new TextField(); //user enters here the total cost to bet
			textfieldHowMuchToBet.setPromptText("How much will you like to bet?");
			
			/* Window Setup */ 

			BorderPane borderPane = new BorderPane(); //allows to place objects into 5 sections in a window
			
			VBox menuBar = new VBox(menu); //used to display the menu, always place in bordanPane (top)
			
			VBox playerSide = new VBox(35);
	        playerSide.setPadding(new Insets(7, 7, 7, 7));
	        playerSide.setStyle("-fx-border-width: 1px; -fx-border-color: black");
			
	        VBox dealerSide = new VBox(35);
	        dealerSide.setPadding(new Insets(7, 7, 7, 7));
	        dealerSide.setStyle("-fx-border-width: 1px; -fx-border-color: black");
	        
	        VBox centerSide = new VBox(35);
	        centerSide.setPadding(new Insets(7, 7, 7, 7));
	        
//1st Scene	        
	        
			borderPane.setTop(menuBar);
			borderPane.setCenter(buttonPlay);
		    BorderPane.setAlignment(about, Pos.CENTER);
		    borderPane.setBottom(about);
		    
		    
			b.theDealer = new BaccaratDealer();
			b.playerHand = new ArrayList<Card>();
			b.bankerHand = new ArrayList<Card>();
			b.gameLogic = new BaccaratGameLogic();
		    
			iOne.setOnAction(new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent action)
				{
					borderPane.setLeft(null);
					borderPane.setRight(null);
					borderPane.setBottom(null);
					borderPane.setCenter(null);
					
					b.totalMoney = 1000.00;
					b.totalWinnings = 0.00;
					b.winnings = 0.00;
					mTwo.setText("Total Money: $" + df2.format(b.totalMoney));
					mThree.setText("Total Winnings: $" + df2.format(b.totalWinnings));
					borderPane.setCenter(buttonPlay);
				    BorderPane.setAlignment(about, Pos.CENTER);
				    borderPane.setBottom(about);
					playerSide.getChildren().clear();
					dealerSide.getChildren().clear();
					centerSide.getChildren().clear();
					about.setText("Programmed by Marcin Perkowski and Jonathan Vega");
				}
			});		    
			
//2nd Scene	 
			buttonPlay.setOnAction(new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent action)
				{
					borderPane.setCenter(textfieldHowMuchToBet);
					BorderPane.setAlignment(buttonConfirmBet, Pos.CENTER);
					buttonPlay.setText("OK");
					borderPane.setRight(buttonConfirmBet);
					borderPane.setBottom(null);
					r1.setSelected(true);
					borderPane.setLeft(paneForRadioButtons);
					BorderPane.setAlignment(paneForRadioButtons, Pos.CENTER);
				}
			});

//3rd Scene				
			buttonConfirmBet.setOnAction(new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent action)
				{
					if((Double.valueOf(textfieldHowMuchToBet.getText()) <= b.totalMoney) && (Double.valueOf(textfieldHowMuchToBet.getText()) > 0 ))
					{
						String path1, path2, path3, path4, path5, path6;
						Image p1, p2, p3, p4, p5, p6;
						b.winnings = 0.00;
						b.currentBet = Double.valueOf(textfieldHowMuchToBet.getText());
						mTwo.setText("Total Money: $" + df2.format(b.totalMoney));
						mThree.setText("Total Winnings: $" + df2.format(b.totalWinnings));
						
						if(r1.isSelected())
						{
							b.betted = "Player";
						}
						else if(r2.isSelected())
						{
							b.betted = "Banker";
						}
						else if(r3.isSelected())
						{
							b.betted = "Draw";
						}
						
						//clean borderPane screen sections
						borderPane.setLeft(null);
						borderPane.setRight(null);
						borderPane.setBottom(null);
						borderPane.setCenter(null);
						
						b.theDealer.generateDeck();
						b.theDealer.shuffleDeck();

						b.playerHand = b.theDealer.dealHand();
						b.bankerHand = b.theDealer.dealHand();
						
						path1 = b.playerHand.get(0).suite + Integer.toString(b.playerHand.get(0).value) + ".png";
						path2 = b.playerHand.get(1).suite + Integer.toString(b.playerHand.get(1).value) + ".png";
						path4 = b.bankerHand.get(0).suite + Integer.toString(b.bankerHand.get(0).value) + ".png";
						path5 = b.bankerHand.get(1).suite + Integer.toString(b.bankerHand.get(1).value) + ".png";
						
						p1 = new Image(path1);
						p2 = new Image(path2);
						p4 = new Image(path4);
						p5 = new Image(path5);
						
						ImageView img1 = new ImageView(p1);
						ImageView img2 = new ImageView(p2);
						ImageView img4 = new ImageView(p4);
						ImageView img5 = new ImageView(p5);
						
						img1.setImage(p1);
						img2.setImage(p2);
						img4.setImage(p4);
						img5.setImage(p5);
						
						ImageView img3 = new ImageView();
						ImageView img6 = new ImageView();
						
						pTotal.setText("Hand Total: " + String.valueOf(b.gameLogic.handTotal(b.playerHand)));
						dTotal.setText("Hand Total: " + String.valueOf(b.gameLogic.handTotal(b.bankerHand)));
						
						if(b.gameLogic.evaluatePlayerDraw(b.playerHand))
						{
							Card tmp = b.theDealer.drawOne();
							b.playerHand.add(new Card(tmp.suite, tmp.value));
							path3 = b.playerHand.get(2).suite + Integer.toString(b.playerHand.get(2).value) + ".png";
							p3 = new Image(path3);
							img3.setImage(p3);
						}
						
						if(b.playerHand.size() == 3)
						{
							if(b.gameLogic.evaluateBankerDraw(b.bankerHand, b.playerHand.get(2)))
							{
								Card tmp = b.theDealer.drawOne();
								b.bankerHand.add(new Card(tmp.suite, tmp.value));
								path6 = b.bankerHand.get(2).suite + Integer.toString(b.bankerHand.get(2).value) + ".png";
								p6 = new Image(path6);
								img6.setImage(p6);
							}
						}
						else
						{
							if(b.gameLogic.evaluateBankerDraw(b.bankerHand, null))
							{
								Card tmp = b.theDealer.drawOne();
								b.bankerHand.add(new Card(tmp.suite, tmp.value));
								path6 = b.bankerHand.get(2).suite + Integer.toString(b.bankerHand.get(2).value) + ".png";
								p6 = new Image(path6);
								img6.setImage(p6);
							}
						}
						
						//clear the BorderPane window sections and display only the playerSide and dealerSide
						playerSide.getChildren().clear();
						dealerSide.getChildren().clear();
						centerSide.getChildren().clear();
						
						playerSide.getChildren().addAll(playerAreaName, img1, img2, pTotal);
						dealerSide.getChildren().addAll(dealerAreaName, img4, img5, dTotal);

						borderPane.setLeft(playerSide);
						borderPane.setRight(dealerSide);
						
						//after display of both sides, execute the below after 2 seconds, which then shows who won and money earn
						pause.setOnFinished(event ->
						{
							pTotal.setText("Hand Total: " + String.valueOf(b.gameLogic.handTotal(b.playerHand)));
							dTotal.setText("Hand Total: " + String.valueOf(b.gameLogic.handTotal(b.bankerHand)));
							b.winner = b.gameLogic.whoWon(b.playerHand, b.bankerHand);
							b.winnings = b.evaluateWinnings();
							b.totalWinnings = b.totalWinnings + b.winnings;
							b.totalMoney = b.totalMoney + b.winnings;
							mTwo.setText("Total Money: $" + df2.format(b.totalMoney));
							mThree.setText("Total Winnings: $" + df2.format(b.totalWinnings));
							
							playerSide.getChildren().clear();
							dealerSide.getChildren().clear();
							centerSide.getChildren().clear();
							
							centerSide.getChildren().addAll(buttonNextRound, about);
							about.setText((b.winner + " Won!") + " You bet " + b.betted);
							dealerSide.getChildren().addAll(dealerAreaName, img4, img5, img6, dTotal);
							playerSide.getChildren().addAll(playerAreaName, img1, img2, img3, pTotal);						
							borderPane.setCenter(centerSide);
						});
						pause.play();
					}
					else if(Double.valueOf(textfieldHowMuchToBet.getText()) > b.totalMoney)
					{
						borderPane.setCenter(textfieldHowMuchToBet);
						error.setText("Error - Insuffiecient Funds");
						borderPane.setBottom(error);
					}
					else
					{
						borderPane.setCenter(textfieldHowMuchToBet);
						error.setText("Error - Why you trying to bet with imaginary money?");
						borderPane.setBottom(error);
					}
				}
			});
			
//Goes to 2nd scene again
			buttonNextRound.setOnAction(new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent action)
				{
					playerSide.getChildren().clear();
					dealerSide.getChildren().clear();
					centerSide.getChildren().clear();
					borderPane.setLeft(null);
					borderPane.setRight(null);
					borderPane.setBottom(null);
					borderPane.setCenter(null);
					
					borderPane.setCenter(textfieldHowMuchToBet);
					BorderPane.setAlignment(buttonConfirmBet, Pos.CENTER);
					buttonPlay.setText("PLAY");
					borderPane.setRight(buttonConfirmBet);
					borderPane.setLeft(paneForRadioButtons);
					BorderPane.setAlignment(paneForRadioButtons, Pos.CENTER);
					mTwo.setText("Total Money: $" + df2.format(b.totalMoney));
					mThree.setText("Total Winnings: $" + df2.format(b.totalWinnings));
				}
			});
			
			Scene scene = new Scene(borderPane,600,600); //create 600 by 600 GUI window
			
			primaryStage.setResizable(false); //the user cannot resize the application window
			primaryStage.setScene(scene);
			primaryStage.show();
		}
}
