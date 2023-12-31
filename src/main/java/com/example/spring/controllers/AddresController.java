package com.example.spring.controllers;

import com.example.spring.dtos.AddressDTO;
import com.example.spring.models.Address;
import com.example.spring.models.Person;
import com.example.spring.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/addres")
public class AddresController {

    @Autowired
    AddressService addressService;

    @PostMapping("/{idPerson}")
    public ResponseEntity<AddressDTO> saveAddres(@PathVariable Long idPerson, @RequestBody AddressDTO addressDTO) {
        AddressDTO addressDTOSave = addressService.saveAddress(addressDTO, idPerson);
        if (addressDTOSave != null) {
            return new ResponseEntity<>(addressDTOSave, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Address address = addressService.findById(id);
        return ResponseEntity.ok().body(address);
    }
    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        List<Address> addressList = addressService.findAll();
        return ResponseEntity.ok().body(addressList);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateAddress(@PathVariable Long id,@RequestBody Address address) {
        Address addressEntity = addressService.update(id, address);
        return ResponseEntity.ok().body(addressEntity);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteAddress(@PathVariable  Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
