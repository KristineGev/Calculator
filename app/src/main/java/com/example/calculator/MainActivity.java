package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button numberButton, operationButton;
    TextView resultDisplay, operationDisplay;
    String numberField = "";
    String operations = "";
    String operator = "";
    String lastOperator = "";
    boolean isPercentClicked = false;

    Double firstNumber = 0.0;
    Double secondNumber = 0.0;
    Double result = 0.0;

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
        numberField += numberButton.getText();
        resultDisplay.setText(numberField);
    }

    public void onOperationClick(View v) {
        if (numberField != "") {
            firstNumber = Double.parseDouble(numberField);

        }
        operator = ((Button) v).getText().toString();
        operations += firstNumber + operator;
        operationDisplay.setText(operations);
        numberField = "";
        calculate(v);
        lastOperator += operator;
    }

    public void calculate(View v) {
        if (numberField == "") {
            if (result == 0) {
                secondNumber = result;
                result = firstNumber;
            } else {
                secondNumber = firstNumber;
                firstNumber = result;
            }

        } else {
            secondNumber = Double.parseDouble(numberField);
            operations += secondNumber;
        }
        resultDisplay.setText(numberField);
        numberField = "";

        if (lastOperator.length() > 0) {
            switch (lastOperator.substring(lastOperator.length() - 1)) {
                case "+":
                    result += secondNumber;
                    break;
                case "-":
                    result -= secondNumber;
                    break;
                case "*":
                    result *= secondNumber;
                    break;
                case "/":
                    result /= secondNumber;
                    break;
                default:
                    break;
            }
        }

        numberField = result.toString();
        operationDisplay.setText(operations);
        numberField = "";
        resultDisplay.setText(String.valueOf(result));
    }

    public void onPercentClick(View v) {

    }


    public void onEqualClick(View v) {
        calculate(v);
        operations = "";
        numberField = "";
        operationDisplay.setText("");
        resultDisplay.setText(String.valueOf(result));
        firstNumber = result;
        result = 0.00;
        lastOperator = "";
        isPercentClicked = false;

    }

    public void onSignButtonClick(View v) {
        Double res = Double.parseDouble(numberField) * -1;
        resultDisplay.setText(String.valueOf(res));
        numberField = String.valueOf(res);
    }

    public void onDeleteClick(View v) {
        String display = resultDisplay.getText().toString();
        if (display.length() > 0) {
            numberField = display.substring(0, display.length() - 1);
            resultDisplay.setText(numberField);
        } else
            resultDisplay.setText("0");
    }

    public void onClear(View view) {
        operationButton = (Button) view;
        numberField = "";
        operations = "";
        resultDisplay.setText("0");
        operationDisplay.setText("");
        result = 0.00;
        secondNumber = 0.00;
        lastOperator = "";
    }
}


