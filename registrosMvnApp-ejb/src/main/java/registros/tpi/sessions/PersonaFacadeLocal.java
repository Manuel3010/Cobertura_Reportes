/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registros.tpi.sessions;

import java.util.List;
import javax.ejb.Local;
import registros.tpi.registrolibreria.Persona;

/**
 *
 * @author manuel
 */
@Local
public interface PersonaFacadeLocal {

    boolean create(Persona persona);

    boolean edit(Persona persona);

    boolean remove(Persona persona);

    Persona find(Object id);

    List<Persona> findAll();

    List<Persona> findRange(int[] range);

    int count();
    
}
