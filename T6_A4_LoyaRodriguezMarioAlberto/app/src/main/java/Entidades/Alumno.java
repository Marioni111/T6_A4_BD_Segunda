package Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alumno {

        @NonNull
        @PrimaryKey
        private String numControl;
        @NonNull
        @ColumnInfo(name = "Nombre")
        private String nombre;
        @NonNull
        @ColumnInfo(name = "PrimerAp")
        private String primerAp;
        @NonNull
        @ColumnInfo(name = "SegundoAp")
        private String segundoAp;
        @NonNull
        @ColumnInfo(name = "Edad")
        private byte edad;
        @NonNull
        @ColumnInfo(name = "Carrera")
        private String carrera;
        @NonNull
        @ColumnInfo(name = "Semestre")
        private byte semestre;

        public Alumno() {

        }

        public Alumno(@NonNull String numControl, @NonNull String nombre, @NonNull String primerAp, @NonNull String segundoAp, byte edad, @NonNull String carrera, byte semestre) {
                this.numControl = numControl;
                this.nombre = nombre;
                this.primerAp = primerAp;
                this.segundoAp = segundoAp;
                this.edad = edad;
                this.carrera = carrera;
                this.semestre = semestre;
        }

        @NonNull
        public String getNumControl() {
                return numControl;
        }
        public void setNumControl(@NonNull String numControl) {
                this.numControl = numControl;
        }

        @NonNull
        public String getNombre() {
                return nombre;
        }
        public void setNombre(@NonNull String nombre) {
                this.nombre = nombre;
        }

        @NonNull
        public String getPrimerAp() {
                return primerAp;
        }
        public void setPrimerAp(@NonNull String primerAp) {
                this.primerAp = primerAp;
        }

        @NonNull
        public String getSegundoAp() {
                return segundoAp;
        }
        public void setSegundoAp(@NonNull String segundoAp) {
                this.segundoAp = segundoAp;
        }

        @NonNull
        public byte getEdad() {
                return edad;
        }
        public void setEdad(byte edad) {
                this.edad = edad;
        }

        @NonNull
        public String getCarrera() {
                return carrera;
        }
        public void setCarrera(@NonNull String carrera) {
                this.carrera = carrera;
        }

        @NonNull
        public byte getSemestre() {
                return semestre;
        }
        public void setSemestre(byte semestre) {
                this.semestre = semestre;
        }

        @Override
        public String toString() {
                return "======================================\n" +
                        "              A L U M N O            \n" +
                        "Numero de Control: "+numControl+"\n"+
                        "Nombre: "+nombre+"\n"+
                        "Primer Ap: "+primerAp+"\n"+
                        "Segundo Ap: "+segundoAp+"\n"+
                        "Edad: "+edad+"\n"+
                        "Carrera: "+carrera+"\n"+
                        "Semestre: "+semestre+"";
        }
}
