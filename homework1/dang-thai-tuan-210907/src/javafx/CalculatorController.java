package javafx;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CalculatorController {

	@FXML
	private javafx.scene.layout.AnchorPane AnchorPane;
	private TextField firstNum;
	private TextField secondNum;
	private TextField result;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void addAction(ActionEvent event) {
		int addResult = Integer.parseInt(firstNum.getText()) + Integer.parseInt(secondNum.getText());
		result.setText(Integer.toString(addResult));
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
			int divResult = Integer.parseInt(firstNum.getText()) / Integer.parseInt(secondNum.getText());
			result.setText(Integer.toString(divResult));
		} catch (Exception e) {
			result.setText("Error: div by 0");
		}
	}

	@FXML
	void mulAction(ActionEvent event) {
		int mulResult = Integer.parseInt(firstNum.getText()) * Integer.parseInt(secondNum.getText());
		result.setText(Integer.toString(mulResult));
	}

	@FXML
	void perAction(ActionEvent event) {
		try {
			int perResult = Integer.parseInt(firstNum.getText()) % Integer.parseInt(secondNum.getText());
			result.setText(Integer.toString(perResult));
		} catch (Exception e) {
			result.setText("Error: div by 0");
		}
	}

	@FXML
	void subAction(ActionEvent event) {
		int subResult = Integer.parseInt(firstNum.getText()) - Integer.parseInt(secondNum.getText());
		result.setText(Integer.toString(subResult));
	}
}
