package swing;

public class CalculatorModel {
    
    private int calculationValue;
    
    public void addTwoNumbers(int firstNumber, int secondNumber){
        calculationValue = firstNumber + secondNumber;
    }
    
    public void subtractTwoNumbers(int firstNumber, int secondNumber){
        calculationValue = firstNumber - secondNumber;
    }
    
    public void multiplyTwoNumbers(int firstNumber, int secondNumber){
        calculationValue = firstNumber * secondNumber;
    }
    
    public void divideTwoNumbers(int firstNumber, int secondNumber){
        if (secondNumber != 0) {
            calculationValue = firstNumber / secondNumber;
        } else {
            calculationValue = 0;
        }
    }
    
    public int getCalculationValue(){
        return calculationValue;
    }
    
}
