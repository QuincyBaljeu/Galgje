import server.GalgjeServer;

public class Main {
    public static void main(String[] args) {
        int port = 10000;
        GalgjeServer server = new GalgjeServer(port);
        server.start();
    }
}
