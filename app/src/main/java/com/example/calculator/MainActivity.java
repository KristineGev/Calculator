package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button numberButton, operationButton;
    TextView resultDisplay, operationDisplay;
    String resultString = "";
    String operationString = "";
    String operator = "";
    String lastOperator = "";
    Double firstNumber = 0.0;
    Double secondNumber = 0.0;
    Double result = 0.0;
    boolean isPercentClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        resultDisplay.setText("0");
    }

    public void init() {
        resultDisplay = findViewById(R.id.display_result);
        operationDisplay = findViewById(R.id.dislpay_number);
    }

    public void onNumberButtonClick(View v) {
        numberButton = (Button) v;
        resultString += numberButton.getText();
        resultDisplay.setText(resultString);
    }

    public void onOperationClick(View v) {
        if (resultString.length() > 0) {
            if (resultString.endsWith("%")) {
                firstNumber = Double.parseDouble(resultString.substring(0, resultString.length() - 1));
            } else {
                firstNumber = Double.parseDouble(resultString);
            }
        }

        operator = ((Button) v).getText().toString();
        if (firstNumber % 1 == 0) {
            if (resultString.endsWith("%")) {
                operationString += firstNumber.intValue() + "%" + operator;
            } else {
                operationString += firstNumber.intValue() + operator;
            }
        } else {
            operationString += resultString + operator;

        }

        operationDisplay.setText(operationString);
        resultString = "";
        calculate(v);
        lastOperator = operator;
    }

    public void calculate(View v) {
        if (resultString.length() == 0) {
            if (result == 0) {
                secondNumber = result;
                result = firstNumber;
            } else {
                secondNumber = firstNumber;
                firstNumber = result;
            }

        } else {

            if (resultString.endsWith("%")) {
                secondNumber = Double.parseDouble(resultString.substring(0, resultString.length() - 1));
            } else {
                secondNumber = Double.parseDouble(resultString);
            }

            operationString += resultString;

        }
        resultDisplay.setText(resultString);
        resultString = "";

        if (lastOperator.length() > 0) {
            switch (lastOperator) {
                case "+":
                    if (!isPercentClicked) {
                        result += secondNumber;
                    }
                    if (isPercentClicked) {
                        result += result * secondNumber / 100;
                        isPercentClicked = false;
                    }
                    break;
                case "-":
                    if (!isPercentClicked)
                        result -= secondNumber;
                    if (isPercentClicked) {
                        result -= result * secondNumber / 100;
                        isPercentClicked = false;
                    }
                    break;
                case "*":
                    if (!isPercentClicked) {
                        result *= secondNumber;
                    }
                    if (isPercentClicked) {
                        result *= secondNumber / 100;
                        isPercentClicked = false;
                    }
                    break;
                case "/":
                    if (!isPercentClicked) {
                        result /= secondNumber;
                    }
                    if (isPercentClicked) {
                        result = result / secondNumber * 100;
                        isPercentClicked = false;
                    }
                    break;
                default:

                    break;
            }
        }
        if (result % 1 == 0) {
            resultString = String.valueOf(result.intValue());
            resultDisplay.setText(resultString);
        } else {
            resultString = result.toString();
            resultDisplay.setText(resultString);
        }
        operationDisplay.setText(operationString);
        resultString = "";
    }


    public void onEqualClick(View v) {
        calculate(v);
        operationString = "";
        resultString = "";
        operationDisplay.setText("");
        if (result % 1 == 0) {
            resultDisplay.setText(String.valueOf(result.intValue()));
        } else {
            resultDisplay.setText(String.valueOf(result));
        }
        firstNumber = result;
        result = 0.00;
        lastOperator = "";
        isPercentClicked = false;

    }

    public void onSignButtonClick(View v) {

        Double res = Double.parseDouble(resultString) * -1;

        if (res % 1 == 0) {
            resultString = String.valueOf(res.intValue());
        } else {
            resultString = String.valueOf(res);
        }
        resultDisplay.setText(resultString);
    }

    public void onPercentClick(View v) {
        isPercentClicked = true;
        double num = Double.parseDouble(resultString);
        resultDisplay.setText(String.valueOf(num / 100));
        resultString += "%";
    }

    public void onDeleteClick(View v) {
        if (resultString.length() > 0) {
            resultString = resultString.substring(0, resultString.length() - 1);
            resultDisplay.setText(resultString);
        } else
            resultDisplay.setText("0");
    }

    public void onClear(View view) {
        operationButton = (Button) view;
        resultString = "";
        operationString = "";
        resultDisplay.setText("0");
        operationDisplay.setText("");
        result = 0.00;
        secondNumber = 0.00;
        lastOperator = "";
        isPercentClicked = false;
    }
}


