package com.example.session09.controller;

import com.example.session09.model.dto.request.RoomServiceDTO;
import com.example.session09.model.entity.RoomServices;
import com.example.session09.service.RoomService;
import com.example.session09.service.RoomServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/services")
public class RoomServiceController {
    @Autowired
    private RoomServicesService roomServicesService;

    @GetMapping
    public ResponseEntity<Page<RoomServices>> getAllServices(Pageable pageable) {
        return ResponseEntity.ok(roomServicesService.getAllServices(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addService(@RequestBody RoomServiceDTO serviceDTO) {
       RoomServices service = roomServicesService.addService(serviceDTO);
       if (service != null) {
           return new ResponseEntity<>(service, HttpStatus.CREATED);
       }else {
           return new ResponseEntity<>("add service failed !" , HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody RoomServiceDTO serviceDTO) {
        RoomServices services = roomServicesService.updateService(id, serviceDTO);
        if (services != null) {
            return new ResponseEntity<>(services, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("update service failed !" , HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
       return roomServicesService.deleteService(id);
    }
}