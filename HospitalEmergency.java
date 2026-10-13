import java.util.ArrayList;
import java.util.List;


public class HospitalEmergency {
    private Nodo cabeza;   
    private Nodo cola;   
    private int tamano;   

    
    public Paciente AtenderSiguientePaciente() {
        if (cabeza == null) return null;      
        Paciente atendido = cabeza.dato;
        cabeza = cabeza.siguiente;                   
        if (cabeza != null) cabeza.anterior = null; 
        else cola = null;                   
        tamano--;
        return atendido;
    }

    
    public void InsertarPorPrioridad(Paciente nuevo) {
        if (nuevo == null)
            throw new IllegalArgumentException("El paciente no puede ser null.");
        Nodo n = new Nodo(nuevo);
        if (cabeza == null) {
            
            cabeza = cola = n;
        } else if (nuevo.nivelPrioridad > cabeza.dato.nivelPrioridad) {
           
            n.siguiente = cabeza;
            cabeza.anterior = n;
            cabeza = n;
        } else {
           
            Nodo actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.dato.nivelPrioridad >= nuevo.nivelPrioridad)
                actual = actual.siguiente;
            n.siguiente = actual.siguiente;
            n.anterior = actual;
            if (actual.siguiente != null) actual.siguiente.anterior = n;
            else cola = n;                  
            actual.siguiente = n;
        }
        tamano++;
    }

    
    public List<String> PacientesEnEsperaCritica(int minutosLimite) {
        if (minutosLimite < 0)
            throw new IllegalArgumentException("El límite de minutos no puede ser negativo.");
        List<String> nombres = new ArrayList<>();
        for (Nodo a = cabeza; a != null; a = a.siguiente)
            if (a.dato.minutosEspera > minutosLimite)
                nombres.add(a.dato.nombre);
        return nombres;
    }

    
    public void ReordenarPorTiempoEspera() {
        if (tamano < 2) return;               
        Nodo actual = cabeza;
        cabeza = cola = null;                 
        while (actual != null) {
            Nodo siguiente = actual.siguiente;   
            actual.anterior = actual.siguiente = null;
            insertarNodoPorTiempo(actual);
            actual = siguiente;
        }
    }

    
    private void insertarNodoPorTiempo(Nodo n) {
        if (cabeza == null) {
            cabeza = cola = n;
        } else if (n.dato.minutosEspera > cabeza.dato.minutosEspera) {
            n.siguiente = cabeza;
            cabeza.anterior = n;
            cabeza = n;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.dato.minutosEspera >= n.dato.minutosEspera)
                actual = actual.siguiente;
            n.siguiente = actual.siguiente;
            n.anterior = actual;
            if (actual.siguiente != null) actual.siguiente.anterior = n;
            else cola = n;
            actual.siguiente = n;
        }
    }

    
    public void mostrar() {
        if (cabeza == null) {
            System.out.println("  (lista vacía)");
            return;
        }
        for (Nodo a = cabeza; a != null; a = a.siguiente)
            System.out.println("  " + a.dato.nombre + " | prioridad " + a.dato.nivelPrioridad+ " | espera " + a.dato.minutosEspera + " min");
    }

    
    public void mostrarInverso() {
        if (cola == null) {
            System.out.println("  (lista vacía)");
            return;
        }
        for (Nodo a = cola; a != null; a = a.anterior)
            System.out.println("  " + a.dato.nombre + " | prioridad " + a.dato.nivelPrioridad  + " | espera " + a.dato.minutosEspera + " min");
    }
}
