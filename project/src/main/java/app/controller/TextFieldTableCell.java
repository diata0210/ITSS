package app.controller;

import app.models.WHCheckTable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class TextFieldTableCell extends TableCell<WHCheckTable, Integer> {
    private final TextField textField = new TextField();

    public TextFieldTableCell() {
        textField.setOnAction(event -> commitEdit(Integer.parseInt(textField.getText())));
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                commitEdit(Integer.parseInt(textField.getText()));
            }
        });
    }

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            textField.setText(item.toString());
            setGraphic(textField);
        }
    }

    @Override
    public void startEdit() {
        super.startEdit();
        textField.setText(getItem().toString());
        setGraphic(textField);
        textField.selectAll();
        textField.requestFocus();
    }

    @Override
    public void commitEdit(Integer newValue) {
        super.commitEdit(newValue);
        ((WHCheckTable) getTableRow().getItem()).setChecked(newValue);
        updateDependentValues();
    }

    private void updateDependentValues() {
        WHCheckTable item = getTableRow().getItem();
        if (item != null) {
            int quantity = item.getQuantity();
            int checked = item.getChecked();
            int deviated = checked - quantity;
            BigDecimal deviationValue = BigDecimal.valueOf(deviated).multiply(item.getPrice());
            item.setDeviated(deviated);
            item.setDeviationValue(deviationValue);
        }
    }
}
