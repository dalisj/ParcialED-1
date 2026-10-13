public class Paciente {
    String historialClinico;
    String nombre;
    int nivelPrioridad;   
    int minutosEspera;

    public Paciente(String historialClinico, String nombre, int nivelPrioridad, int minutosEspera) {
        
        if (historialClinico == null || historialClinico.trim().isEmpty())
            throw new IllegalArgumentException("El historial clínico no puede estar vacío.");
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (nivelPrioridad < 1 || nivelPrioridad > 5)
            throw new IllegalArgumentException("El nivel de prioridad debe estar entre 1 y 5.");
        if (minutosEspera < 0)
            throw new IllegalArgumentException("Los minutos de espera no pueden ser negativos.");

        this.historialClinico = historialClinico;
        this.nombre = nombre;
        this.nivelPrioridad = nivelPrioridad;
        this.minutosEspera = minutosEspera;
    }

    public String getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(String historialClinico) {
        this.historialClinico = historialClinico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelPrioridad() {
        return nivelPrioridad;
    }

    public void setNivelPrioridad(int nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }

    public int getMinutosEspera() {
        return minutosEspera;
    }

    public void setMinutosEspera(int minutosEspera) {
        this.minutosEspera = minutosEspera;
    }

    
     
}
