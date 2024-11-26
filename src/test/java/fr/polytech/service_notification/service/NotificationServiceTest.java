package fr.polytech.service_notification.service;

import fr.polytech.service_notification.model.Notification;
import fr.polytech.service_notification.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @InjectMocks
    private NotificationService service;

    @Mock
    private NotificationRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Préparation des données simulées
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setMessage("Message 1");

        Notification notification2 = new Notification();
        notification2.setId(2L);
        notification2.setMessage("Message 2");

        when(repository.findAll()).thenReturn(Arrays.asList(notification1, notification2));

        // Appel de la méthode
        List<Notification> notifications = service.findAll();

        // Vérifications
        assertThat(notifications).hasSize(2);
        assertThat(notifications).contains(notification1, notification2);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Préparation des données simulées
        Notification notification = new Notification();
        notification.setMessage("Test Save");

        when(repository.save(notification)).thenReturn(notification);

        // Appel de la méthode
        Notification savedNotification = service.save(notification);

        // Vérifications
        assertThat(savedNotification).isEqualTo(notification);
        verify(repository, times(1)).save(notification);
    }

    @Test
    void testFindById_Found() {
        // Préparation des données simulées
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Test FindById");

        when(repository.findById(1L)).thenReturn(Optional.of(notification));

        // Appel de la méthode
        Notification foundNotification = service.findById(1L);

        // Vérifications
        assertThat(foundNotification).isNotNull();
        assertThat(foundNotification.getId()).isEqualTo(1L);
        assertThat(foundNotification.getMessage()).isEqualTo("Test FindById");
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Préparation des données simulées
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Appel de la méthode
        Notification foundNotification = service.findById(1L);

        // Vérifications
        assertThat(foundNotification).isNull();
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindByRecipientId() {
        // Préparation des données simulées
        Notification notification = new Notification();
        notification.setRecipient(123L);
        notification.setMessage("Test FindByRecipientId");

        when(repository.findByRecipientId(123L)).thenReturn(Collections.singletonList(notification));

        // Appel de la méthode
        List<Notification> foundNotification = service.findByRecipientId(123L);

        // Vérifications
        assertThat(foundNotification).isNotNull();
        assertThat(foundNotification.getFirst().getRecipient()).isEqualTo(123L);
        verify(repository, times(1)).findByRecipientId(123L);
    }

    @Test
    void testDelete() {
        // Appel de la méthode
        service.delete(1L);

        // Vérifications
        verify(repository, times(1)).deleteById(1L);
    }
}
