package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.service.UserSkinProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skin-profile")
@SecurityRequirement(name = "api")
public class UserSkinProfileAPI {

    @Autowired
    private UserSkinProfileService userSkinProfileService;

    @GetMapping("/gets")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userSkinProfileService.getAll());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity getById(@PathVariable long id) {
        return ResponseEntity.ok(userSkinProfileService.getById(id));
    }

    @GetMapping("/get-by-user/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity getByUserId(@PathVariable long id) {
        return ResponseEntity.ok(userSkinProfileService.getByUserId(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity create(int skinPoint) {
        return ResponseEntity.ok(userSkinProfileService.create(skinPoint));
    }
}
