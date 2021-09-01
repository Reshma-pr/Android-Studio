package com.reshadev.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //Class Variables are also called Fields
    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();


        setupButtonClickListener();


    }


    private void findViews() {
        resultText = findViewById(R.id.text_result);

        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);

        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);


    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double calcBmi = calculateBMI();
                String ageText = age.getText().toString();
                int age = Integer.parseInt(ageText);
                if (age >= 18) {

                    displayResult(calcBmi);
                } else {
                    displayGuidance(calcBmi);
                }
            }
        });
    }


    private double calculateBMI() {
        String feetText = feet.getText().toString();
        String inchesText = inches.getText().toString();
        String weightText = weight.getText().toString();
        //Converting the number into 'Strings' into int variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        double heightInMetres = totalInches * 0.0254;
        //Formula= weight/height^2
        return weight / (heightInMetres * heightInMetres);


    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiText = myDecimalFormatter.format(bmi);
        String fullResultString;
        if (bmi < 18.5) {
            //Underweight
            fullResultString = bmiText + " - You are underweight ";
        } else if (bmi > 25) {
            //Display overweight
            fullResultString = bmiText + " - You are overweight ";
        } else {
            //healthy

            fullResultString = bmiText + " - You are healthy weight ";
        }
        resultText.setText(fullResultString);

    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiText = myDecimalFormatter.format(bmi);
        String fullResultString;
        if (maleButton.isChecked()) {
            //display boy guidance
            fullResultString = bmiText + " - As you are under 18 please consult your doctor for the healthy range for boys";
        } else if (femaleButton.isChecked()) {
            //display girl guidance
            fullResultString = bmiText + " -As you are under 18 please consult your doctor for the healthy range for girls";
        } else {
            fullResultString = bmiText + " - As you are under 18 please consult your doctor for the healthy range ";

        }
        resultText.setText(fullResultString);

    }

}
