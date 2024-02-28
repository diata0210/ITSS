package javafx;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CalculatorController {
    private CalculatorModel theModel = new CalculatorModel();

    @FXML
    private TextField firstNumber;

    @FXML
    private TextField secondNumber;

    @FXML
    private Text operatorLabel;

    @FXML
    private TextField calcSolution;

    @FXML
    void calculateButtonPress(ActionEvent event) {
        int first = 0, second = 0;
        try {
            first = Integer.parseInt(firstNumber.getText());
            second = Integer.parseInt(secondNumber.getText());
            String operator = operatorLabel.getText();
            if (operator.equals("+")) {
                theModel.addTwoNumbers(first, second);
            } else if (operator.equals("-")) {
                theModel.subtractTwoNumbers(first, second);
            } else if (operator.equals("x")) {
                theModel.multiplyTwoNumbers(first, second);
            } else if (operator.equals("/")) {
                theModel.divideTwoNumbers(first, second);
            }
            calcSolution.setText(String.valueOf(theModel.getCalculationValue()));
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "You Need to Enter 2 Integers");
        }
    }

    @FXML
    void addButtonPressed(ActionEvent event) {
        operatorLabel.setText("+");
    }

    @FXML
    void subtractButtonPressed(ActionEvent event) {
        operatorLabel.setText("-");
    }

    @FXML
    void multiplyButtonPressed(ActionEvent event) {
        operatorLabel.setText("x");
    }

    @FXML
    void divideButtonPressed(ActionEvent event) {
        operatorLabel.setText("/");
    }

}
