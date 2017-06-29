/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registros.tpi.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import registros.tpi.sessions.PersonaFacadeLocal;

import registros.tpi.registrolibreria.Persona;

/**
 *
 * @author manuel
 */
@Named(value = "frmPersona")
@ViewScoped
public class FrmPersona implements Serializable {

    private LazyDataModel<Persona> modelo;

    @EJB
    private PersonaFacadeLocal personaFacade;

    private Persona registroPersona;

    /**
     * Creates a new instance of FrmPersona
     */
    /*  public FrmPersona() {
    }*/
    @PostConstruct
    public void init() {

        setModelo(new LazyDataModel<Persona>() {

            @Override
            public List<Persona> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List salida = new ArrayList();
                if (personaFacade != null) {
                    this.setRowCount(personaFacade.count());
                    int[] rango = new int[2];
                    rango[0] = first;
                    rango[1] = pageSize;
                    salida = personaFacade.findRange(rango);
                }
                return salida;
            }

            @Override
            public Object getRowKey(Persona object) {
                return object.getDui();
            }

            @Override
            public Persona getRowData(String rowKey) {
                if (this.getWrappedData() != null) {
                    List<Persona> lista = (List<Persona>) this.getWrappedData();
                    if (!lista.isEmpty()) {
                        for (Persona get : lista) {
                            if (get.getDui().compareTo(Integer.parseInt(rowKey)) == 0) {
                                return get;
                            }
                        }
                    }
                }
                return null;
            }
        });

    }

    public void limpiar(){
          RequestContext.getCurrentInstance().reset("vistaEditar");
            this.registroPersona = new Persona();
    }
    
    
     public void btnNuevoAction(ActionEvent ae) {
        //editar=false;
         try{
            limpiar();
         
        }catch(Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
    }
     
   
     
      public void btnGuardarAction(ActionEvent ae){
        try {
            
            if(this.registroPersona != null && this.personaFacade != null){
                
                boolean resultado =this.personaFacade.create(registroPersona); //this.trfl.create(tipo);
        
                FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, resultado?"Creado con exito":"Error", null);
                //this.agregar = !resultado;
                FacesContext.getCurrentInstance().addMessage(null, msj);
                limpiar();
            }
        } catch (Exception e) {
           Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
     
      }
     
     
      public void btnModificarAction(ActionEvent ae){
        try{
            boolean resultado = this.personaFacade.edit(registroPersona); 
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, resultado?"Modificado con exito":"Error", null);
            //this.editar = resultado;
            FacesContext.getCurrentInstance().addMessage(null, msj);
            limpiar();
        }catch(Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
    }
                   
        public void btnEliminarAction(ActionEvent ae) {
        try {
            if(this.registroPersona != null && this.personaFacade != null){
                boolean resultado = this.personaFacade.remove(registroPersona);
                //editar=!resultado;
                FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, resultado?"Eliminado con exito":"Error", null);
                FacesContext.getCurrentInstance().addMessage(null, msj);
                limpiar();
                
            }
        } catch (Exception e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
    }

    
    
    
    
    public LazyDataModel<Persona> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Persona> modelo) {
        this.modelo = modelo;
    }

    public Persona getRegistroPersona() {
        return registroPersona;
    }

    public void setRegistroPersona(Persona registroPersona) {
        this.registroPersona = registroPersona;
    }

}
