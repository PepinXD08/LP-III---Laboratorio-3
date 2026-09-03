

import java.util.ArrayList;
import java.util.List;

public class Ejercicio4_ISP {

    public interface ServicioLimpieza {
        void solicitarLimpieza();
    }

    public interface ServicioComida {
        void solicitarComida();
    }

    public interface ServicioLavanderia {
        void solicitarLavanderia();
    }

    public static class HabitacionEstandar implements ServicioLimpieza {
        private int numero;

        public HabitacionEstandar(int numero) {
            this.numero = numero;
        }

        public int getNumero() {
            return numero;
        }

        @Override
        public void solicitarLimpieza() {
            System.out.println("Servicio de limpieza programado para la habitación estándar " + numero);
        }
    }

    public static class HabitacionSuite implements ServicioLimpieza, ServicioComida, ServicioLavanderia {
        private int numero;

        public HabitacionSuite(int numero) {
            this.numero = numero;
        }

        public int getNumero() {
            return numero;
        }

        @Override
        public void solicitarLimpieza() {
            System.out.println("Servicio de limpieza prioritaria programado para la suite " + numero);
        }

        @Override
        public void solicitarComida() {
            System.out.println("Servicio de comida a la habitación en camino para la suite " + numero);
        }

        @Override
        public void solicitarLavanderia() {
            System.out.println("Servicio de lavandería express solicitado para la suite " + numero);
        }
    }

    public static class ControladorServicios {

        public void atenderSolicitudes(Object habitacion) {
            System.out.println("Procesando solicitudes...");

            if (habitacion instanceof ServicioLimpieza) {
                ((ServicioLimpieza) habitacion).solicitarLimpieza();
            }
            
            if (habitacion instanceof ServicioComida) {
                ((ServicioComida) habitacion).solicitarComida();
            }
            
            if (habitacion instanceof ServicioLavanderia) {
                ((ServicioLavanderia) habitacion).solicitarLavanderia();
            }
        }
    }

    public static void main(String[] args) {
        HabitacionEstandar estandar = new HabitacionEstandar(101);
        HabitacionSuite suite = new HabitacionSuite(201);
        
        ControladorServicios controlador = new ControladorServicios();

        System.out.println("--- Habitación 101 (Estándar) ---");
        controlador.atenderSolicitudes(estandar);

        System.out.println("\n--- Habitación 201 (Suite) ---");
        controlador.atenderSolicitudes(suite);
    }
}