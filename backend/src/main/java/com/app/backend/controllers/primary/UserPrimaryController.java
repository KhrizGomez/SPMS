package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.UserPrimaryDTO;
import com.app.backend.services.primary.IUserPrimaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/primary/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserPrimaryController {
    private final IUserPrimaryService userService;

    @GetMapping
    public ResponseEntity<List<UserPrimaryDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPrimaryDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<UserPrimaryDTO> getByCardId(@PathVariable String cardId) {
        return ResponseEntity.ok(userService.getUserByCardId(cardId));
    }

    @PostMapping
    public ResponseEntity<UserPrimaryDTO> create(@RequestBody UserPrimaryDTO userDTO) {
        UserPrimaryDTO savedUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPrimaryDTO> update(@PathVariable Integer id, @RequestBody UserPrimaryDTO userDTO) {
        UserPrimaryDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
