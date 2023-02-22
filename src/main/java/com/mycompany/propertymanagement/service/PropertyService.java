package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getAllProperties();

    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long Id);

    PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long Id);

    PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long Id);

    void deleteProperty(Long Id);
}
