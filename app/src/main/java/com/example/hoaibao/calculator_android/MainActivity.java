package com.example.hoaibao.calculator_android;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText ed_input;
    public TextView tv_result;

    public Button btnnumber1;
    public Button btnnumber2;
    public Button btnnumber3;
    public Button btnnumber4;
    public Button btnnumber5;
    public Button btnnumber6;
    public Button btnnumber7;
    public Button btnnumber8;
    public Button btnnumber9;
    public Button btnnumber0;

    public Button btnCong;
    public Button btnTru;
    public Button btnnNhan;
    public Button btnChia;

    public Button btnResult;
    public Button btnDetele;
    public Button btnPoint;
    public Button btnDeteleAll;

    ArrayList<Double> arrso;
    ArrayList<String> arrpheptoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innitbtn();

    }
    public void innitbtn() {
        ed_input = findViewById(R.id.ed_input);
        tv_result = findViewById(R.id.tv_result);

        btnnumber1 = findViewById(R.id.btnnumber1);
        btnnumber2 = findViewById(R.id.btnnumber2);
        btnnumber3 = findViewById(R.id.btnnumber3);
        btnnumber4 = findViewById(R.id.btnnumber4);
        btnnumber5 = findViewById(R.id.btnnumber5);
        btnnumber6 = findViewById(R.id.btnnumber6);
        btnnumber7 = findViewById(R.id.btnnumber7);
        btnnumber8 = findViewById(R.id.btnnumber8);
        btnnumber9 = findViewById(R.id.btnnumber9);
        btnnumber0 = findViewById(R.id.btnnumber0);

        btnChia = findViewById(R.id.btnChia);
        btnCong = findViewById(R.id.btnCong);
        btnDetele = findViewById(R.id.btnDelete);
        btnTru = findViewById(R.id.btnTru);
        btnnNhan = findViewById(R.id.btnNhan);
        btnPoint = findViewById(R.id.btnPoint);
        btnResult = findViewById(R.id.btnResult);
        btnDeteleAll = findViewById(R.id.btnDeleteAll);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnnumber0:
                ed_input.append("0");
                break;
            case R.id.btnnumber1:
                ed_input.append("1");
                break;
            case R.id.btnnumber2:
                ed_input.append("2");
                break;
            case R.id.btnnumber3:
                ed_input.append("3");
                break;
            case R.id.btnnumber4:
                ed_input.append("4");
                break;
            case R.id.btnnumber5:
                ed_input.append("5");
                break;
            case R.id.btnnumber6:
                ed_input.append("6");
                break;
            case R.id.btnnumber7:
                ed_input.append("7");
                break;
            case R.id.btnnumber8:
                ed_input.append("8");
                break;
            case R.id.btnnumber9:
                ed_input.append("9");
                break;
            case R.id.btnDelete:
                //ed_input.setText(delete(ed_input.getText().toString()));
                BaseInputConnection txtconn = new BaseInputConnection(ed_input, true);
                txtconn.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btnPoint:
                ed_input.append(".");
                break;
            case R.id.btnCong:
                ed_input.append("+");
                break;
            case R.id.btnTru:
                ed_input.append("-");
                break;
            case R.id.btnNhan:
                ed_input.append("*");
                break;
            case R.id.btnChia:
                ed_input.append("/");
                break;
            case R.id.btnDeleteAll:
                ed_input.setText("");
                break;
            case R.id.btnResult:
                xuly();
                break;
            default:
                break;
        }

    }
    public String delete(String s) {
        String temp = s.substring(0, s.length() - 1);
        return temp;
    }

    public int Addarrpheptoan(String input) {
        arrpheptoan = new ArrayList<>();
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length - 1; i++) {
            switch (arr[i]) {
                case '+':
                    arrpheptoan.add(arr[i] + "");
                    break;
                case '-':
                    arrpheptoan.add(arr[i] + "");
                    break;
                case '*':
                    arrpheptoan.add(arr[i] + "");
                    break;
                case '/':
                    arrpheptoan.add(arr[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public void Addarrso(String input) {
        arrso = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher a = regex.matcher(input);
        while (a.find()) {
            arrso.add(Double.valueOf(a.group(1)));
        }
    }

    public void xuly() {
        DecimalFormat fm = new DecimalFormat("####.##");
        Addarrso(ed_input.getText().toString());
        Addarrpheptoan(ed_input.getText().toString());
        Double kq = 0.0;
        if (arrpheptoan.size() >= arrso.size()) {
            tv_result.setText("Lỗi phép toán");
        } else {
            for (int i = 0; i < arrso.size() - 1; i++) {
                switch (arrpheptoan.get(i)) {
                    case "+":
                        if (i == 0)
                            kq = arrso.get(i) + arrso.get(i + 1);
                        else
                            kq += arrso.get(i + 1);
                        break;
                    case "-":
                        if (i == 0)
                            kq = arrso.get(i) - arrso.get(i + 1);
                        else
                            kq -= arrso.get(i + 1);
                        break;
                    case "*":
                        if (i == 0)
                            kq = arrso.get(i) * arrso.get(i + 1);
                        else
                            kq *= arrso.get(i + 1);
                        break;
                    case "/":
                        if (i == 0)
                            if (arrso.get(i + 1) == 0) {
                                tv_result.setText("Lỗi chia 0##");
                                ed_input.setText("");
                                return;
                            } else {
                                kq = arrso.get(i) / arrso.get(i + 1);
                            }
                        else if (arrso.get(i + 1) == 0) {
                            tv_result.setText("Lỗi chia 0##");
                            ed_input.setText("");
                            return;
                        } else {
                            kq /= arrso.get(i + 1);
                        }
                        break;
                    default:
                        break;
                }
                tv_result.setText(fm.format(kq).toString());
                ed_input.setText("");
            }
        }
    }
}
