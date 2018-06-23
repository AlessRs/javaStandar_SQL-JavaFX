package jdbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Alessandro
 */
public class TablaFxController implements Initializable {

    static LugarDAO DAO;

    @FXML
    private TextField idText;
    @FXML
    private TextField responsableText;
    @FXML
    private TextField direccionText;
    @FXML
    private TextField routerText;
    @FXML
    private Button botonInsertar;
    @FXML
    private Button botonActualizar;
    @FXML
    private Button botonSelleccionar;
    @FXML
    private Button botonBorrar;
    @FXML
    private TableView<Lugar> tabla;
    @FXML
    private TableColumn<Lugar, String> idColumna;
    @FXML
    private TableColumn<Lugar, String> nombreColumna;
    @FXML
    private TableColumn<Lugar, String> direccionColumna;
    @FXML
    private TableColumn<Lugar, String> routerColumna;

    private ObservableList<Lugar> tablaVisible = FXCollections.observableArrayList();

    @FXML
    private void botonInsertarAccion(ActionEvent event) {
        try {
            tablaVisible.add(DAO.insertar(new Lugar(responsableText.getText(), direccionText.getText(), routerText.getText())));
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMain.class.getName()).log(Level.INFO, "Valores invalidos", ex);
        }
    }

    @FXML
    private void botonActualizarAccion(ActionEvent event) {
        Lugar lugarBKP;
        Lugar lugar ;
        lugar = tabla.getSelectionModel().getSelectedItem();
        lugarBKP = lugar.clone();
        
        try {
            
            lugar.setDireccion(direccionText.getText());
            lugar.setNombreResponsable(responsableText.getText());
            lugar.setRouter(routerText.getText());
            DAO.actualizar(lugar);
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMain.class.getName()).log(Level.INFO, "Valores invalidos", ex);
            lugar.setDireccion(lugarBKP.getDireccion());
            lugar.setNombreResponsable(lugarBKP.getNombreResponsable());
            lugar.setRouter(lugarBKP.getRouter());
        }
    }

    @FXML
    private void botonBorrarAccion(ActionEvent event) {
        
        Lugar lugarBKP;
        Lugar lugar ;
        lugar = tabla.getSelectionModel().getSelectedItem();
        lugarBKP = lugar.clone();
        tablaVisible.remove(lugar);
        try {
            
            DAO.borrar(tabla.getSelectionModel().getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMain.class.getName()).log(Level.INFO, "Valores invalidos", ex);
        }
    }

    @FXML
    private void botonSeleccionarAccion(ActionEvent event) {
        try {
            tablaVisible.clear();
            tablaVisible.addAll(DAO.obtenerTodos());
        } catch (SQLException ex) {
            Logger.getLogger(JdbcMain.class.getName()).log(Level.INFO, "Valores invalidos", ex);
        }

    }

    @FXML
    private void seleccionarCuadro(MouseEvent event) {
        Lugar lugar = tabla.getSelectionModel().getSelectedItem();
        idText.setText(lugar.getId());
        direccionText.setText(lugar.getDireccion());
        responsableText.setText(lugar.getNombreResponsable());
        routerText.setText(lugar.getRouter());

    }

    @FXML
    private void idEscrito(KeyEvent event) {
        if(idText.getText().equals("")){
            botonBorrar.setDisable(true);
            botonActualizar.setDisable(true);
        }else{
            botonBorrar.setDisable(false);
            botonActualizar.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumna.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreResponsableProperty());
        direccionColumna.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        routerColumna.setCellValueFactory(cellData -> cellData.getValue().routerProperty());
        
        try {
            DAO = new LugarDAOImpl(AdministradorDeConecciones.obtenerConeccion());
            tablaVisible.addAll(DAO.obtenerTodos());
            tabla.setItems(tablaVisible);

        } catch (SQLException ex) {
            Logger.getLogger(JdbcMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
