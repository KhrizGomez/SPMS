package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.UserDTO;
import com.app.backend.services.secondary.IUserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.setUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        userDTO.setIdUsuario(id);
        UserDTO updateUser = userService.setUser(userDTO);
        return ResponseEntity.ok(updateUser);
    }
}
