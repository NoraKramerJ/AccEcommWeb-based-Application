package com.cydeo.controller;


import com.cydeo.dto.ClientVendorDto;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.enums.ClientVendorType;
import com.cydeo.service.ClientVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/clientVendor")
public class ClientVendorController {




    private final ClientVendorService clientVendorService;

    public ClientVendorController(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }
@GetMapping
    public ResponseEntity<ResponseWrapper> getClientVendorList() {
    List<ClientVendorDto> clientVendorDtoList = clientVendorService.listAllClientVendors();
    return ResponseEntity.ok(new ResponseWrapper("client/vendor list is successfully retrieved", clientVendorDtoList, HttpStatus.OK));
}
    @PostMapping
    public ResponseEntity<ResponseWrapper> createClientVendor(@RequestBody ClientVendorDto clientVendor){
        clientVendorService.save(clientVendor);
        return ResponseEntity.ok(new ResponseWrapper("Client/Vendor is successfully created",HttpStatus.CREATED));


    }
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateClientVendor(@RequestBody ClientVendorDto clientVendorDto) {
        clientVendorService.update(clientVendorDto);
        return ResponseEntity.ok(new ResponseWrapper("client/vendor successfully updated", HttpStatus.OK));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper> deleteClientVendorById(@PathVariable("id") Long id){
        clientVendorService.delete(id);
        return ResponseEntity.ok(new ResponseWrapper("Client/Vendor is deleted",HttpStatus.OK));



/*
 I redirect the to the clientVendorList... If an invoice id does not exist in the DELETE method,
  I want to redirect to the GET method to show an error message.Further,
  we can add the clientVendorWithInvoice() c method in our controller class to help us
validate that the fields don't allow empty strings:
        RedirectAttributes addFlashAttribute(String attributeName,
                @Nullable
                        Object attributeValue)
        Add the given flash attribute.
                Parameters:
        attributeName - the attribute name; never null
        attributeValue - the attribute value; may be null
        RedirectAttributes addFlashAttribute(String attributeName, @Nullable Object attributeValue);

RedirectAttributes addFlashAttribute(Object attributeValue);

Map<String, ?> getFlashAttributes();
FlashMap class inherits its behavior from the HashMap class
As such, a FlashMap instance can store a key-value mapping of the attributes.
## Input FlashMap is used in the final GET request to access the read-only flash attributes that
 were sent by the previous POST request before the redirect
 ##Output FlashMap is used in the POST request to temporarily save
 the flash attributes and send them to the next GET request after the redirect
*/


    }
}
