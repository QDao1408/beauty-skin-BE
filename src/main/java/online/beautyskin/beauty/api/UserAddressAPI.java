package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.UserAddress;
import online.beautyskin.beauty.entity.request.UserAddressRequest;
import online.beautyskin.beauty.repository.UserAddressRepository;
import online.beautyskin.beauty.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@SecurityRequirement(name = "api")
public class UserAddressAPI {

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity create(@Valid @RequestBody UserAddressRequest userAddressRequest) {
        return ResponseEntity.ok(userAddressService.createAddress(userAddressRequest));
    }

    @GetMapping("/getAvailabe")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity getAvailable() {
        return ResponseEntity.ok(userAddressService.getAvailableAddress());
    }

//    @GetMapping("/getByUser/{id}")
//    @PreAuthorize("hasAuthority('USER')")
//    public ResponseEntity getByUser(@PathVariable long userId) {
//        return ResponseEntity.ok(userAddressService.getAddressById(userId));
//    }

//    @PutMapping("/update/{id}")
//    @PreAuthorize("hasAuthority('USER')")
//    public ResponseEntity update(@Valid @RequestBody UserAddressRequest userAddressRequest, @PathVariable long id) {
//        return ResponseEntity.ok(userAddressService.updateAddress(id, userAddressRequest));
//    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(userAddressService.deleteAddress(id));
    }

}
