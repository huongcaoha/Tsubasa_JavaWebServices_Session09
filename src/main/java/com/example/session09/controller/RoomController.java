package com.example.session09.controller;

import com.example.session09.model.dto.request.RoomDTO;
import com.example.session09.model.entity.Room;
import com.example.session09.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<Page<Room>> getAllRooms(Pageable pageable) {
        return ResponseEntity.ok(roomService.getAllRooms(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@RequestBody RoomDTO roomDTO) {
        Room room = roomService.addRoom(roomDTO);
        if (room != null) {
            return new ResponseEntity<>(room , HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Room not added !" , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        Room room = roomService.updateRoom(id, roomDTO);
        if (room != null) {
            return new ResponseEntity<>(room , HttpStatus.OK);
        }else {
            return new ResponseEntity<>("update room failed !", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
      return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
    }
}
