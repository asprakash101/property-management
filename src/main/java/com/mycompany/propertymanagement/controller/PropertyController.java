package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Value("${pms.dummy}")
    private String dummy;

//    @Value("${pms.localDummy}")
//    private String localDummy;

    @Value(("${spring.datasource.url}"))
    private String dbURL;

    @GetMapping("/hello")
    public String sayHello(){

        System.out.println("Inside the dev branch");
        System.out.println(dummy);
//        System.out.println(localDummy);
        System.out.println(dbURL);
        return "Hello";

    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyDTO = propertyService.saveProperty(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {

        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(propertyList, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/properties/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long id){

        propertyDTO = propertyService.updateProperty(propertyDTO, id);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<PropertyDTO>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-description/{id}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long id) {
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, id);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-price/{id}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long id) {
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, id);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/properties/delete-property/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}
