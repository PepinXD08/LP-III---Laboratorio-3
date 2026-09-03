package martin;

public class Main {
	public static class NotificationManagerEmail {
	    public void sendEmail(String message) {
	        System.out.println("Enviando correo: " + message);
	    }
	}
	
	public static class NotificacionManagerSMS {
	    public void sendSMS(String message) {
	        System.out.println("Enviando SMS: " + message);
	    }
	}
	
	public static void main(String[] args) {
		NotificationManagerEmail email = new NotificationManagerEmail();
		NotificacionManagerSMS sms = new NotificacionManagerSMS();
		
		email.sendEmail("Hola");
		sms.sendSMS("Buenas");
	}
}
