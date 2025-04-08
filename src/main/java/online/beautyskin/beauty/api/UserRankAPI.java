package online.beautyskin.beauty.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.service.UserRank;


@RestController
@RequestMapping("/api/user-rank")
@SecurityRequirement(name = "api")
public class UserRankAPI {
    
    @Autowired
    private UserRank userRank;

    @GetMapping("/gets")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userRank.getAll());
    }

    @GetMapping("/get-user-by-rank/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getUserByRank(@PathVariable long id) {
        return ResponseEntity.ok(userRank.getUserByRank(id));
    }

    @PostMapping("/create-rank")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity createRank(@RequestBody online.beautyskin.beauty.entity.UserRank userRank) {
        return ResponseEntity.ok(this.userRank.createRank(userRank));
    }
    
    
}
