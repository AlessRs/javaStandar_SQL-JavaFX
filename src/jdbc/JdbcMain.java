package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JdbcMain extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/jdbc/TablaFx.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static void selectALL(Connection conexion) throws SQLException {
        List<Lugar> lugares = new ArrayList();
        Statement smtm = conexion.createStatement();

        ResultSet result = smtm.executeQuery("select * from lugar1");

        while (result.next()) {
            String id = result.getString("id");
            String nombreResponsable = result.getString("NombreResponsable");
            String direccion = result.getString("direccion");
            String router = result.getString("Router");
            lugares.add(new Lugar(id, nombreResponsable, direccion, router));
        }

        lugares.forEach(System.out::println);

    }

    private static void selectById(Connection conexion, String id) throws SQLException {
        Lugar lugar = null;

        PreparedStatement smtm = conexion.prepareStatement("select * from lugar where id=?");
        smtm.setString(1, id);
        ResultSet result = smtm.executeQuery();

        if (result.next()) {
            String nombreResponsable = result.getString("NombreResponsable");
            String direccion = result.getString("direccion");
            String router = result.getString("Router");
            lugar = new Lugar(id, nombreResponsable, direccion, router);
        }

        System.out.println(lugar);

    }

    private static void insertar(Connection conexion) throws SQLException {
        //Statement stmt = conexion.createStatement();
        PreparedStatement stmt = conexion.prepareStatement("insert into lugar1 (id, NombreResponsable, Direccion, Router) values (?, ?,?,?) ");

        Lugar lugar = new Lugar("1", "Pedro", "Jujuy 45600", "toplink");
        Lugar lugar2 = new Lugar("2", "Oscar", "Jujuy 45600", "toplink");
        List<Lugar> lugares = new ArrayList<>();
        lugares.add(lugar);
        //lugares.add(lugar2);

        for (Lugar l : lugares) {
            /*int records = stmt.executeUpdate("insert into lugar (NombreResponsable, Direccion, Router) values ('"+l.getNombreResponsable()
                    +"', '"+l.getDireccion()+"','"+
                        l.getRouter()+"') ");*/
            stmt.setString(1, l.getId());
            stmt.setString(2, l.getNombreResponsable());
            stmt.setString(3, l.getDireccion());
            stmt.setString(4, l.getRouter());
            int records = stmt.executeUpdate();
            System.out.println("Registros modificados: " + records);
        }
    }

}
