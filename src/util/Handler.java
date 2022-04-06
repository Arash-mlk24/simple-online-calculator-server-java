package util;

import controller.MainController;

import java.io.DataOutputStream;
import java.util.ArrayList;

public class Handler {

    private DataOutputStream output = null;
    private ArrayList<String> data = new ArrayList<>();
    private MainController controller = new MainController();
    private boolean started = false;

    public Handler(DataOutputStream output) {
        this.output = output;
    }

    public String handleMessage(String message) {
        System.out.println("Received message: " + message);

        try {

            if (message.equals("exit")) {
                output.writeUTF("exit");
                return "close";
            }

        } catch (Exception e) {

            System.out.println("Error occurred while sending \"exit\" to client!");

        }

        try {

            if (!started) {
                if (message.equals("start")) {
                    started = true;
                    output.writeUTF("Calculator started!");
                } else {
                    output.writeUTF("You can only send \"start\" or \"exit\"");
                }
                return "OK";
            }

        } catch (Exception e) {
            System.out.println("error occurred while checking calculator status: " + e);
        }

        try {
            if (!validateMessage(message)) {
                started = false;
                data.clear();
                output.writeUTF("Invalid message, START AGAIN!");
            } else {
                if (data.size() != 3) output.writeUTF("VALID!");
            }
        } catch (Exception e) {
            System.out.println("Error in sending message to CLIENT: " + e);
        }

        if (data.size() == 3) {
            String result = controller.calculate(data);
            try {

                if (result.equals("ERROR")) {
                    System.out.println("Error occurred while calculating stuffs!");
                    started = false;
                    data.clear();
                    output.writeUTF("INTERNAL ERROR occurred!");
                } else {
                    System.out.println("calculated successfully: " + result);
                    started = false;
                    data.clear();
                    output.writeUTF("Your result is: \"" + result + "\", start again to calculate!");
                }

            } catch (Exception e) {
                System.out.println("Error in sending message to CLIENT: " + e);
            }
        }

        return "OK";
    }

    private boolean validateMessage(String message) {
        if (data.isEmpty() || data.size() == 2) {
            try {
                double number = Double.parseDouble(message);
                data.add(Double.toString(number));
                System.out.println("VALID!");
                return true;
            } catch (Exception e) {
                System.out.println("NOT VALID!");
                return false;
            }
        } else {
            if (Constants.SYMBOLS.contains(message)) {
                System.out.println("VALID!");
                data.add(message);
                return true;
            } else {
                System.out.println("NOT VALID!");
                return false;
            }
        }
    }

}
