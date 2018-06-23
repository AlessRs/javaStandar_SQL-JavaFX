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

/**
 *
 * @author Alessandro
 */
public class TablaFxController implements Initializable {
    static LugarDAO DAO;
    
    @FXML
    private TextField idText;
    private TextField responsableText;
    private TextField direccionText;
    private TextField routerText;
    private Button botonInsertar;
    private Button botonActualizar;
    private Button botonSelleccionar;
    private Button botonBorrar;
    private TableView<Lugar> tabla;
    private TableColumn<Lugar, String> idColumna;
    private TableColumn<Lugar, String> responsableColumna;
    private TableColumn<Lugar, String> direccionColumna;
    private TableColumn<Lugar, String> routerColumna;
    
    private ObservableList<Lugar> tablaVisible = FXCollections.observableArrayList();
    
    @FXML
    private void botonInsertarAccion(ActionEvent event) {
        try{
        DAO.insertar(new Lugar(responsableText.getText(),direccionText.getText(),routerText.getText()));
        }catch(SQLException ex){
            Logger.getLogger(JdbcMain.class.getName()).log(Level.INFO,"Valores invalidos",ex);
        }
    }
    @FXML
    private void botonActualizarAccion(ActionEvent event){
        try{
        DAO.actualizar(new Lugar(responsableText.getText(),direccionText.getText(),routerText.getText()));
        }catch(SQLException ex){
            Logger.getLogger(JdbcMain.class.getName()).log(Level.INFO,"Valores invalidos",ex);
        }
    }
    @FXML
    private void botonBorrarAccion(ActionEvent event){
        try{
        DAO.borrar(new Lugar(idText.getText(),responsableText.getText(),direccionText.getText(),routerText.getText()));
        }catch(SQLException ex){
            Logger.getLogger(JdbcMain.class.getName()).log(Level.INFO,"Valores invalidos",ex);
        }
    }
    @FXML
    private void botonSeleccionarAccion(ActionEvent event){
        try{
        tablaVisible = (ObservableList<Lugar>)DAO.obtenerTodos();
        }catch(SQLException ex){
            Logger.getLogger(JdbcMain.class.getName()).log(Level.INFO,"Valores invalidos",ex);
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumna.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        responsableColumna.setCellValueFactory(cellData -> cellData.getValue().nombreResponsableProperty());
        direccionColumna.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        routerColumna.setCellValueFactory(cellData -> cellData.getValue().routerProperty());
        tablaVisible.add(new Lugar("1", "Pedro", "Jujuy 45600", "toplink"));
        tablaVisible.add(new Lugar("2", "Oscar", "Jujuy 45600", "toplink"));
        tabla.setItems(tablaVisible);
        try{
            DAO = new LugarDAOImpl(AdministradorDeConecciones.obtenerConeccion());
        }catch(SQLException ex){
            Logger.getLogger(JdbcMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
