package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;


public class MainActivity extends AppCompatActivity {
    /*We create a boolean array, if an answer was correct it will be marked as true inside the array, otherways false*/
 boolean[] answers = new boolean[100];
    /*Initianing the score*/
 int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Create a spinner and assign it an adapter that is linked to the array inside the strings.xml*/

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        /*Create an ArrayAdapter using the string array and a default spinner layout*/
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.animal_array, android.R.layout.simple_spinner_item);
        /*Specify the layout to use when the list of choices appears*/
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /*Apply the adapter to the spinner*/
        spinner.setAdapter(adapter);
    }

    /*When the submit button is pressed, we check all the answers, create a score and display the correct answers in case the user has any mistakes*/
    public void submitAnswers(View view)
    {
        /*Assign views to variables using casting and finding by view id*/
        EditText questionOneEditText = (EditText) findViewById(R.id.number_edit_text);
        RadioButton thirdRadio = (RadioButton) findViewById(R.id.radio_option_three);
        CheckBox firstCheckBox = (CheckBox) findViewById(R.id.checkbox_one);
        CheckBox secondCheckBox = (CheckBox) findViewById(R.id.checkbox_two);
        CheckBox thirdCheckBox = (CheckBox) findViewById(R.id.checkbox_three);
        SeekBar tempSeekBar = (SeekBar) findViewById(R.id.temeperature_seek_bar);
        EditText secondEditText = (EditText) findViewById(R.id.second_edit_text);
        RadioButton fractureRadio = (RadioButton) findViewById(R.id.radio_option_two_fracture);
        CheckBox cCheckBoxOne = (CheckBox) findViewById(R.id.checkbox_c_one);
        CheckBox cCheckBoxTwo = (CheckBox) findViewById(R.id.checkbox_c_two);
        CheckBox cCheckBoxThree = (CheckBox) findViewById(R.id.checkbox_c_three);
        TextView correctAnswers = (TextView) findViewById(R.id.correct_answers_text);


        String message = "";
       /* Create a string that gets the value from the editText (question 1) to compare it with the correct answers*/
        String number = questionOneEditText.getText().toString();

        /*If the number inserted by the user is correct we set the value of answers[1] to true*/
        if(number.equals("911") || number.equals("112") || number.equals("999"))
            answers[1] = true;

        /*If the user checked the correct radio button we set the answer as correct*/
        if(thirdRadio.isChecked()) {
            answers[2] = true;
        }

        /*Here we check if the user ONLY checked the correct check boxes*/
        if(!firstCheckBox.isChecked() && secondCheckBox.isChecked() && thirdCheckBox.isChecked())
        {
            answers[3] = true;
        }

        /*With this if statement, we check wether the user moved the bar to a correct number, the bar is between 36 and 38, the middle value being 37 so all the values above the middle value (20) will be marked as correct*/
        if(tempSeekBar.getProgress() > 20)
        {
            answers[4] = true;
        }

        /*If the user got the right value in the spinner we set the answer as correct*/
        if(getSpinnerText().equals(getString(R.string.spinnerCheck)))
        {
            answers[5] = true;
        }

        /*If the word inserted by the user is correct we set the value of answers[5] to true*/
        if(secondEditText.getText().toString().equalsIgnoreCase("fracture"))
        {
            answers[6] = true;
        }

        /*If the user checked the correct radio button we set the answer as correct*/
        if(fractureRadio.isChecked())
        {
            answers[7] = true;
        }

        /*Here we check if the user ONLY checked the correct check boxes*/
        if(!cCheckBoxOne.isChecked() && cCheckBoxTwo.isChecked() && cCheckBoxThree.isChecked())
        {
            answers[8] = true;
        }

        /*Using this repetitive structure we check each answer from 1 to 8, if an answer is correct we give one point*/
        /*If an answer is wrong we create update the final message with the correct answer and an explanation*/
        /*After that, we get to 9, where we extend the summary beginning by saying that the user got either all the answers correct or he missed a few*/
        for(int i = 1; i <= 9; i++)
        {
            if(!answers[i])
            {
               message = buildSummary(i, message);
            }
            else score++;

        }
        /*Here we assing the final summary text to a TextView*/
        correctAnswers.setText(message);
    }

   /* Using this method we build the final summary*/
    private String buildSummary(int i, String message)
    { /*Using this switch we verify what question we need to explain and edit the final summary string accordingly*/
      switch (i)
      {
          case 1:
              message += getString(R.string.answer_one) + "\n";
              break;
          case 2:
              message += getString(R.string.answer_two) + "\n";
              break;
          case 3:
              message += getString(R.string.answer_three) + "\n";
              break;
          case 4:
              message += getString(R.string.answer_four) + "\n";
              break;
          case 5:
              message += getString(R.string.answer_five) + "\n";
              break;
          case 6:
              message += getString(R.string.answer_six) + "\n";
              break;
          case 7:
              message += getString(R.string.answer_seven) + "\n";
              break;
          case 8:
              message += getString(R.string.answer_eight) + "\n";
              break;
          case 9: {
              if (score == 8)
                  message = getString(R.string.allDone);
              else
                  message = getString(R.string.someMistakes) + "\n" + message;
              break;
          }


      }


        String totalScore = getString(R.string.scoreString) + " " + score + " " + getString(R.string.scoreStringS);
        Toast.makeText(this, totalScore , Toast.LENGTH_SHORT).show();
      return  message;
    }

    /*With this method we get the text inside the spinner*/
    private String getSpinnerText()
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        return spinner.getSelectedItem().toString();
    }

}

