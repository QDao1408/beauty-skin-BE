package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.UserAddress;
import online.beautyskin.beauty.entity.request.UserAddressRequest;
import online.beautyskin.beauty.entity.respone.UserAddressResponse;
import online.beautyskin.beauty.repository.UserAddressRepository;
import online.beautyskin.beauty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import online.beautyskin.beauty.exception.NullUserException;
import online.beautyskin.beauty.exception.NullAddressException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserRepository userRepository;

//    public List<UserAddressResponse> getAddressById(long id) {
//        List<UserAddress> addresses = userAddressRepository.findByIsDeletedFalse();
//        UserAddressResponse addressResponse = new UserAddressResponse();
//        List<UserAddressResponse> addressesResponse = new ArrayList<>();
//        for(UserAddress a : addresses) {
//            if(a.getId() == id) {
//                addressResponse.setAddressId(a.getId());
//                addressResponse.setCity(a.getCity());
//                addressResponse.setDistrict(a.getDistrict());
//                addressResponse.setWard(a.getWard());
//                addressResponse.setUserId(a.getId());
//                addressResponse.setReceiverAddress(a.getReceiverAddress());
//                addressResponse.setReceiverPhone(a.getReceiverPhone());
//                addressResponse.setReceiverName(a.getReceiverName());
//                addressesResponse.add(addressResponse);
//            } else if(userRepository.findById(id) == null) {
//                throw new NullUserException("User không tồn tại");
//            }
//            if(addressesResponse == null) {
//                throw new NullAddressException("User chưa thêm địa chỉ nhận hàng");
//            }
//        }
//        return addressesResponse;
//    }

    public List<UserAddress> findByUserId(long userId) {
        List<UserAddress> userAddressList = new ArrayList<>();
        if(userRepository.findById(userId) == null) {
                throw new NullUserException("User không tồn tại");
        }
        userAddressList = userAddressRepository.findByUserIdAndIsDeletedFalse(userId);
        if(userAddressList.isEmpty()) {
            throw new NullAddressException("User chưa thêm địa chỉ nhận hàng");
        }
        return userAddressList;
    }

    public List<UserAddressResponse> getAvailableAddress() {
        List<UserAddress> addresses = userAddressRepository.findByIsDeletedFalse();
        List<UserAddressResponse> addressesResponse = new ArrayList<>();
        for(UserAddress a : addresses) {
            UserAddressResponse addressResponse = new UserAddressResponse();
            addressResponse.setId(a.getId());
            addressResponse.setCity(a.getCity());
            addressResponse.setAddress(a.getReceiverAddress());
            addressResponse.setName(a.getReceiverName());
            addressResponse.setPhone(a.getReceiverPhone());
            addressResponse.setWard(a.getWard());
            addressResponse.setDistrict(a.getDistrict());
            addressesResponse.add(addressResponse);
        }
        return addressesResponse;
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
        userAddress.setDeleted(userAddressRequest.isDeleted());
        userAddress.setUser(userRepository.findById(id));
        return userAddressRepository.save(userAddress);
    }

    public UserAddress deleteAddress(long id) {
        UserAddress userAddress = userAddressRepository.findById(id);
        userAddress.setDeleted(true);
        return userAddressRepository.save(userAddress);
    }
}
