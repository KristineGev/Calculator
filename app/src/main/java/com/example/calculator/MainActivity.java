package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button numberButton, signButton, operationButton;
    TextView resultDisplay, numberDisplay;
    String resultField = "";
    String operationField = "";
    String operator = "";
    String lastOperator = "";

    Double firstNumber = 0.00;
    Double secondNumber = 0.00;
    Double result = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        resultDisplay = findViewById(R.id.display_result);
        numberDisplay = findViewById(R.id.dislpay_number);
    }

    public void onNumberButtonClick(View v) {
        numberButton = (Button) v;
        resultField += numberButton.getText();
        resultDisplay.setText(resultField);
    }

    public void onOperationClick(View v) {
        if (resultField != "") {
            firstNumber = Double.parseDouble(resultField);
        }
        operator = ((Button) v).getText().toString();
        operationField += operator + firstNumber;
        numberDisplay.setText(operationField);
        resultField = "";
        calculate(v);
        lastOperator += operator;
    }

    public void calculate(View v) {
        if (resultField == "") {
            if (result == 0) {
                secondNumber = result;
                result = firstNumber;
            }
            secondNumber = firstNumber;
            firstNumber = result;
        } else
            {
            secondNumber = Double.parseDouble(resultField);
            operationField += secondNumber;
        }
        resultDisplay.setText(resultField);
        resultField = "";

        if (lastOperator.length() > 0) {
            switch (lastOperator.substring(lastOperator.length() - 1)) {
                case "+":
                    result += secondNumber;
                    Log.v(operator, operationField + "");
                    resultField = result.toString();
                    break;
                case "-":
                    result -= secondNumber;
                    resultDisplay.setText(String.valueOf(result));
                    resultField = result.toString();
                    Log.v("Tag", "first " + firstNumber + " second " + secondNumber + " result " + result);
                    break;
                case "*":
                    result *= secondNumber;
                    resultDisplay.setText(String.valueOf(result));
                    break;
                case "/":
                    result /= secondNumber;
                    resultDisplay.setText(String.valueOf(result));
                    break;
                case "=":
                    resultField = "";
                    operationField="";
                    numberDisplay.setText("");
                    resultDisplay.setText("");
                    break;
            }
        }
        numberDisplay.setText(operationField);
        resultField = "";

        resultDisplay.setText(String.valueOf(result));
    }

    public void onPercentClick(View v) {
        if (secondNumber != 0.00) {
            secondNumber /= 100;
        } else {
            firstNumber /= 100;
        }
    }

    public void onDeleteClick(View v) {
        String display = resultDisplay.getText().toString();
        if (display.length() > 0) {
            resultField = display.substring(0, display.length() - 1);
            resultDisplay.setText(resultField);
        } else
            resultDisplay.setText("0");
    }

    public void onClear(View view) {
        operationButton = (Button) view;
        resultField = "";
        operationField = "";
        resultDisplay.setText("0");
        numberDisplay.setText("");
        result = 0.00;
        secondNumber = 0.00;
        lastOperator = "";
    }
}


