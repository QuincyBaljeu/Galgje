import client.GalgjePlayer;
import javafx.application.Application;

public class Client{
    public static void main(String[] args) {
        GalgjePlayer player = new GalgjePlayer("localhost", 10000);
        player.connect();

    }

}
