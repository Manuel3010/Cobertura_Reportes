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
import java.util.Objects;
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
import registros.tpi.sessions.EstudianteFacadeLocal;
import registros.tpi.registrolibreria.Estudiante;
import registros.tpi.registrolibreria.Persona;
import registros.tpi.sessions.PersonaFacadeLocal;

/**
 *
 * @author manuel
 */
@Named(value = "frmEstudiante")
@ViewScoped
public class FrmEstudiante implements Serializable{

   private LazyDataModel<Estudiante> modelo;
   private Estudiante registroEstudiante;
   private List<Persona> personas;
   
   @EJB
   private EstudianteFacadeLocal estudianteFacade;
   @EJB
   private PersonaFacadeLocal personaFacade;
    /**
     * Creates a new instance of FrmEstudiante
     */
  /*  public FrmEstudiante() {
    }
    */
     @PostConstruct
    public void init(){
        this.personas=this.personaFacade.findAll();
         setModelo(new LazyDataModel<Estudiante>(){

            @Override
            public List<Estudiante> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List salida = new ArrayList();
                if(estudianteFacade != null){
                    this.setRowCount(estudianteFacade.count());
                    int[] rango = new int[2];
                    rango[0] = first;
                    rango[1] = pageSize;
                    salida = estudianteFacade.findRange(rango);
                }
                return salida;
            }

            @Override
            public Object getRowKey(Estudiante object) {
                return object.getCarnet();
            }

            @Override
            public Estudiante getRowData(String rowKey) {
                if(this.getWrappedData()!=null){
                    List<Estudiante> lista = (List<Estudiante>) this.getWrappedData();
                    if(!lista.isEmpty()) {
                        for(Estudiante get : lista) {
                            if(get.getCarnet().compareTo((rowKey))==0) {
                                return get;
                            }
                        }
                    }
                }
                return null;
            }       
        });
    
    
    
    }
    
    public Integer getPersonaSeleccionado(){
     if(registroEstudiante!= null){
            if(registroEstudiante.getDui()!= null){
                return this.registroEstudiante.getDui().getDui();
            } else {
                return 0;
            }         
        } else {
            return 0;
        }
    }
    
    public void setPersonaSeleccionado(Integer dui){
        if(dui >= 0 && !this.personas.isEmpty()){
            for(Persona p : this.getPersonas()) {
                if(Objects.equals(p.getDui(), dui)) {
                    if(this.registroEstudiante.getDui() != null) {
                        this.registroEstudiante.getDui().setDui(dui);
                    } else {
                        this.registroEstudiante.setDui(p);
                    }
                }
            }
        }
    
    }
    
     public void limpiar(){
          RequestContext.getCurrentInstance().reset("vistaEditarEstudiante");
            this.registroEstudiante=new Estudiante();
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
            
            if(this.registroEstudiante != null && this.estudianteFacade != null){
                
                boolean resultado =this.estudianteFacade.create(registroEstudiante); 
        
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
            boolean resultado = this.estudianteFacade.edit(registroEstudiante); 
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
            if(this.registroEstudiante != null && this.estudianteFacade != null){
                boolean resultado = this.estudianteFacade.remove(registroEstudiante);
                //editar=!resultado;
                FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, resultado?"Eliminado con exito":"Error", null);
                FacesContext.getCurrentInstance().addMessage(null, msj);
                limpiar();
                
            }
        } catch (Exception e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
    }

    public LazyDataModel<Estudiante> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Estudiante> modelo) {
        this.modelo = modelo;
    }

    public Estudiante getRegistroEstudiante() {
        return registroEstudiante;
    }

    public void setRegistroEstudiante(Estudiante registroEstudiante) {
        this.registroEstudiante = registroEstudiante;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
    
}
