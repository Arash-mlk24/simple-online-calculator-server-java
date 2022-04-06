package controller;

import java.util.ArrayList;

public class MainController {

    public String calculate(ArrayList<String> data) {

        System.out.println("Calculating with arguments: " + data.get(0) + " " + data.get(1) + " " + data.get(2));

        double firstNumber;
        double secondNumber;
        String sign;

        try {

            firstNumber = Double.parseDouble(data.get(0));
            secondNumber = Double.parseDouble(data.get(2));
            sign = data.get(1);

            switch (sign) {
                case "+":
                    return Double.toString(firstNumber + secondNumber);
                case "-":
                    return Double.toString(firstNumber - secondNumber);
                case "/":
                    return Double.toString(firstNumber / secondNumber);
                case "*":
                    return Double.toString(firstNumber * secondNumber);
                case "^":
                    return Double.toString(Math.pow(firstNumber, secondNumber));
                default:
                    return "ERROR";
            }

        } catch (Exception e) {
            return "ERROR";
        }

    }

}
