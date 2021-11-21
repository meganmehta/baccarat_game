import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;

    private Consumer<Serializable> callback;

    Server(Consumer<Serializable> call){

        callback = call;
        server = new TheServer();
        server.start();
    }


    public class TheServer extends Thread{

        public void run() {

            try(ServerSocket mysocket = new ServerSocket(5555);){
                System.out.println("Server is waiting for a client!");


                while(true) {

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    callback.accept("New client has connected to server: " + "Client #" + count);
                    clients.add(c);
                    callback.accept("Number of clients connected to server: " + clients.size()); //how many clients are connected to the server.
                    c.start();

                    count++;

                }
            }//end of try
            catch(Exception e) {
                callback.accept("Server socket did not launch");
            }
        }//end of while

        /*public void send(BaccaratInfo gameStuff) {

            try {
                //out.writeObject(gameStuff);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }


    class ClientThread extends Thread{


        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;
        BaccaratGame game;


        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }


        public void run(){

            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
                game = new BaccaratGame(); //create new instance of BaccaratGame when new thread is created

            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

            while(true) {
                try {
                    BaccaratInfo gameInfo = (BaccaratInfo) in.readObject();
                    String amount = String.valueOf(gameInfo.betAmount);
                    //outputs message with client bet info
                    callback.accept("Client #" + count + " bet $" +
                            amount + " on " + gameInfo.userBetChoice);
                    //plays game on backend
                    game.setBetAmount(gameInfo.betAmount);
                    game.setBetOn(gameInfo.userBetChoice);
                    double winnings = game.evaluateWinnings();
                    gameInfo.playerHand = game.playerHand;
                    gameInfo.bankerHand = game.bankerHand;
                    gameInfo.extraPlayerCard = game.playerECard;
                    gameInfo.extraBankerCard = game.bankerECard;
                    out.writeObject(gameInfo); //sending to client
                    callback.accept("Client #" + count + "won" + winnings);

                }
                catch(Exception e) {
                    callback.accept("Client #"+ count +" has left the server!");
                    clients.remove(this);
                    callback.accept("Number of clients connected to server: " + clients.size()); //how many clients are connected to the server.
                    break;
                }
            }
        }//end of run


    }//end of client thread
}






