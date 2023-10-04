package com.example.spring.service;

import com.example.spring.models.Address;
import com.example.spring.repository.AddressRepository;
import com.example.spring.service.exceptions.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonService personService;
    Address addressEntity = new Address();
    @Transactional(rollbackFor = Exception.class)
    public Address saveAddress(Address address, Long idPerson) {
        if (personService.checksIfPersonExists(idPerson)) {
            if (addressEntity.getMain() == false) {
                addressEntity.setMain(false);
            }

            return addressRepository.save(address);
        }
       throw new NotFoundException("Error saving address");
    }
    @Transactional(readOnly = true)
    public Address findById(Long id) {
        Optional<Address> address;
        address = addressRepository.findById(id);
        return address.get();
    }
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        List<Address> address = new ArrayList<>();
        address.stream().forEach(addressEntity -> addressRepository.findAll());
        return address;
    }
    @Transactional(rollbackFor = Exception.class)
    public Address update(Long id, Address address) {
        addressEntity = addressRepository.getReferenceById(id);
        updateData(addressEntity, address);
        return addressRepository.save(addressEntity);
    }
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if (addressEntity.getId() != null) {
            addressRepository.deleteById(id);
        }
        throw new NotFoundException("Address not found");
    }
    @Transactional(readOnly = true)
    public void updateData(Address entity, Address address) {
        entity.setCity(address.getCity());
        entity.setNumber(address.getNumber());
        entity.setPublicPlace(address.getPublicPlace());
        entity.setZipCode(address.getZipCode());
    }

}
