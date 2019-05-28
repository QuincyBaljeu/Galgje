import server.GalgjeServer;

public class Main {
    public static void main(String[] args) {

        //launch master first, then master.
        int port = 10000;
        GalgjeServer server = new GalgjeServer(port);
        server.start();

        
    }
}
