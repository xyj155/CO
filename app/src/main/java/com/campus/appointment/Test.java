package com.campus.appointment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        RadioButton radioButton=new RadioButton(this);
        final EditText editText=new EditText(this);
        Button button=new Button(this);
        editText.setEnabled(false);
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editText.setEnabled(true);//启用编辑
                    editText.setFocusableInTouchMode(true);
                    editText.setFocusable(true);
                    editText.requestFocus();
                }else {
                    editText.setEnabled(false);//禁用编辑
                    editText.setFocusable(false);
                    editText.setFocusableInTouchMode(false);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                //上传逻辑
            }
        });
    }
}
