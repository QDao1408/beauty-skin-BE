package online.beautyskin.beauty.service;

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

    List<UserAddress> address = new ArrayList<>();

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

    public List<UserAddressResponse> findByUserId(long userId) {
        List<UserAddress> userAddressList = new ArrayList<>();
        if(userRepository.findById(userId) == null) {
                throw new NullUserException("User không tồn tại");
        }
        userAddressList = userAddressRepository.findByUserIdAndIsDeletedFalse(userId);
        if(userAddressList.isEmpty()) {
            throw new NullAddressException("User chưa thêm địa chỉ nhận hàng");
        }

        List<UserAddressResponse> addressesResponse = new ArrayList<>();
        for(UserAddress a : userAddressList) {
            UserAddressResponse addressResponse = new UserAddressResponse();
            addressResponse.setId(a.getId());
            addressResponse.setProvince(a.getProvince());
            addressResponse.setAddress(a.getAddress());
            addressResponse.setName(a.getName());
            addressResponse.setPhone(a.getPhone());
            addressResponse.setWard(a.getWard());
            addressResponse.setDistrict(a.getDistrict());
            addressResponse.setIsDefault(a.isDefault());
            addressesResponse.add(addressResponse);
        }
        return addressesResponse;
    }

    public List<UserAddressResponse> getAvailableAddress() {
        List<UserAddress> addresses = userAddressRepository.findByIsDeletedFalse();
        List<UserAddressResponse> addressesResponse = new ArrayList<>();
        for(UserAddress a : addresses) {
            UserAddressResponse addressResponse = new UserAddressResponse();
            addressResponse.setId(a.getId());
            addressResponse.setProvince(a.getProvince());
            addressResponse.setAddress(a.getAddress());
            addressResponse.setName(a.getName());
            addressResponse.setPhone(a.getPhone());
            addressResponse.setWard(a.getWard());
            addressResponse.setDistrict(a.getDistrict());
            addressResponse.setIsDefault(a.isDefault());
            addressesResponse.add(addressResponse);
        }
        return addressesResponse;
    }

    public boolean hasAddress(long userId) {
        boolean result = true;
        address = userAddressRepository.findByUserIdAndIsDeletedFalse(userId);
        if (address.isEmpty()) {
            result = false;
        }
        return result;
    }

    public UserAddress createAddress(UserAddressRequest userAddressRequest) {

        UserAddress userAddress = new UserAddress();
        userAddress.setProvince(userAddressRequest.getProvince());
        userAddress.setDistrict(userAddressRequest.getDistrict());
        userAddress.setWard(userAddressRequest.getWard());
        userAddress.setAddress(userAddressRequest.getAddress());
        userAddress.setName(userAddressRequest.getName());
        userAddress.setPhone(userAddressRequest.getPhone());
        userAddress.setUser(userRepository.findById(userAddressRequest.getUserId()));
        userAddress.setDefault(userAddressRequest.getIsDefault());
        userAddress.setDeleted(false);

        if (userAddress.isDefault() && hasAddress(userAddress.getUser().getId())) {
            List<UserAddress> a = userAddressRepository.findByUserIdAndIsDeletedFalse(userAddress.getUser().getId());
            for (UserAddress address1 : a) {
                if(address1.getId() != userAddressRequest.getUserId()) {
                    address1.setDefault(false);
                }
            }
        }
        return userAddressRepository.save(userAddress);
    }

    public UserAddressResponse responseAddress(UserAddress address) {
        UserAddressResponse response = new UserAddressResponse();
        response.setProvince(address.getProvince());
        response.setDistrict(address.getDistrict());
        response.setWard(address.getWard());
        response.setAddress(address.getAddress());
        response.setName(address.getName());
        response.setPhone(address.getPhone());
        response.setIsDefault(address.isDefault());
        response.setId(address.getId());

        return response;
    }

    public UserAddress updateAddress(long id, UserAddressRequest userAddressRequest) {
        UserAddress userAddress = userAddressRepository.findById(id);
        userAddress.setProvince(userAddressRequest.getProvince());
        userAddress.setDistrict(userAddressRequest.getDistrict());
        userAddress.setWard(userAddressRequest.getWard());
        userAddress.setAddress(userAddressRequest.getAddress());
        userAddress.setName(userAddressRequest.getName());
        userAddress.setPhone(userAddressRequest.getPhone());
        userAddress.setDefault(userAddressRequest.getIsDefault());

        if (userAddress.isDefault() && hasAddress(userAddress.getUser().getId())) {
            List<UserAddress> a = userAddressRepository.findByUserIdAndIsDeletedFalse(userAddress.getUser().getId());
            for (UserAddress address1 : a) {
                if(address1.getId() != id) {
                    address1.setDefault(false);
                }
            }
        }
        return userAddressRepository.save(userAddress);
    }

    public UserAddress deleteAddress(long id) {
        UserAddress userAddress = userAddressRepository.findById(id);
        userAddress.setDeleted(true);
        return userAddressRepository.save(userAddress);
    }
}
