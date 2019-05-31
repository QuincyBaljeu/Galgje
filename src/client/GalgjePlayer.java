package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GalgjePlayer{

    private Socket socket;
    private String host;
    private int port;
    private String name;

    public GalgjePlayer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect(){
        try{
            this.socket = new Socket(this.host, this.port);
            System.out.println("Player connected");

        } catch (IOException e){
            System.out.println("Could not connect with server");
        }
    }

    public void guessLetter(String guessedLetter){
        try {
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            out.writeUTF(guessedLetter);
        } catch (IOException e){
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
