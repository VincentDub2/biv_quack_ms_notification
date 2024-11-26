package fr.polytech.service_notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.service_notification.model.Notification;
import fr.polytech.service_notification.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Notification notification;

    @BeforeEach
    void setUp() {
        notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Test message");
        notification.setRecipient(2L);
        notification.setRead(false);
    }

    @Test
    void testGetAllNotifications() throws Exception {
        when(service.findAll()).thenReturn(Collections.singletonList(notification));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].message").value("Test message"));
    }

    @Test
    void testGetNotificationById() throws Exception {
        when(service.findById(1L)).thenReturn(notification);

        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Test message"))
                .andExpect(jsonPath("$.recipient").value(2L));
    }

    @Test
    void testGetNotificationByRecipientId() throws Exception {

        when(service.findByRecipientId(2L)).thenReturn(Collections.singletonList(notification));

        //This mothode return a list of notification
        mockMvc.perform(get("/recipient/2"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(1))
                        .andExpect(jsonPath("$[0].message").value("Test message"));

    }

    @Test
    void testCreateNotification() throws Exception {
        when(service.save(any(Notification.class))).thenReturn(notification);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notification)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Test message"))
                .andExpect(jsonPath("$.recipient").value(2L));
    }

    @Test
    void testDeleteNotification() throws Exception {
        Mockito.doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/1"))
                .andExpect(status().isOk());
    }
}
