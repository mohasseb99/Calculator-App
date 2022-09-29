package com.example.calculator;

// azbot divide by zero
// azbot length ally zahr fe text

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.service.quickaccesswallet.SelectWalletCardRequest;
import android.support.v4.os.IResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class Calculator extends Fragment {
    Button[] btns = new Button[36];
    TextView tv;
    Stack<Double> memory;

    double operandOne = 0, result = 0;
    String operation = "";
    String viewText = "", pressed = "0";
    boolean first = true, isFloat = false, isNeg = false, firstOp = true;
    boolean firstRun = true, calc = true;
    int base = 10;

    void initalize_from_first(){
        result = 0;
        operandOne = 0;
        operation = "";
        viewText = "";
        pressed = "0";
        first = true;
        isFloat = false;
        isNeg = false;
        firstOp = true;
        firstRun = true;
        base = 10;
        tv.setText(pressed);
    }

    void convert_from_to(int fbase, int tbase){
        int fValue = Integer.valueOf(pressed, fbase);
        if(tbase == 2){
            pressed = Integer.toBinaryString(fValue);
        }
        else if(tbase == 8){
            pressed = Integer.toOctalString(fValue);
        }
        else if(tbase == 10){
            pressed = Integer.toString(fValue);
        }
        else if(tbase == 16){
            pressed = Integer.toHexString(fValue);
        }
    }


    View.OnClickListener numberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(),"numbers",Toast.LENGTH_SHORT).show();
            if(operation == "=") initalize_from_first();
            firstOp = true;

            switch (view.getId()) {
                case R.id.btn_num0:
                    if (!first) pressed += "0";
                    break;
                case R.id.btn_num1:
                    if(first) pressed = (isNeg) ? "-":"";
                    pressed += "1";
                    first = false;
                    break;
                case R.id.btn_num2:
                    if (base != 2) {
                        if(first) pressed = (isNeg) ? "-":"";
                        pressed += "2";
                        first = false;
                    }
                    break;
                case R.id.btn_num3:
                    if (base != 2) {
                        if(first) pressed = (isNeg) ? "-":"";
                        pressed += "3";
                        first = false;
                    }
                    break;
                case R.id.btn_num4:
                    if (base != 4) {
                        if(first) pressed = (isNeg) ? "-":"";
                        pressed += "4";
                        first = false;
                    }
                    break;
                case R.id.btn_num5:
                    if (base != 2) {
                        if(first) pressed = (isNeg) ? "-":"";
                        pressed += "5";
                        first = false;
                    }
                    break;
                case R.id.btn_num6:
                    if (base != 2) {
                        if(first) pressed = (isNeg) ? "-":"";
                        pressed += "6";
                        first = false;
                    }
                    break;
                case R.id.btn_num7:
                    if (base != 2) {
                        if(first) pressed = (isNeg) ? "-":"";
                        pressed += "7";
                        first = false;
                    }
                    break;
                case R.id.btn_num8:
                    if (base == 10 || base == 16) {
                        if(first) pressed = (isNeg) ? "-":"";
                        pressed += "8";
                        first = false;
                    }
                    break;
                case R.id.btn_num9:
                    if (base == 10 || base == 16) {
                        if(first) pressed = (isNeg) ? "-":"";
                        pressed += "9";
                        first = false;
                    }
                    break;
                case R.id.btn_A:
                    if (base == 16 && calc == false) {
                        if(first) pressed = "";
                        pressed += "A";
                        first = false;
                    }
                    break;
                case R.id.btn_B:
                    if (base == 16 && calc == false) {
                        if(first) pressed = "";
                        pressed += "B";
                        first = false;
                    }
                    break;
                case R.id.btn_C:
                    if (base == 16 && calc == false) {
                        if(first) pressed = "";
                        pressed += "C";
                        first = false;
                    }
                    break;
                case R.id.btn_D:
                    if (base == 16 && calc == false) {
                        if(first) pressed = "";
                        pressed += "D";
                        first = false;
                    }
                    break;
                case R.id.btn_E:
                    if (base == 16 && calc == false) {
                        if(first) pressed = "";
                        pressed += "E";
                        first = false;
                    }
                    break;
                case R.id.btn_F:
                    if (base == 16 && calc == false) {
                        if(first) pressed = "";
                        pressed += "F";
                        first = false;
                    }
                    break;
                case R.id.btn_float:
                    if (!isFloat && base == 10 && calc == true) {
                        pressed += ".";
                        isFloat = true;
                        first = false;
                    }
                    break;
                case R.id.btn_plusNeg:
                    if (first && base == 10 && calc == true) {
                        if (isNeg) pressed = "0";
                        else pressed = "-0";
                        isNeg = !isNeg;
                    }
                    break;
                case R.id.btn_removeAll:
                    initalize_from_first();
                    break;
                case R.id.btn_backSpace:
                    int size = pressed.length();
                    if(size > 0 && pressed.charAt(0) != '0'){
                        pressed = pressed.substring(0, size - 1);
                        size = pressed.length();
                    }
                    if (size == 0){
                        pressed = "0";
                        first = true;
                    }
            }

            /*if(pressed.length() <= 9)*/tv.setText(viewText + operation + "\n" + pressed);
        }
    };

    View.OnClickListener baseListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(calc == false) {
                switch (view.getId()) {
                    case R.id.btn_decimal:
                        convert_from_to(base, 10);
                        base = 10;
                        break;
                    case R.id.btn_hexa:
                        convert_from_to(base, 16);
                        base = 16;
                        break;
                    case R.id.btn_binary:
                        convert_from_to(base, 2);
                        base = 2;
                        break;
                    case R.id.btn_octal:
                        convert_from_to(base, 8);
                        base = 8;
                        break;
                }
                tv.setText("\n" + pressed);
            }
            else{
                Toast.makeText(getActivity(),"Can not change base in cal mode",Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener memoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(calc == false) {
                Toast.makeText(getActivity(),"can not use in con mode",Toast.LENGTH_SHORT).show();
                return;
            }
            double lastValue;
            switch (view.getId()){
                case R.id.btn_memRemove:
                    memory.clear();
                    memory.push(0.0);
                    break;
                case R.id.btn_memRecall:
                    pressed = memory.peek().toString();
                    double value = Double.parseDouble(pressed);
                    if(value > 0) first = false;
                    if(value != Math.ceil(value)) isFloat = true;
                    if(value < 0) isNeg = true;
                    firstOp = true;
                    tv.setText(viewText + operation + "\n" + pressed);
                    break;
                case R.id.btn_memPlus:
                    lastValue = memory.peek();
                    memory.pop();
                    lastValue += Double.parseDouble(pressed);
                    memory.push(lastValue);
                    break;
                case R.id.btn_memSubtract:
                    lastValue = memory.peek();
                    memory.pop();
                    lastValue -= Double.parseDouble(pressed);
                    memory.push(lastValue);
                    break;
                case R.id.btn_memStore:
                    lastValue = Double.parseDouble(pressed);
                    memory.push(lastValue);
                    break;
            }
        }
    };


    View.OnClickListener opsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(operation == "=" && calc) initalize_from_first();
            if(firstOp && calc){
                operandOne = Double.parseDouble(pressed);
                if(firstRun) {
                    result = operandOne;
                    firstRun = false;
                    viewText += pressed;
                }
                else {
                    switch (operation) {
                        case "+":
                            viewText += " + ";
                            result += operandOne;
                            break;
                        case "-":
                            viewText += " - ";
                            result -= operandOne;
                            break;
                        case "*":
                            viewText += " * ";
                            result *= operandOne;
                            break;
                        case "÷":
                            viewText += " ÷ ";
                            result /= operandOne;
                            if(operandOne == 0) {
                                initalize_from_first();
                                tv.setText("\n" + "Can not divide by 0");
                                return;
                            }
                            break;
                        case "%":
                            viewText += " % ";
                            result %= operandOne;
                            if(operandOne == 0) {
                                initalize_from_first();
                                tv.setText("\n" + "undefined value");
                                return;
                            }
                            break;
                    }
                    viewText += pressed;
                }
                tv.setText(viewText + "\n");
                first = true;
                isNeg = isFloat = false;
                pressed = "0";
                firstOp = false;
            }

            switch (view.getId()){
                case R.id.btn_sum:
                    if(calc) {
                        tv.setText(viewText + " +" + "\n" + result);
                        operation = "+";
                    }
                    break;
                case R.id.btn_subtract:
                    if(calc) {
                        tv.setText(viewText + " - " + "\n" + result);
                        operation = "-";
                    }
                    break;
                case R.id.btn_multiply:
                    if(calc) {
                        tv.setText(viewText + " * " + "\n" + result);
                        operation = "*";
                    }
                    break;
                case R.id.btn_divide:
                    if(calc) {
                        tv.setText(viewText + " ÷ " + "\n" + result);
                        operation = "÷";
                    }
                    break;
                case R.id.btn_equal:
                    if(calc) {
                        tv.setText(viewText + " = " + "\n" + result);
                        pressed = Double.toString(result);
                        operation = "=";
                        //initalize_from_first();
                    }
                    break;
                case R.id.btn_mod:
                    if(calc) {
                        tv.setText(viewText + " % " + "\n" + result);
                        operation = "%";
                    }
                    break;
                case R.id.btn_calc:
                    calc = !calc;
                    if(calc) btns[30].setText(R.string.calc);
                    else btns[30].setText(R.string.conv);
                    initalize_from_first();
                    break;
            }
        }
    };







    public Calculator() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        memory = new Stack<Double>();
        memory.push((Double)0.0);

        tv = (TextView)view.findViewById(R.id.TV_numbers);

        /* numbers buttons */
        btns[0] = (Button)view.findViewById(R.id.btn_num0);
        btns[1] = (Button)view.findViewById(R.id.btn_num1);
        btns[2] = (Button)view.findViewById(R.id.btn_num2);
        btns[3] = (Button)view.findViewById(R.id.btn_num3);
        btns[4] = (Button)view.findViewById(R.id.btn_num4);
        btns[5] = (Button)view.findViewById(R.id.btn_num5);
        btns[6] = (Button)view.findViewById(R.id.btn_num6);
        btns[7] = (Button)view.findViewById(R.id.btn_num7);
        btns[8] = (Button)view.findViewById(R.id.btn_num8);
        btns[9] = (Button)view.findViewById(R.id.btn_num9);
        btns[10] = (Button)view.findViewById(R.id.btn_A);
        btns[11] = (Button)view.findViewById(R.id.btn_B);
        btns[12] = (Button)view.findViewById(R.id.btn_C);
        btns[13] = (Button)view.findViewById(R.id.btn_D);
        btns[14] = (Button)view.findViewById(R.id.btn_E);
        btns[15] = (Button)view.findViewById(R.id.btn_F);
        btns[16] = (Button)view.findViewById(R.id.btn_float);
        btns[17] = (Button)view.findViewById(R.id.btn_plusNeg);
        btns[18] = (Button)view.findViewById(R.id.btn_removeAll);
        btns[19] = (Button)view.findViewById(R.id.btn_backSpace);


        for(int i = 0; i <= 19; i++){
            btns[i].setOnClickListener(numberListener);
        }

        /* base buttons */
        btns[20] = (Button)view.findViewById(R.id.btn_decimal);
        btns[21] = (Button)view.findViewById(R.id.btn_binary);
        btns[22] = (Button)view.findViewById(R.id.btn_hexa);
        btns[23] = (Button)view.findViewById(R.id.btn_octal);

        for(int i = 20; i <= 23; i++){
            btns[i].setOnClickListener(baseListener);
        }

        /* operations buttons */
        btns[24] = (Button)view.findViewById(R.id.btn_sum);
        btns[25] = (Button)view.findViewById(R.id.btn_subtract);
        btns[26] = (Button)view.findViewById(R.id.btn_multiply);
        btns[27] = (Button)view.findViewById(R.id.btn_divide);
        btns[28] = (Button)view.findViewById(R.id.btn_mod);
        btns[29] = (Button)view.findViewById(R.id.btn_equal);
        btns[30] = (Button)view.findViewById(R.id.btn_calc);

        for(int i = 24; i <= 30; i++){
            btns[i].setOnClickListener(opsListener);
        }

        /* memory buttons */
        btns[31] = (Button)view.findViewById(R.id.btn_memPlus);
        btns[32] = (Button)view.findViewById(R.id.btn_memSubtract);
        btns[33] = (Button)view.findViewById(R.id.btn_memRecall);
        btns[34] = (Button)view.findViewById(R.id.btn_memRemove);
        btns[35] = (Button)view.findViewById(R.id.btn_memStore);

        for(int i = 31; i <= 35; i++){
            btns[i].setOnClickListener(memoryListener);
        }
    }
}