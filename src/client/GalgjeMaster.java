package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GalgjeMaster {

    private Socket socket;
    private String host;
    private int port;
    private String name;

    public GalgjeMaster(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect(){
        try{
            this.socket = new Socket(host, port);
            System.out.println("instances.Master connected");
        } catch (IOException e){
            System.out.println("Could not connect with server");
        }
    }

    public void enterPassword(String password){
        try {
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            out.writeUTF(password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readServerGuiData(){
        try {
            DataInputStream dataReader = new DataInputStream(this.socket.getInputStream());
            return dataReader.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
