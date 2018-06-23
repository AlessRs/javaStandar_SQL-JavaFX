package jdbc;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lugar {

    private final StringProperty id;
    private final StringProperty nombreResponsable;
    private final StringProperty direccion;
    private final StringProperty router;
    
    public Lugar (){
        this("Juan Perez", "Calle Falsa 123", "Default");
    }

    public Lugar(String nombreResponsable, String direccion, String router) {
        this.id = new SimpleStringProperty("-");
        this.nombreResponsable = new SimpleStringProperty(nombreResponsable);
        this.direccion =new SimpleStringProperty( direccion);
        this.router =new SimpleStringProperty( router);
    }

    public Lugar(String id, String nombreResponsable, String direccion, String router) {
        this.id = new SimpleStringProperty(id);
        this.nombreResponsable = new SimpleStringProperty(nombreResponsable);
        this.direccion =new SimpleStringProperty( direccion);
        this.router =new SimpleStringProperty( router);
    }

    public StringProperty nombreResponsableProperty() {
        return nombreResponsable;
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public StringProperty routerProperty() {
        return router;
    }

    public StringProperty idProperty() {
        return id;
    }
    
    public String getNombreResponsable() {
        return nombreResponsable.get();
    }

    public String getDireccion() {
        return direccion.get();
    }

    public String getRouter() {
        return router.get();
    }

    public String getId() {
        return id.get();
    }
    
    public void getNombreResponsable(String s) {
        nombreResponsable.set(s);
    }

    public void getDireccion(String s) {
        direccion.set(s);
    }

    public void getRouter(String s) {
        router.set(s);
    }

    public void getId(String s) {
        id.set(s);
    }
    

    @Override
    public String toString() {
        return "Lugar{" + "nombreResponsable=" + nombreResponsable + ", direccion=" + direccion + ", router=" + router + '}';
    }
    
    
    
    
    
}