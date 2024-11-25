package fr.polytech.service_notification;

import fr.polytech.service_notification.model.Notification;
import fr.polytech.service_notification.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ServiceNotificationApplicationTests {

	@Autowired
	private NotificationRepository notificationRepository;

	@Test
	void testSaveAndRetrieveNotification() {
		// Créer une notification
		Notification notification = new Notification();
		notification.setMessage("Test message");
		notification.setRecipient(2L);
		notification.setRead(false);

		// Sauvegarder dans la base de données
		Notification savedNotification = notificationRepository.save(notification);

		// Vérifier que la notification a été sauvegardée et peut être récupérée
		assertThat(savedNotification).isNotNull();
		assertThat(savedNotification.getId()).isNotNull();
		assertThat(savedNotification.getMessage()).isEqualTo("Test message");
		assertThat(savedNotification.getRecipient()).isEqualTo(2L);
	}

}
