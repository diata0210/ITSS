package javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CalculatorController {
    @FXML
    private AnchorPane anchorPane;

    @FXML private Button add_button;

    @FXML private Button sub_button;

    @FXML private Button mul_button;

    @FXML private Button div_button;

    @FXML private TextField first_number;

    @FXML private TextField second_number;

    @FXML private Label cal_value;

    @FXML
    void addAction(ActionEvent event) {
		int addResult = Integer.parseInt(first_number.getText()) + Integer.parseInt(second_number.getText());
		cal_value.setText(Integer.toString(addResult));
	}

    @FXML
	void subAction(ActionEvent event) {
		int subResult = Integer.parseInt(first_number.getText()) - Integer.parseInt(first_number.getText());
		cal_value.setText(Integer.toString(subResult));
	}
    @FXML
	void clearAction(ActionEvent event) {
		first_number.setText("");
		second_number.setText("");
		cal_value.setText("");
	}

    @FXML
	void divAction(ActionEvent event) {
		try {
			int divResult = Integer.parseInt(first_number.getText()) / Integer.parseInt(second_number.getText());
			cal_value.setText(Integer.toString(divResult));
		} catch (Exception e) {
			cal_value.setText("Error: div by 0");
		}
	}

	@FXML
	void mulAction(ActionEvent event) {
		int mulResult = Integer.parseInt(first_number.getText()) * Integer.parseInt(second_number.getText());
		cal_value.setText(Integer.toString(mulResult));
	}

}
