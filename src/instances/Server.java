package instances;

import server.GalgjeServer;

public class Server {

    public static void main(String[] args) {
        server.GalgjeServer server = new GalgjeServer(10000);
        server.start();
    }
}
