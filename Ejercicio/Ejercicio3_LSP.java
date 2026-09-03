

import java.util.List;
import java.util.ArrayList;

public class Ejercicio3_LSP {

    public static abstract class Habitacion {
        protected int numero;
        protected double precioBase;
        protected boolean estaDisponible;

        public Habitacion(int numero, double precioBase) {
            this.numero = numero;
            this.precioBase = precioBase;
            this.estaDisponible = true;
        }

        public int getNumero() {
            return numero;
        }

        public boolean isDisponible() {
            return estaDisponible;
        }

        public abstract double calcularPrecioFinal();
        
        public abstract String obtenerDescripcionServicios();
    }

    public static class HabitacionEstandar extends Habitacion {
        public HabitacionEstandar(int numero, double precioBase) {
            super(numero, precioBase);
        }

        @Override
        public double calcularPrecioFinal() {
            return precioBase;
        }

        @Override
        public String obtenerDescripcionServicios() {
            return "Servicios básicos: Cama doble, TV, Baño privado.";
        }
    }

    public static class HabitacionSuite extends Habitacion {
        private double costoServicioExtra;

        public HabitacionSuite(int numero, double precioBase, double costoServicioExtra) {
            super(numero, precioBase);
            this.costoServicioExtra = costoServicioExtra;
        }

        @Override
        public double calcularPrecioFinal() {
            return precioBase + costoServicioExtra;
        }

        @Override
        public String obtenerDescripcionServicios() {
            return "Servicios Premium: Jacuzzi, Minibar, Vista panorámica.";
        }
    }

    public static class ControladorReservas {
        
        public double procesarCobroHabitaciones(List<Habitacion> habitaciones) {
            double total = 0;
            
            for (Habitacion habitacion : habitaciones) {
                if (habitacion.isDisponible()) {
                    double precio = habitacion.calcularPrecioFinal();
                    total += precio;
                    
                    System.out.println("Habitación: " + habitacion.getNumero() + 
                                       " | Tipo: " + habitacion.getClass().getSimpleName() +
                                       " | Costo: S/ " + precio);
                    System.out.println("   -> " + habitacion.obtenerDescripcionServicios());
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {
        List<Habitacion> misHabitaciones = new ArrayList<>();
        misHabitaciones.add(new HabitacionEstandar(101, 150.0));
        misHabitaciones.add(new HabitacionSuite(201, 300.0, 50.0));
        misHabitaciones.add(new HabitacionEstandar(102, 150.0));

        ControladorReservas controlador = new ControladorReservas();
        
        System.out.println("--- PROCESANDO COBROS DE HABITACIONES ---");
        double granTotal = controlador.procesarCobroHabitaciones(misHabitaciones);
        System.out.println("Total a pagar: S/ " + granTotal);
    }
}