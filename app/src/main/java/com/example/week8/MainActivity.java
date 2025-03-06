package com.example.week8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText inputFirstNumber, inputSecondNumber;
    private TextView textOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputFirstNumber= findViewById(R.id.inputFirstNumber);
        inputSecondNumber= findViewById(R.id.inputSecondNumber);
        textOutput= findViewById(R.id.textResult);

        Button btnSum= findViewById(R.id.buttonPlus);
        Button btnSubtraction= findViewById(R.id.buttonMinus);
        Button btnMultiplication= findViewById(R.id.buttonMultiply);
        Button btnDivision= findViewById(R.id.buttonDivide);

        btnSum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                performOperation("+");
            }
        });

        btnSubtraction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                performOperation("-");
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                performOperation("*");
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                performOperation("/");
            }
        });
    }

    private void performOperation(String operator){
        String num1Str= inputFirstNumber.getText().toString();
        String num2Str= inputSecondNumber.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()){
            textOutput.setText("Please enter both numbers");
            return;
        }
//This takes a number written as text (String) and turns it into a real number (double)
        double num1= Double.parseDouble(num1Str);
        double num2= Double.parseDouble(num2Str);
        double result= 0;

        switch (operator){
            case "+":
                result= num1 + num2;
                break;
            case "-":
                result= num1 - num2;
                break;
            case "*":
                result= num1 * num2;
                break;
            case "/":
                if (num2==0){
                    textOutput.setText("Cannot divide by zero");
                    return;
                }
                result= num1/num2;
                break;
        }

        if (result%1==0){
            textOutput.setText(String.valueOf((int)result));
        } else if (result *10%1==0){
            textOutput.setText(String.format("%.1f", result));
        }else {
            textOutput.setText(String.format("%.2f", result));
        }
    }
}