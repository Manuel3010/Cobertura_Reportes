/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registros.tpi.sessions;

import java.util.List;
import javax.ejb.Local;
import registros.tpi.registrolibreria.Estudiante;

/**
 *
 * @author manuel
 */
@Local
public interface EstudianteFacadeLocal {

    boolean create(Estudiante estudiante);

    boolean edit(Estudiante estudiante);

    boolean remove(Estudiante estudiante);

    Estudiante find(Object id);

    List<Estudiante> findAll();

    List<Estudiante> findRange(int[] range);

    int count();
    
}
