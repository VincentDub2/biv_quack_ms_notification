package fr.polytech.service_notification.clients;

import fr.polytech.service_notification.dto.EmplacementResponse;
import fr.polytech.service_notification.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-emplacements")
public interface EmplacementClient {
    @GetMapping("/{id}")
    EmplacementResponse getEmplacementById(@PathVariable Long id);
}
