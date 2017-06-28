/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registros.tpi.Backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import registros.tpi.Sessions.PersonaFacadeLocal;

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
    public FrmPersona() {
    }
    
    @PostConstruct
    public void init(){
        
         setModelo(new LazyDataModel<Persona>(){

            @Override
            public List<Persona> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List salida = new ArrayList();
                if(personaFacade != null){
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
                if(this.getWrappedData()!=null){
                    List<Persona> lista = (List<Persona>) this.getWrappedData();
                    if(!lista.isEmpty()) {
                        for(Persona get : lista) {
                            if(get.getDui().compareTo(Integer.parseInt(rowKey))==0) {
                                return get;
                            }
                        }
                    }
                }
                return null;
            }       
        });
    
    
    
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
