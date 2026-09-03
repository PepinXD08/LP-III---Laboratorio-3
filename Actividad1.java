package martin;

public class Vehiculo {
	public static class Vehicle {
		void acelerar() {
			System.out.println("Acelerando...");
		}
	}
	
	public static class Coche extends Vehicle {
		@Override
		public void acelerar() {
			System.out.println("El coche está acelerando usando el motor...");
		}
	}
	
	public static class Bicicleta extends Vehicle {
		@Override
		public void acelerar() {
			System.out.println("La bicicleta está acelerando...");
		}
	}
	
	public static void main(String[] args) {
		Vehicle vehiculo1 = new Coche();
		Vehicle vehiculo2 = new Bicicleta();
		
		vehiculo1.acelerar();
		vehiculo2.acelerar();
	}
}
