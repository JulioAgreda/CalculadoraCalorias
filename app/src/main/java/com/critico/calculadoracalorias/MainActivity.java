package com.critico.calculadoracalorias;

    import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.critico.calculadoracalorias.activity.ResultActivity;

public class MainActivity extends AppCompatActivity
{
    private Spinner listaActividades;
    private Button boton;
    private EditText peso;
    private EditText estatura;
    private EditText edad;
    private RadioButton rgbHombre;
    private RadioButton rgbMujer;
    private RadioButton rgbKilo;
    private RadioButton rgbLibra;

    private int mb;
    private int cmp;
    private int cb;
    private int cs;

    private int est;
    private int eda;
    private int pes;
    private String actividad;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaActividades = (Spinner)findViewById(R.id.spActividad);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.actividades , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaActividades.setAdapter(spinner_adapter);

        boton = (Button)findViewById(R.id.btnCalcular);

        boton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                peso =(EditText)findViewById(R.id.txPeso);
                estatura =(EditText)findViewById(R.id.txEstatura);
                edad =(EditText)findViewById(R.id.txPeso);
                rgbHombre = (RadioButton)findViewById(R.id.rbHombre);
                rgbMujer = (RadioButton)findViewById(R.id.rbMujer);
                rgbKilo = (RadioButton)findViewById(R.id.rbKilos);
                rgbLibra = (RadioButton)findViewById(R.id.rbLibras);

                pes = Integer.valueOf(peso.getText().toString());
                est = Integer.valueOf(estatura.getText().toString());
                eda = Integer.valueOf(edad.getText().toString());
                
                actividad = listaActividades.getSelectedItem().toString();

                convertirLibras();

                if(rgbHombre.isChecked())
                {
                    double db;
                    //  Obteniendo el Metabolismo Basal
                    db = (10*pes)+(6.26*est)-(5*eda)+5;
                    mb = (int)db;

                    actividadSeleccionada();

                    bajarSubirCalorias(mb);

                    enviar();

                    //Toast.makeText(MainActivity.this, selec, Toast.LENGTH_SHORT).show();
                }

                if(rgbMujer.isChecked())
                {
                    double db;
                    //  Obteniendo el Metabolismo Basal
                    db = (10*pes)+(6.26*est)-(5*eda)-161;
                    mb = (int)db;

                    actividadSeleccionada();

                    bajarSubirCalorias(mb);

                    enviar();

                    //Toast.makeText(MainActivity.this, selec, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void convertirLibras()
    {
        if(rgbLibra.isChecked())
        {
            double d;
            d = pes/0.45;
            pes = (int)d;
        }
    }

    public void actividadSeleccionada()
    {
        switch(actividad)
        {
            // Calorias para mantener el peso
            case "Sedentario":
                double c = mb * 1.2;
                cmp = (int)c;
                break;

            case "Actividad Moderada":
                double d = mb * 1.375;
                cmp = (int) d;
                break;

            case "Actividad Intensa":
                double a = mb * 1.55;
                cmp = (int) a;
                break;

            case "Actividad Muy Intensa":
                double b = mb * 1.725;
                cmp = (int) b;
                break;
        }
    }

    public void bajarSubirCalorias(int mb)
    {
        // Calorias para bajar de peso
        cb = (int)mb - (mb*15/100);
        // Calorias para subir de peso
        cs = (int)mb + (mb*15/100);
    }

    public void enviar()
    {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("mb", mb);
        intent.putExtra("cmp",cmp);
        intent.putExtra("cb",cb);
        intent.putExtra("cs",cs);
        startActivity(intent);
    }
}
