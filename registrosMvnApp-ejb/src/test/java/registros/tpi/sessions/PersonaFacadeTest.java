/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registros.tpi.sessions;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import registros.tpi.registrolibreria.Persona;

/**
 *
 * @author sigfrid
 */
public class PersonaFacadeTest {
    
    public PersonaFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    @Test
//    public void testCreate() throws Exception {
//        System.out.println("create");
//        Persona entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        PersonaFacadeLocal instance = (PersonaFacadeLocal)container.getContext().lookup("java:global/classes/PersonaFacade");
//        boolean expResult = false;
//        boolean result = instance.create(entity);
//        assertEquals(expResult, result);
//        container.close();
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testCrear() throws Exception {
        System.out.println("crear");
        Persona entity = null;
        PersonaFacade instance = new PersonaFacade();
        Persona expResult = null;
        Persona result = instance.crear(entity);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testEditar() throws Exception {
        System.out.println("editar");
        Persona entity = null;
        PersonaFacade instance = new PersonaFacade();
        Persona expResult = null;
        Persona result = instance.editar(entity);
        assertEquals(expResult, result);
        
    }

//    @Test
//    public void testEdit() throws Exception {
//        System.out.println("edit");
//        Persona entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        PersonaFacadeLocal instance = (PersonaFacadeLocal)container.getContext().lookup("java:global/classes/PersonaFacade");
//        boolean expResult = false;
//        boolean result = instance.edit(entity);
//        assertEquals(expResult, result);
//        container.close();
//        
//    }
//
//    @Test
//    public void testRemove() throws Exception {
//        System.out.println("remove");
//        Persona entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        PersonaFacadeLocal instance = (PersonaFacadeLocal)container.getContext().lookup("java:global/classes/PersonaFacade");
//        boolean expResult = false;
//        boolean result = instance.remove(entity);
//        assertEquals(expResult, result);
//        container.close();
//        
//    }
//
//    @Test
//    public void testFind() throws Exception {
//        System.out.println("find");
//        Object id = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        PersonaFacadeLocal instance = (PersonaFacadeLocal)container.getContext().lookup("java:global/classes/PersonaFacade");
//        Persona expResult = null;
//        Persona result = instance.find(id);
//        assertEquals(expResult, result);
//        container.close();
//       
//    }
//
//    @Test
//    public void testFindAll() throws Exception {
//        System.out.println("findAll");
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        PersonaFacadeLocal instance = (PersonaFacadeLocal)container.getContext().lookup("java:global/classes/PersonaFacade");
//        List<Persona> expResult = null;
//        List<Persona> result = instance.findAll();
//        assertEquals(expResult, result);
//        container.close();
//        
//    }
//
//    @Test
//    public void testFindRange() throws Exception {
//        System.out.println("findRange");
//        int[] range = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        PersonaFacadeLocal instance = (PersonaFacadeLocal)container.getContext().lookup("java:global/classes/PersonaFacade");
//        List<Persona> expResult = null;
//        List<Persona> result = instance.findRange(range);
//        assertEquals(expResult, result);
//        container.close();
//        
//    }
//
//    @Test
//    public void testCount() throws Exception {
//        System.out.println("count");
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        PersonaFacadeLocal instance = (PersonaFacadeLocal)container.getContext().lookup("java:global/classes/PersonaFacade");
//        int expResult = 0;
//        int result = instance.count();
//        assertEquals(expResult, result);
//        container.close();
//        
//    }
    
}
