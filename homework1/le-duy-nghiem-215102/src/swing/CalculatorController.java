package swing;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculatorController {
    private CalculatorView calView = new CalculatorView();
    private CalculatorModel calModel = new CalculatorModel();

    public CalculatorController(CalculatorView view,CalculatorModel model){
        this.calModel = model;
        this.calView = view;

        this.calView.addAction(new CalculatorActionListener());
        this.calView.subAction(new CalculatorActionListener());
        this.calView.mulAction(new CalculatorActionListener());
        this.calView.divAction(new CalculatorActionListener());
        this.calView.clearAction(new ClearActionListener());
    }

    class CalculatorActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            int firstNumber,secondNumber = 0;
            try {
                firstNumber = calView.getFirtNumber();
                secondNumber = calView.getSecondNumber();
                String command = e.getActionCommand();
                switch (command) {
                    case "+":
                        calModel.addTwoNumbers(firstNumber,secondNumber);
                        break;
                    case "-":
                        calModel.subTwoNumbers(firstNumber, secondNumber);
                        break;
                    case "x":
                        calModel.mulTwoNumbers(firstNumber, secondNumber);
                        break;
                    case ":":
                    try{
                        calModel.divTwoNumbers(firstNumber, secondNumber);
                    } catch (ArithmeticException err){
                        calView.displayErrorMessage("You can't divide by 0");
                        return;
                    }
                    break;

                }
                calView.setResult(calModel.getCalculationValue());
            } catch (NumberFormatException err){
                System.out.println(err.getStackTrace());
                calView.displayErrorMessage("You need to enter 2 Integers");
            }
           
        }
    }
    class ClearActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            calView.clear();
        }
    }
}
