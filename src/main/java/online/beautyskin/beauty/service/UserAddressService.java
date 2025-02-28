package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.UserAddress;
import online.beautyskin.beauty.entity.request.UserAddressRequest;
import online.beautyskin.beauty.repository.UserAddressRepository;
import online.beautyskin.beauty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserAddress> getAddressById(long id) {
        return userAddressRepository.findByIdAndIsDeletedFalse(id);
    }

    public List<UserAddress> getAvailableAddress() {
        return userAddressRepository.findByIsDeletedFalse();
    }

    public UserAddress createAddress(UserAddressRequest userAddressRequest) {
        UserAddress userAddress = new UserAddress();
        userAddress.setCity(userAddressRequest.getCity());
        userAddress.setDistrict(userAddressRequest.getDistrict());
        userAddress.setWard(userAddressRequest.getWard());
        userAddress.setReceiverAddress(userAddressRequest.getReceiverAddress());
        userAddress.setReceiverName(userAddressRequest.getReceiverName());
        userAddress.setReceiverPhone(userAddressRequest.getReceiverPhone());
        userAddress.setUser(userRepository.findById(userAddressRequest.getUserId()));
        userAddress.setDeleted(false);
        return userAddressRepository.save(userAddress);
    }

    public UserAddress updateAddress(long id, UserAddressRequest userAddressRequest) {
        UserAddress userAddress = userAddressRepository.findById(id);
        userAddress.setCity(userAddressRequest.getCity());
        userAddress.setDistrict(userAddressRequest.getDistrict());
        userAddress.setWard(userAddressRequest.getWard());
        userAddress.setReceiverAddress(userAddressRequest.getReceiverAddress());
        userAddress.setReceiverName(userAddressRequest.getReceiverName());
        userAddress.setReceiverPhone(userAddressRequest.getReceiverPhone());
        userAddress.setDeleted(false);
        userAddress.setUser(userRepository.findById(id));
        return userAddressRepository.save(userAddress);
    }

    public UserAddress deleteAddress(long id) {
        UserAddress userAddress = userAddressRepository.findById(id);
        userAddress.setDeleted(true);
        return userAddressRepository.save(userAddress);
    }
}
