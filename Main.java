
public class Main {

    private static HospitalEmergency crearListaDePrueba() {
        HospitalEmergency lista = new HospitalEmergency();
        lista.InsertarPorPrioridad(new Paciente("HC-001", "Ana", 2, 30));
        lista.InsertarPorPrioridad(new Paciente("HC-002", "Luis", 5, 10));
        lista.InsertarPorPrioridad(new Paciente("HC-003", "Marta", 1, 50));
        lista.InsertarPorPrioridad(new Paciente("HC-004", "Pedro", 4, 20));
        return lista;
    }

    public static void main(String[] args) {

        System.out.println("=== CASOS POSITIVOS ===");

        
        System.out.println("Caso 1: insertar pacientes con prioridades 2, 5, 1, 4");
        HospitalEmergency lista = new HospitalEmergency();
        lista.InsertarPorPrioridad(new Paciente("HC-001", "Ana", 2, 30));
        lista.InsertarPorPrioridad(new Paciente("HC-002", "Luis", 5, 10));
        lista.InsertarPorPrioridad(new Paciente("HC-003", "Marta", 1, 50));
        lista.InsertarPorPrioridad(new Paciente("HC-004", "Pedro", 4, 20));
        System.out.println(" Recorrido hacia adelante (siguiente):");
        lista.mostrar();          // esperado: Luis (5), Pedro (4), Ana (2), Marta (1)
        System.out.println(" Recorrido hacia atrás (anterior):");
        lista.mostrarInverso();   // esperado: Marta (1), Ana (2), Pedro (4), Luis (5)

        
        System.out.println("Caso 2: verificar orden de atención");
        HospitalEmergency paraAtender = crearListaDePrueba();
        int turno = 1;
        Paciente atendido = paraAtender.AtenderSiguientePaciente();
        while (atendido != null) {
            System.out.println("  Turno " + turno + ": " + atendido.nombre + " (prioridad " + atendido.nivelPrioridad + ")");
            turno++;
            atendido = paraAtender.AtenderSiguientePaciente();
        }
        
        System.out.println(" Lista después de atender a todos:");
        paraAtender.mostrar();    

        
        System.out.println("Caso 3: verificar tiempos de espera (límite 25 min)");
        HospitalEmergency paraCriticos = crearListaDePrueba();
        System.out.println("  Críticos: " + paraCriticos.PacientesEnEsperaCritica(25));   // esperado: [Ana, Marta]

        
        System.out.println("Caso 4: reordenamiento por tiempo de espera");
        HospitalEmergency paraReordenar = crearListaDePrueba();
        paraReordenar.ReordenarPorTiempoEspera();
        System.out.println(" Recorrido hacia adelante (siguiente):");
        paraReordenar.mostrar();          
        System.out.println(" Recorrido hacia atrás (anterior):");
        paraReordenar.mostrarInverso();   

        System.out.println("\n=== CASOS NEGATIVOS ===");
        HospitalEmergency vacia = new HospitalEmergency();

        
        System.out.println("Caso negativo 1: insertar un paciente null");
        try {
            vacia.InsertarPorPrioridad(null);
        } catch (IllegalArgumentException e) {
            System.out.println("  Error controlado: " + e.getMessage());
        }

        
        System.out.println("Caso negativo 2: paciente con prioridad inválida (7)");
        try {
            vacia.InsertarPorPrioridad(new Paciente("HC-005", "Carlos", 7, 15));
        } catch (IllegalArgumentException e) {
            System.out.println("  Error controlado: " + e.getMessage());
        }

       
        System.out.println("Caso negativo 3: atender siguiente paciente con la lista vacía");
        Paciente resultado = vacia.AtenderSiguientePaciente();
        System.out.println("  Resultado: " + (resultado == null ? "no hay pacientes para atender" : resultado.nombre));
    }
}
