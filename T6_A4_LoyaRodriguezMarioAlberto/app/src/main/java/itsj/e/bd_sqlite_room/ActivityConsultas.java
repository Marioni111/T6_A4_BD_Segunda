package itsj.e.bd_sqlite_room;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Entidades.Alumno;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import bd_room.EscuelaBD;

public class ActivityConsultas extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText txtBuscar;
    private Button btnBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        txtBuscar = findViewById(R.id.txt_buscarC);
        btnBuscar = findViewById(R.id.btn_searchC);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        EscuelaBD bd = Room.databaseBuilder(getApplicationContext(), EscuelaBD.class, "Escuela").allowMainThreadQueries().build();
        ArrayList listaAlumnos = new ArrayList(bd.alumnoDAO().getAll());
        mAdapter = new MyAdapter(listaAlumnos);
        recyclerView.setAdapter(mAdapter);

        /*
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
    }


    /*
    public void OnClick(View v) {
        new Thread((new Runnable() {
            @Override
            public void run() {

                EscuelaBD bd = Room.databaseBuilder(getApplicationContext(), EscuelaBD.class, "Escuela").build();

                //bd.alumnoDAO().insertAll(new Alumno("1", (byte)1 ));
                //bd.alumnoDAO().insertAll(new Alumno("2", (byte)2 ));
                //bd.alumnoDAO().insertAll(new Alumno("3", (byte)3 ));

                List<Alumno> listaAlumnos = bd.alumnoDAO().getAll();
                for(Alumno a: listaAlumnos) {
                    Log.i("ALUMNO -->", a.toString());
                }

                ArrayList<Alumno> list = new ArrayList<>(listaAlumnos);

            }
        })).start();
    }
    */
}


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Alumno> alumnos;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public MyAdapter(ArrayList<Alumno> listaAlumnos) {
        alumnos = listaAlumnos;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_registros, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(alumnos.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }
}




