package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.Repository.PropertyRepository;
import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.propertyDTOToEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
        return dto;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe : listOfProps) {
            propList.add(propertyConverter.convertEntityToDTO(pe));
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long Id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(Id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());

            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long Id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(Id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setDescription(propertyDTO.getDescription());

            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long Id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(Id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setPrice(propertyDTO.getPrice());

            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long Id) {
        propertyRepository.deleteById(Id);
    }


}
