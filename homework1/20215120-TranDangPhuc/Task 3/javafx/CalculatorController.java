package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculatorController {
	private CalculatorModel theModel = new CalculatorModel();
	@FXML
	private javafx.scene.layout.AnchorPane AnchorPane;
	
	@FXML
	private TextField firstNum;
	@FXML

	private TextField secondNum;
	@FXML

	private TextField result;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void addAction(ActionEvent event) {
		theModelmodel.addTwoNumbers(Integer.parseInt(firstNum.getText()), Integer.parseInt(secondNum.getText()));
		result.setText(Integer.toString(theModel.getCalculationValue()));
	}

	@FXML
	void clearAction(ActionEvent event) {
		firstNum.setText("");
		secondNum.setText("");
		result.setText("");
	}

	@FXML
	void divAction(ActionEvent event) {
		try {
			theModel.divTwoNumbers(Integer.parseInt(firstNum.getText()), Integer.parseInt(secondNum.getText()));
			result.setText(Integer.toString(theModel.getCalculationValue()));
		} catch (Exception e) {
			result.setText("Error: div by 0");
		}
	}

	@FXML
	void mulAction(ActionEvent event) {
		theModel.mulTwoNumbers(Integer.parseInt(firstNum.getText()), Integer.parseInt(secondNum.getText()));
		result.setText(Integer.toString(theModel.getCalculationValue()));
	}

	@FXML
	void modAction(ActionEvent event) {
		try {
			theModel.modTwoNumbers(Integer.parseInt(firstNum.getText()), Integer.parseInt(secondNum.getText()));
			result.setText(Integer.toString(theModel.getCalculationValue()));
		} catch (Exception e) {
			result.setText("Error: div by 0");
		}
	}

	@FXML
	void subAction(ActionEvent event) {
		theModel.subTwoNumbers(Integer.parseInt(firstNum.getText()), Integer.parseInt(secondNum.getText()));
		result.setText(Integer.toString(theModel.getCalculationValue()));
	}
}
