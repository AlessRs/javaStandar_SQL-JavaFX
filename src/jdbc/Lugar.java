package jdbc;

import java.util.Objects;
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
    
    public void setNombreResponsable(String s) {
        nombreResponsable.set(s);
    }

    public void setDireccion(String s) {
        direccion.set(s);
    }

    public void setRouter(String s) {
        router.set(s);
    }

    public void setId(String s) {
        id.set(s);
    }
    

    @Override
    public String toString() {
        return "Lugar{" + "nombreResponsable=" + nombreResponsable + ", direccion=" + direccion + ", router=" + router + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    public Lugar clone(){
        return new Lugar(getId(),getNombreResponsable(), getDireccion(),getRouter());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lugar other = (Lugar) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
