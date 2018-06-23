package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LugarDAOImpl implements LugarDAO {
    Connection connection;
    
    LugarDAOImpl(Connection connection){
        this.connection= connection;
    }

    @Override
    public Lugar insertar(Lugar lugar) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("insert into lugar (NombreResponsable, Direccion, Router) values (?,?,?) ", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, lugar.getNombreResponsable());
        stmt.setString(2, lugar.getDireccion());
        stmt.setString(3, lugar.getRouter());
        stmt.executeUpdate();
        ResultSet result = stmt.getGeneratedKeys();
        result.next();
        lugar.setId(result.getString(1));
        return lugar;
    }

    @Override
    public List<Lugar> obtenerTodos() throws SQLException {
        List<Lugar> lugares = new ArrayList();
        Statement smtm = connection.createStatement();

        ResultSet result = smtm.executeQuery("select * from lugar");

        while (result.next()) {
            String id = result.getString("id");
            String nombreResponsable = result.getString("NombreResponsable");
            String direccion = result.getString("direccion");
            String router = result.getString("Router");
            lugares.add(new Lugar(id, nombreResponsable, direccion, router));
        }
        
        return lugares;
    }

    @Override
    public void actualizar(Lugar lugar)  throws SQLException {
        PreparedStatement stmnt = connection.prepareStatement("update lugar set NombreResponsable=?,  Direccion=?, Router=? where id=?");
        stmnt.setString(1, lugar.getNombreResponsable());
        stmnt.setString(2, lugar.getDireccion());
        stmnt.setString(3, lugar.getRouter());
        stmnt.setString(4, lugar.getId());
        stmnt.executeUpdate();
        
    }

    @Override
    public void borrar(Lugar lugar)  throws SQLException {
        PreparedStatement smtm = connection.prepareStatement("delete from lugar where id=?");
        smtm.setString(1, lugar.getId());
        smtm.executeUpdate();
    }

    @Override
    public Lugar buscarPorId(String id)throws SQLException {
        Lugar lugar = null;
            
        PreparedStatement smtm = connection.prepareStatement("select * from lugar where id=?");
        smtm.setString(1, id);
        ResultSet result = smtm.executeQuery();

        if ( result.next()) {
            String nombreResponsable = result.getString("NombreResponsable");
            String direccion = result.getString("direccion");
            String router = result.getString("Router");
            lugar = new Lugar(id, nombreResponsable, direccion, router);
        }
        
        return lugar;
    }

}