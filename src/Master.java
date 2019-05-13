import client.GalgjeMaster;

public class Master {

    public static void main(String[] args) {
        GalgjeMaster master = new GalgjeMaster("localhost", 10000);
        master.connect();
    }
}
