package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.Repository.UserRepository;
import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.service.UserSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserSerice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUe = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if(optUe.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();

            ErrorModel error = new ErrorModel();
            error.setCode("EMAIL_ALREADY_EXISTS");
            error.setMessage("The Email With Which You Are Trying To Register Already Exists");
            errorModelList.add(error);

            throw new BusinessException(errorModelList);
        }

        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        UserDTO userDTO = null;

        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());

        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_CREDENTIALS");
            errorModel.setMessage("Incorrect Email or Password");
            errorModelList.add(errorModel);

//            We can add multiple errors here as required
//            ErrorModel errorModel = new ErrorModel();
//            errorModel.setCode("INVALID_CREDENTIALS");
//            errorModel.setMessage("Incorrect Email or Password");
//            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
            return userDTO;
    }
}
