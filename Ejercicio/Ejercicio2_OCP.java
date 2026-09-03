package martin;

import java.time.LocalDateTime;
import java.time.Duration;

public class Ejercicio2_OCP {
	public interface PoliticaCancelacion {
	    boolean puedeCancelar(Reserva reserva);
	    double calcularPenalizacion(Reserva reserva);
	}

	public static class PoliticaCancelacionFlexible implements PoliticaCancelacion {
	    @Override
	    public boolean puedeCancelar(Reserva reserva) {
	        LocalDateTime ahora = LocalDateTime.now();
	        LocalDateTime checkIn = reserva.getFechaCheckIn();
	        long horas = Duration.between(ahora, checkIn).toHours();
	        return horas >= 24;
	    }
	    
	    @Override
	    public double calcularPenalizacion(Reserva reserva) {
	        return 0;
	    }
	}

	public static class PoliticaCancelacionModerada implements PoliticaCancelacion {
	    @Override
	    public boolean puedeCancelar(Reserva reserva) {
	        LocalDateTime ahora = LocalDateTime.now();
	        LocalDateTime checkIn = reserva.getFechaCheckIn();
	        long horas = Duration.between(ahora, checkIn).toHours();
	        return horas >= 72;
	    }
	    
	    @Override
	    public double calcularPenalizacion(Reserva reserva) {
	        return reserva.getPrecio() * 0.50;
	    }
	}

	public static class PoliticaCancelacionEstricta implements PoliticaCancelacion {
	    @Override
	    public boolean puedeCancelar(Reserva reserva) {
	        LocalDateTime ahora = LocalDateTime.now();
	        LocalDateTime fechaReserva = reserva.getFechaReserva();
	        return ahora.equals(fechaReserva) || ahora.isBefore(fechaReserva);
	    }

	    @Override
	    public double calcularPenalizacion(Reserva reserva) {
	        return 0;
	    }
	}

	public static class Reserva {
	    private LocalDateTime fechaReserva;
	    private LocalDateTime fechaCheckIn;
	    private Cliente cliente;
	    private PoliticaCancelacion politicaCancelacion;
	    private double precio;
	    private boolean cancelada;

	    public Reserva(String fechaCheckIn, Cliente cliente,PoliticaCancelacion politicaCancelacion) {
	        this.fechaReserva = LocalDateTime.now();
	        this.fechaCheckIn = LocalDateTime.parse(fechaCheckIn);
	        this.cliente = cliente;
	        this.politicaCancelacion = politicaCancelacion;
	        this.precio = 200.0;
	        this.cancelada = false;
	    }

	    public void cancelar() {
	        if (politicaCancelacion.puedeCancelar(this)) {
	            double penalizacion = politicaCancelacion.calcularPenalizacion(this);
	            cancelada = true;
	            System.out.println("Reserva cancelada correctamente");
	            
	            if (penalizacion > 0) {
	                System.out.println("Penalización: S/ " + penalizacion);
	            } else {
	                System.out.println("Cancelación sin penalización");
	            }
	        } else {
	            System.out.println("La reserva no puede ser cancelada según la política");
	        }
	    }

	    public LocalDateTime getFechaReserva() {
	        return fechaReserva;
	    }

	    public LocalDateTime getFechaCheckIn() {
	        return fechaCheckIn;
	    }

	    public double getPrecio() {
	        return precio;
	    }

	    public Cliente getCliente() {
	        return cliente;
	    }

	    public boolean isCancelada() {
	        return cancelada;
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
}