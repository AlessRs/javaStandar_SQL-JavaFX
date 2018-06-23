package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface LugarDAO {
    
    void insertar(Lugar lugar) throws SQLException;
    List<Lugar> obtenerTodos() throws SQLException;
    void actualizar(Lugar lugar) throws SQLException;
    void borrar(Lugar lugar) throws SQLException;
    Lugar buscarPorId (String id) throws SQLException;
        
}
