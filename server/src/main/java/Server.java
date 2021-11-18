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
    }


    class ClientThread extends Thread{


        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }

        //updateClients goes on all client threads?
        public void updateClients(String message) {
            for(int i = 0; i < clients.size(); i++) {
                ClientThread t = clients.get(i);
                try {
                    t.out.writeObject(message);
                }
                catch(Exception e) {}
            }
        }

        public void run(){

            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

            updateClients("New client on server: Client #"+ count); //if a new client joins the server.

            while(true) {
                try {
                    //include game related updates here?
                    //list has to inclue:
                    //- The results of each game played by any client.
                    //- how much the a client bet on each game
                    //- how much a client won or lost on each game
                    //- if a client drops off the server.
                    //- is the client playing another hand.
                    BaccaratInfo gameInfo = (BaccaratInfo) in.readObject();
                    callback.accept("Client #" + count + " bet $" +
                            gameInfo.betAmount + " on " + gameInfo.userBetChoice);
                    //updateClients("client #"+count+" said: "+data);

                }
                catch(Exception e) {
                    //callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
                    updateClients("Client #"+count+" has left the server!");
                    clients.remove(this);
                    callback.accept("Number of clients connected to server: " + clients.size()); //how many clients are connected to the server.
                    break;
                }
            }
        }//end of run


    }//end of client thread
}






