package itsj.e.bd_sqlite_room;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Controlador.AlumnoDAO;
import Entidades.Alumno;
import androidx.room.Room;
import bd_room.EscuelaBD;

public class ActivityBajas extends Activity {

    Spinner spinnerEdad, spinnerSemestre, spinnerCarrera;
    EditText txtNumControl, txtNombre, txtPrimerAp, txtSegundoAp, txtBuscar;
    Button botonEliminar, botonCancelar, botonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        spinnerEdad = findViewById(R.id.spinner_edadB);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Edad, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapterEdad);

        spinnerSemestre = findViewById(R.id.spinner_semestreB);
        ArrayAdapter<CharSequence> adapterSemestre = ArrayAdapter.createFromResource(this, R.array.Semestre, android.R.layout.simple_spinner_item);
        adapterSemestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestre.setAdapter(adapterSemestre);

        spinnerCarrera = findViewById(R.id.spinner_carreraB);
        ArrayAdapter<CharSequence> adapterCarrera = ArrayAdapter.createFromResource(this, R.array.Carrera, android.R.layout.simple_spinner_item);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarrera.setAdapter(adapterCarrera);

        txtNumControl = findViewById(R.id.txt_numControlB);
        txtNombre = findViewById(R.id.txt_nombreB);
        txtPrimerAp = findViewById(R.id.txt_primerApB);
        txtSegundoAp = findViewById(R.id.txt_segundoApB);
        txtBuscar = findViewById(R.id.txt_buscarC);

        botonEliminar = findViewById(R.id.btn_eliminarB);
        botonCancelar = findViewById(R.id.btn_cancelarB);
        botonBuscar = findViewById(R.id.btn_searchC);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EscuelaBD bd = Room.databaseBuilder(getApplicationContext(), EscuelaBD.class, "Escuela").allowMainThreadQueries().build();

                String buscar = txtBuscar.getText().toString();
                Alumno al = bd.alumnoDAO().buscarAlumno(buscar);

                if( al != null ) {
                    Log.i("Activity Bajas", "Se están llenando");
                    txtNumControl.setText(al.getNumControl());
                    txtNombre.setText(al.getNombre());
                    txtPrimerAp.setText(al.getPrimerAp());
                    txtSegundoAp.setText(al.getSegundoAp());

                    int posicion = 0;
                    for (int i = 0; i < spinnerEdad.getCount(); i++) {
                        if (spinnerEdad.getItemAtPosition(i).toString().equalsIgnoreCase(String.valueOf(al.getEdad())) ) {
                            posicion = i;
                        }
                    }
                    spinnerEdad.setSelection(posicion);
                    posicion = 0;
                    for (int i = 0; i < spinnerSemestre.getCount(); i++) {
                        if (spinnerSemestre.getItemAtPosition(i).toString().equalsIgnoreCase(String.valueOf(al.getSemestre())) ) {
                            posicion = i;
                        }
                    }
                    spinnerSemestre.setSelection(posicion);
                    posicion = 0;
                    for (int i = 0; i < spinnerCarrera.getCount(); i++) {
                        if (spinnerCarrera.getItemAtPosition(i).toString().equalsIgnoreCase(al.getCarrera()) ) {
                            posicion = i;
                        }
                    }
                    spinnerCarrera.setSelection(posicion);
                    Toast.makeText(ActivityBajas.this, "Datos cargados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityBajas.this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    Log.i("Activity Bajas", "No se encontró resultado");
                }

            }
        });
        
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EscuelaBD bd = Room.databaseBuilder(getApplicationContext(), EscuelaBD.class, "Escuela").allowMainThreadQueries().build();

                if(bd.alumnoDAO().eliminarAlumno(txtNumControl.getText().toString()) != 0 ) {
                    Log.i("ActivityBajas", "Si se pudo");
                    txtNumControl.setText("");
                    txtBuscar.setText("");
                    txtNombre.setText("");
                    txtPrimerAp.setText("");
                    txtSegundoAp.setText("");
                    spinnerEdad.setSelection(0);
                    spinnerSemestre.setSelection(0);
                    spinnerCarrera.setSelection(0);
                    Toast.makeText(ActivityBajas.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("Activity Bajas", "No se pudo");
                    Toast.makeText(ActivityBajas.this, "No se pudo eliminar el registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        
    }

}
