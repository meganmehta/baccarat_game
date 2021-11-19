import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread{


    Socket socketClient;

    ObjectOutputStream out;
    ObjectInputStream in;

    //BaccaratGmae clientGame;

    private Consumer<Serializable> callback;

    Client(Consumer<Serializable> call){

        callback = call;
    }

    public void run() {

        try {
            socketClient= new Socket("127.0.0.1",5555);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
            //create new game for every client thread?
            //BaccaratGame clientGame = new BaccaratGame();
        }
        catch(Exception e) {}

        while(true) {

            try {
                //would this be actual data variables in the BaccaratInfo class or?
                BaccaratInfo gameInfo = (BaccaratInfo)in.readObject();
                callback.accept(gameInfo);
            }
            catch(Exception e) {}
        }

    }

    public void send(String data) {

        try {
            out.writeObject(data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
