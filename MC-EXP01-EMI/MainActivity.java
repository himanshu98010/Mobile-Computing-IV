package com.example.emi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText P=(EditText)findViewById(R.id.editText);
        final EditText R=(EditText)findViewById(com.example.emi.R.id.editText2);
        final EditText N=(EditText)findViewById(com.example.emi.R.id.editText3);

        final EditText emi=(EditText)findViewById(com.example.emi.R.id.editText5);
        Button emibtn=(Button)findViewById(com.example.emi.R.id.button);
        emibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loan=P.getText().toString();
                String rate=R.getText().toString();
                String tenure=R.getText().toString();
                if(TextUtils.isEmpty(loan))
                {
                    P.setError("Enter loan amount");
                    return;
                }
                if(TextUtils.isEmpty(rate))
                {
                    R.setError("Enter rate");
                    return;
                }
                if(TextUtils.isEmpty(tenure))
                {
                    N.setError("Enter the loan tenure");
                    return;
                }
                float p=Float.parseFloat(loan);
                float r=Float.parseFloat(rate)/100;
                int t=Integer.parseInt(tenure);

                double e=p*r*(Math.pow((1+r),t))/(Math.pow((1+r),t)-1);
                emi.setText(String.valueOf(e));
            }
        });

        Button reset=(Button)findViewById(com.example.emi.R.id.button2);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                P.setText("");
                R.setText("");
                N.setText("");
            }
        });

    }
}
