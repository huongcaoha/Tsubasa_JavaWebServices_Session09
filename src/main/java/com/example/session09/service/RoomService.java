package com.example.session09.service;

import com.example.session09.model.dto.request.RoomDTO;
import com.example.session09.model.entity.Room;
import com.example.session09.repository.RoomRepository;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service

public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void save(Room room) {
        roomRepository.save(room);
    }

    public Room addRoom(RoomDTO roomDTO) {
        Room newRoom = convertRoomDTOToRoom(roomDTO);
        newRoom.setReservation(null);
        try {
            return roomRepository.save(newRoom);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Room findRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public Room updateRoom(Long id, RoomDTO roomDTO) {
        Room oldRoom = findRoomById(id);
        if (oldRoom != null) {
            oldRoom.setRoomName(roomDTO.getRoomName());
            oldRoom.setType(roomDTO.getType());
            oldRoom.setPrice(roomDTO.getPrice());
            try {
                return roomRepository.save(oldRoom);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    public String deleteRoom(Long id) {
        Room room = findRoomById(id);
        if (room != null) {
            try {
                roomRepository.delete(room);
                return "Delete room successfully";
            } catch (Exception e) {
                return "Delete Room Failed";
            }
        }else {
            return "Room not found";
        }
    }

    public Page<Room> getAllRooms(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    public Room convertRoomDTOToRoom(RoomDTO roomDTO) {
        return Room
                .builder()
                .roomName(roomDTO.getRoomName())
                .type(roomDTO.getType())
                .price(roomDTO.getPrice())
                .build();
    }
}
