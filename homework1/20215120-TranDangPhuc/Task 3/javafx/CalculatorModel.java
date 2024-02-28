package javafx;
/**
 * CaculatorModel
 */
public class CalculatorModel {

  private int calculationValue;

  public void addTwoNumbers(int firstNumber, int secondNumber) {
    calculationValue = firstNumber + secondNumber;
  }

  public void subTwoNumbers(int firstNumber, int secondNumber) {
    calculationValue = firstNumber - secondNumber;
  }

  public void mulTwoNumbers(int firstNumber, int secondNumber) {
    calculationValue = firstNumber * secondNumber;
  }

  public void divTwoNumbers(int firstNumber, int secondNumber) {
    calculationValue = firstNumber / secondNumber;
  }

  public void modTwoNumbers(int firstNumber, int secondNumber) {
    calculationValue = firstNumber % secondNumber;
  }

  public int getCalculationValue() {
    return calculationValue;
  }
}