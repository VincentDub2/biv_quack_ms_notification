package fr.polytech.service_notification.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationTest {

    @Test
    void testGettersAndSetters() {
        // Création d'une instance de Notification
        Notification notification = new Notification();

        // Test des setters
        notification.setId(1L);
        notification.setMessage("Test Message");
        notification.setRecipient(123L);
        notification.setRead(true);

        // Test des getters avec AssertJ
        assertThat(notification.getId()).isEqualTo(1L);
        assertThat(notification.getMessage()).isEqualTo("Test Message");
        assertThat(notification.getRecipient()).isEqualTo(123L);
        assertThat(notification.isRead()).isTrue();
    }

    @Test
    void testDefaultValues() {
        // Création d'une nouvelle instance de Notification
        Notification notification = new Notification();

        // Vérification des valeurs par défaut
        assertThat(notification.getId()).isNull();
        assertThat(notification.getMessage()).isNull();
        assertThat(notification.getRecipient()).isNull();
        assertThat(notification.isRead()).isFalse();
    }

    @Test
    void testEquality() {
        // Création de deux instances de Notification avec les mêmes valeurs
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setMessage("Test Message");
        notification1.setRecipient(123L);
        notification1.setRead(false);

        Notification notification2 = new Notification();
        notification2.setId(1L);
        notification2.setMessage("Test Message");
        notification2.setRecipient(123L);
        notification2.setRead(false);

        // Vérification que les deux instances sont équivalentes
        assertThat(notification1)
                .usingRecursiveComparison()
                .isEqualTo(notification2);
    }
}
