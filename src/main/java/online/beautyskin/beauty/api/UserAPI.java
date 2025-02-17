package online.beautyskin.beauty.api;

import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.UserUpdateRequest;
import online.beautyskin.beauty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserAPI {
    @Autowired
    private UserService userService;

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody UserUpdateRequest user) {
        User user1 = userService.update(user,id);
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        User u = userService.findById(id);
        userService.delete(u);
        return ResponseEntity.ok(u);
    }

    @PutMapping("/inActive/{id}")
    public ResponseEntity inActive(@PathVariable long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }






}
