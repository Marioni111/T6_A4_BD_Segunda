package itsj.e.bd_sqlite_room;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Entidades.Alumno;
import androidx.room.Room;
import bd_room.EscuelaBD;

public class ActivityCambios extends Activity {

    Spinner spinnerEdad, spinnerSemestre, spinnerCarrera;
    EditText txtNumControl, txtNombre, txtPrimerAp, txtSegundoAp, txtBuscar;
    Button botonModificar, botonCancelar, botonBuscar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        spinnerEdad = findViewById(R.id.spinner_edadC);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Edad, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapterEdad);

        spinnerSemestre = findViewById(R.id.spinner_semestreC);
        ArrayAdapter<CharSequence> adapterSemestre = ArrayAdapter.createFromResource(this, R.array.Semestre, android.R.layout.simple_spinner_item);
        adapterSemestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestre.setAdapter(adapterSemestre);

        spinnerCarrera = findViewById(R.id.spinner_carreraC);
        ArrayAdapter<CharSequence> adapterCarrera = ArrayAdapter.createFromResource(this, R.array.Carrera, android.R.layout.simple_spinner_item);
        adapterCarrera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarrera.setAdapter(adapterCarrera);

        txtNumControl = findViewById(R.id.txt_numControlC);
        txtNombre = findViewById(R.id.txt_nombreC);
        txtPrimerAp = findViewById(R.id.txt_primerApC);
        txtSegundoAp = findViewById(R.id.txt_segundoApC);
        txtBuscar = findViewById(R.id.txt_buscarC);

        botonModificar = findViewById(R.id.btn_modificar);
        botonCancelar = findViewById(R.id.btn_cancelarC);
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
                    Toast.makeText(ActivityCambios.this, "Datos cargados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityCambios.this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    Log.i("Activity Bajas", "No se encontró resultado");
                }

            }
        });
    }

    public void Modificar(View v) {
        EscuelaBD bd = Room.databaseBuilder(getApplicationContext(), EscuelaBD.class, "Escuela").allowMainThreadQueries().build();
        Alumno a = new Alumno(txtNumControl.getText().toString(), txtNombre.getText().toString(), txtPrimerAp.getText().toString(), txtSegundoAp.getText().toString(), Byte.parseByte(spinnerEdad.getSelectedItem().toString()), spinnerCarrera.getSelectedItem().toString(), Byte.parseByte(spinnerSemestre.getSelectedItem().toString()));

        bd.alumnoDAO().update(a);
        Toast.makeText(ActivityCambios.this, "Registro Modificado", Toast.LENGTH_SHORT).show();
        txtNumControl.setText("");
        txtBuscar.setText("");
        txtNombre.setText("");
        txtPrimerAp.setText("");
        txtSegundoAp.setText("");
        spinnerEdad.setSelection(0);
        spinnerSemestre.setSelection(0);
        spinnerCarrera.setSelection(0);

    }

}
