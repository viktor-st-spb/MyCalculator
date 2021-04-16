package org.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

       /******** Helper Variables ********/
    //Whether the last presses button is numeric
    var lastNumeric : Boolean = false
    //Whether the last presses button is decimal point
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**** Digit buttons onClickListener ****/
    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    /**** CLR button onClickListener ****/
    fun onClear (view: View){
        tvInput.text = ""
        //Restore initial state of helper variables
        lastNumeric = false
        lastDot = false
    }


    /**** Decimal point button onClickListener ****/
    fun onDecimalPoint(view: View) {
        //Check whether the last pressed button was numeric
        if (lastNumeric && !lastDot) {
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    /**** Operator buttons onClickListener ****/
    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    /**** Check whether pressed button is operator one ****/
    private fun isOperatorAdded(value: String) : Boolean {
        //Check whether the string starts with 'minus'
        return if (value.startsWith("-")){
            false
        } else {
            //check whether the string contains / or * or + or - sign
            value.contains("/") || value.contains("*") ||
                    value.contains("+") || value.contains("-")
        }
    }
}