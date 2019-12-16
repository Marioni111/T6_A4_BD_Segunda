package Controlador;

import java.util.List;

import Entidades.Alumno;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AlumnoDAO {

    @Query("SELECT * FROM Alumno")
    List<Alumno> getAll();

    @Query("SELECT * FROM Alumno WHERE numControl = :nc")
    Alumno buscarAlumno(String nc);

    @Query("DELETE FROM Alumno WHERE numControl = :nc")
    int eliminarAlumno(String nc);

    /*
    @Query("SELECT * FROM Alumno WHERE uid IN (:userIds)")
    List<Alumno> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Alumno WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Alumno findByName(String first, String last); */

    @Insert
    void insertAll(Alumno... alumnos);

    @Delete
    void delete(Alumno alumno);

    @Update
    void update(Alumno alumno);



}
