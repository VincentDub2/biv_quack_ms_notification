package fr.polytech.service_notification.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class EvaluationEventTest {

    @Test
    void testGettersAndSetters() {
        // Création d'une instance de EvaluationEvent
        EvaluationEvent event = new EvaluationEvent();

        // Test des setters
        event.setCommentaire("Great work on the project!");
        event.setEmplacementId(123L);
        event.setHoteId(2L);
        event.setId(1L);
        event.setNote(5);
        event.setReservationId(456L);
        event.setVoyageurId(789L);

        // Test des getters avec AssertJ
        assertThat(event.getId()).isEqualTo(1L);
        assertThat(event.getHoteId()).isEqualTo(2L);
        assertThat(event.getVoyageurId()).isEqualTo(789L);
        assertThat(event.getReservationId()).isEqualTo(456L);
        assertThat(event.getEmplacementId()).isEqualTo(123L);
        assertThat(event.getNote()).isEqualTo(5);
        assertThat(event.getCommentaire()).isEqualTo("Great work on the project!");

    }

    @Test
    void testDefaultValues() {
        // Création d'une nouvelle instance de EvaluationEvent
        EvaluationEvent event = new EvaluationEvent();

        // Vérification des valeurs par défaut
        assertThat(event.getId()).isNull();
        assertThat(event.getHoteId()).isNull();
        assertThat(event.getVoyageurId()).isNull();
        assertThat(event.getReservationId()).isNull();
        assertThat(event.getEmplacementId()).isNull();
        assertThat(event.getNote()).isZero();
        assertThat(event.getCommentaire()).isNull();
    }

    @Test
    void testEquality() {
        // Création de deux instances avec les mêmes valeurs
        EvaluationEvent event1 = new EvaluationEvent();
        event1.setCommentaire("Great work on the project!");
        event1.setEmplacementId(123L);
        event1.setHoteId(2L);
        event1.setId(1L);
        event1.setNote(5);
        event1.setReservationId(456L);
        event1.setVoyageurId(789L);

        EvaluationEvent event2 = new EvaluationEvent();
        event2.setCommentaire("Great work on the project!");
        event2.setEmplacementId(123L);
        event2.setHoteId(2L);
        event2.setId(1L);
        event2.setNote(5);
        event2.setReservationId(456L);
        event2.setVoyageurId(789L);

        // Vérification que les deux instances sont équivalentes
        assertThat(event1)
                .usingRecursiveComparison()
                .isEqualTo(event2);
    }
}
