package model;

import model.builder.webServer.WebServerBuilder;
import util.Handler;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    // server initialization
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private DataInputStream in = null;
    private int port;

    public void setPort(int port) {
        this.port = port;
    }

    public WebServer() { }

    public void run() {

        try {

            System.out.println("Starting server...");
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            System.out.println("Waiting for a client...");
            socket = serverSocket.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";

            Handler handler = null;

            while (true) {
                try {

                    handler = checkHandlerInitialization(handler);
                    line = in.readUTF();

                    String result = handler.handleMessage(line);

                    if (result.equals("close")) break;

                } catch(Exception e) {

                    System.out.println("Error occurred while reading message: " + e);

                }
            }

            System.out.println("Closing connection");
            socket.close();
            in.close();

        } catch (Exception e) {

            System.out.println("Error occurred while starting server: " + e);

        }

    }

    private Handler checkHandlerInitialization(Handler handler) throws IOException {
        if (handler == null) {
            return new Handler(
                    new DataOutputStream(socket.getOutputStream())
            );
        }
        return handler;
    }

}
