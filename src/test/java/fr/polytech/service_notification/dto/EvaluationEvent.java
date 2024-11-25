package fr.polytech.service_notification.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class EvaluationEventTest {

    @Test
    void testGettersAndSetters() {
        // Création d'une instance de EvaluationEvent
        EvaluationEvent event = new EvaluationEvent();

        // Test des setters
        event.setEvaluationType("Performance");
        event.setUserId(123L);
        event.setTarget(2L);
        event.setMessage("Great work on the project!");

        // Test des getters avec AssertJ
        assertThat(event.getEvaluationType()).isEqualTo("Performance");
        assertThat(event.getUserId()).isEqualTo(123L);
        assertThat(event.getTarget()).isEqualTo(2L);
        assertThat(event.getMessage()).isEqualTo("Great work on the project!");
    }

    @Test
    void testDefaultValues() {
        // Création d'une nouvelle instance de EvaluationEvent
        EvaluationEvent event = new EvaluationEvent();

        // Vérification des valeurs par défaut
        assertThat(event.getEvaluationType()).isNull();
        assertThat(event.getUserId()).isNull();
        assertThat(event.getTarget()).isNull();
        assertThat(event.getMessage()).isNull();
    }

    @Test
    void testEquality() {
        // Création de deux instances avec les mêmes valeurs
        EvaluationEvent event1 = new EvaluationEvent();
        event1.setEvaluationType("Feedback");
        event1.setUserId(456L);
        event1.setTarget(2L);
        event1.setMessage("Needs improvement");

        EvaluationEvent event2 = new EvaluationEvent();
        event2.setEvaluationType("Feedback");
        event2.setUserId(456L);
        event2.setTarget(2L);
        event2.setMessage("Needs improvement");

        // Vérification que les deux instances sont équivalentes
        assertThat(event1)
                .usingRecursiveComparison()
                .isEqualTo(event2);
    }
}
