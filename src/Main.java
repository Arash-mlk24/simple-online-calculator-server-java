import model.WebServer;
import model.builder.webServer.WebServerBuilder;
import util.Constants;

public class Main {

    public static void main(String[] args) {

        System.out.println("Creating web server...");

        WebServer webServer =
                WebServerBuilder
                        .builder()
                        .withPort(Constants.PORT)
                        .build();

        System.out.println("Web server created!");

        webServer.run();

    }

}
