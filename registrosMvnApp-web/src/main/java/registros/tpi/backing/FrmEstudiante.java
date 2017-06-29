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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import registros.tpi.sessions.EstudianteFacadeLocal;
import registros.tpi.registrolibreria.Estudiante;

/**
 *
 * @author manuel
 */
@Named(value = "frmEstudiante")
@ViewScoped
public class FrmEstudiante implements Serializable{

   private LazyDataModel<Estudiante> modelo;
   private Estudiante registroEstudiante;
   
   @EJB
   private EstudianteFacadeLocal estudianteFacade;
       
    /**
     * Creates a new instance of FrmEstudiante
     */
    public FrmEstudiante() {
    }
    
     @PostConstruct
    public void init(){
        
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
    
}
