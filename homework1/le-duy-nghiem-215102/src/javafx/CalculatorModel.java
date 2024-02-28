package javafx;



public class CalculatorModel {
    private int calValue = 0;
    public void addTwoNumbers(int firstNumber, int secondNumber){
        calValue = firstNumber + secondNumber;
    }

    public int getCalculationValue(){
        return calValue;
    }
    public void subTwoNumbers(int firstNumber,int secondNumber){
        calValue = firstNumber - secondNumber;
    }
    public void mulTwoNumbers(int firstNumber,int secondNumber){
        calValue = firstNumber * secondNumber;
    }
    public void divTwoNumbers(int firstNumber,int secondNumber){
        calValue = firstNumber / secondNumber;
    }
}
