package fr.polytech.service_notification.clients;

import fr.polytech.service_notification.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-users")
public interface UserClient {
    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable Long id);
}
