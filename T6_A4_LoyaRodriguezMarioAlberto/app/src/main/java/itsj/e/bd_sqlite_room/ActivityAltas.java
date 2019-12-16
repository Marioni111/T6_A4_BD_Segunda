package itsj.e.bd_sqlite_room;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import Controlador.AlumnoDAO;
import Entidades.Alumno;
import androidx.room.Room;
import bd_room.EscuelaBD;

public class ActivityAltas extends Activity {

    Spinner spinnerEdad, spinnerSemestre, spinnerCarrera;
    EditText txtNumControl, txtNombre, txtPrimerAp, txtSegundoAp;
    Button botonAñadir, botonCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);


        spinnerEdad = findViewById(R.id.spinnerEdad);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Edad, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapterEdad);

        spinnerSemestre = findViewById(R.id.spinnerSemestre);
        ArrayAdapter<CharSequence> adapterSemestre = ArrayAdapter.createFromResource(this, R.array.Semestre, android.R.layout.simple_spinner_item);
        adapterSemestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestre.setAdapter(adapterSemestre);

        spinnerCarrera = findViewById(R.id.spinnerCarrera);
        ArrayAdapter<CharSequence> adapterCarrera = ArrayAdapter.createFromResource(this, R.array.Carrera, android.R.layout.simple_spinner_item);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarrera.setAdapter(adapterCarrera);

        txtNumControl = findViewById(R.id.txtNumControl);
        txtNombre = findViewById(R.id.txtNombre);
        txtPrimerAp = findViewById(R.id.txtPrimerApellido);
        txtSegundoAp = findViewById(R.id.txtSegundoApellido);

        botonAñadir = findViewById(R.id.botonAñadir);
        botonCancelar = findViewById(R.id.botonCancelar);

        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EscuelaBD bd = Room.databaseBuilder(getApplicationContext(), EscuelaBD.class, "Escuela").allowMainThreadQueries().build();
                Alumno a = new Alumno(txtNumControl.getText().toString(), txtNombre.getText().toString(), txtPrimerAp.getText().toString(), txtSegundoAp.getText().toString(), Byte.parseByte(spinnerEdad.getSelectedItem().toString()), spinnerCarrera.getSelectedItem().toString(), Byte.parseByte(spinnerSemestre.getSelectedItem().toString()));

                bd.alumnoDAO().insertAll(a);

                List<Alumno> listaAlumnos = bd.alumnoDAO().getAll();
                for(Alumno al: listaAlumnos) {
                    if(al.getNumControl().equals(a.getNumControl())){
                        Toast.makeText(getApplicationContext(), "Se añadio un registro", Toast.LENGTH_SHORT).show();
                        txtNumControl.setText("");
                        txtNombre.setText("");
                        txtPrimerAp.setText("");
                        txtSegundoAp.setText("");
                        spinnerEdad.setSelection(0);
                        spinnerCarrera.setSelection(0);
                        spinnerSemestre.setSelection(0);
                        Log.i("ALUMNO -->", al.toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo añadir el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}

