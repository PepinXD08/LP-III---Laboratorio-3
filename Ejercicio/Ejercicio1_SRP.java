package martin;

import java.util.List;
import java.util.ArrayList;

public class Ejercicio1_SRP {
	public static class Habitacion {
	    private int numero;
	    private String tipo;
	    private double precio;
	    private String estado;
	    private GestorDisponibilidadHabitacion gestorDisponibilidad;

	    public Habitacion(int numero, String tipo, double precio) {
	        this.numero = numero;
	        this.tipo = tipo;
	        this.precio = precio;
	        this.estado = "DISPONIBLE";
	        this.gestorDisponibilidad = new GestorDisponibilidadHabitacion();
	    }
	    
	    public boolean verificarDisponibilidad(String fechaInicio, String fechaFin) {
	        return gestorDisponibilidad.verificarDisponibilidad(fechaInicio, fechaFin);
	    }

	    public void reservar() {
	        estado = "RESERVADA";
	    }
	    
	    public void liberar() {
	        estado = "DISPONIBLE";
	    }

	    public double calcularPrecio(String temporada, double descuento) {
	        double precioFinal = precio;

	        if (temporada.equals("ALTA")) {
	            precioFinal *= 1.20;
	        }
	        precioFinal -= precioFinal * descuento;
	        return precioFinal;
	    }

	    public String generarInformeOcupacion() {
	        return "Habitación: " + numero + " | Tipo: " + tipo + " | Estado: " + estado;
	    }

	    public int getNumero() {
	        return numero;
	    }

	    public String getEstado() {
	        return estado;
	    }

	    public GestorDisponibilidadHabitacion getGestorDisponibilidad() {
	        return gestorDisponibilidad;
	    }
	}
	
	public static class GestorDisponibilidadHabitacion {
	    private List<Reserva> reservas;
	    
	    public GestorDisponibilidadHabitacion() {
	        reservas = new ArrayList<>();
	    }

	    public boolean verificarDisponibilidad(String fechaInicio, String fechaFin) {
	        for (Reserva reserva : reservas) {
	            if (reserva.seCruzaCon(fechaInicio, fechaFin)) {
	                return false;
	            }
	        }
	        return true;
	    }

	    public void agregarReserva(Reserva reserva) {
	        reservas.add(reserva);
	    }
	} 
	
	public static class Reserva {
	    private String fechaInicio;
	    private String fechaFin;
	    private Cliente cliente;

	    public Reserva(String fechaInicio, String fechaFin, Cliente cliente) {
	        this.fechaInicio = fechaInicio;
	        this.fechaFin = fechaFin;
	        this.cliente = cliente;
	    }

	    public boolean seCruzaCon(String inicio, String fin) {
	        return inicio.compareTo(fechaFin) < 0 && fin.compareTo(fechaInicio) > 0;
	    }
	}
	
	public static class Cliente {
	    private String nombre;

	    public Cliente(String nombre) {
	        this.nombre = nombre;
	    }
	    
	    public String getNombre() {
	        return nombre;
	    }
	}
	
	public static class ControladorReserva {
	    public void crearReserva(Habitacion habitacion, String fechaInicio, String fechaFin, Cliente cliente) {
	        GestorDisponibilidadHabitacion gestor = habitacion.getGestorDisponibilidad();
	        
	        if (gestor.verificarDisponibilidad(fechaInicio, fechaFin)) {
	            Reserva reserva = new Reserva(fechaInicio, fechaFin, cliente);
	            gestor.agregarReserva(reserva);
	            habitacion.reservar();
	            System.out.println("Reserva creada correctamente");
	        } else {
	            System.out.println("La habitación no está disponible");
	        }
	    }
	}
}