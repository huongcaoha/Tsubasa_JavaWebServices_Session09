package com.example.session09.service;
import com.example.session09.model.dto.request.RoomServiceDTO;
import com.example.session09.model.entity.RoomServices;
import com.example.session09.repository.RoomServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoomServicesService {
    @Autowired
    private RoomServiceRepository roomServiceRepository;

    public RoomServices addService(RoomServiceDTO serviceDTO) {
        RoomServices roomServices = new RoomServices();
        roomServices.setName(serviceDTO.getName());
        roomServices.setPrice(serviceDTO.getPrice());
        try {
            return roomServiceRepository.save(roomServices);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public RoomServices findById(Long id) {
        return roomServiceRepository.findById(id).orElse(null);
    }

    public RoomServices updateService(Long id, RoomServiceDTO serviceDTO) {
        RoomServices roomServices = findById(id);
        if (roomServices != null) {
            roomServices.setName(serviceDTO.getName());
            roomServices.setPrice(serviceDTO.getPrice());
            try {
                return roomServiceRepository.save(roomServices);
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    public ResponseEntity<String> deleteService(Long id) {
        RoomServices roomServices = findById(id);
        if (roomServices != null) {
            try {
                roomServiceRepository.delete(roomServices);
                return new ResponseEntity<>("Delete service successfully", HttpStatus.OK);
            }catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Delete service failed", HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>("Service not found", HttpStatus.BAD_REQUEST);
        }
    }

    public Page<RoomServices> getAllServices(Pageable pageable) {
        return roomServiceRepository.findAll(pageable);
    }
}
