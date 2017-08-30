package com.critico.calculadoracalorias.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.critico.calculadoracalorias.R;

public class ResultActivity extends AppCompatActivity
{
    private TextView mb;
    private TextView cmp;
    private TextView cb;
    private TextView cs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mb = (TextView)findViewById(R.id.txRes1);
        cmp = (TextView)findViewById(R.id.txRes2);
        cb = (TextView)findViewById(R.id.txRes3);
        cs = (TextView)findViewById(R.id.txRes4);

        int Itbm = getIntent().getIntExtra("mb", 0);
        int Icmp = getIntent().getIntExtra("cmp", 0);
        int Icb = getIntent().getIntExtra("cb", 0);
        int Ics = getIntent().getIntExtra("cs", 0);

        mb.setText(""+Itbm);
        cmp.setText(""+Icmp);
        cb.setText(""+Icb);
        cs.setText(""+Ics);


    }



}
