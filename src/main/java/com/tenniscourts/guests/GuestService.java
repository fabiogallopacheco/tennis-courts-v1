package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    private final GuestMapper guestMapper;

    public GuestDTO addGuest(GuestDTO guestDTO) {
        return guestMapper.map(guestRepository.saveAndFlush(guestMapper.map(guestDTO)));
    }

    public GuestDTO findById(Long guestId) {
        return guestRepository.findById(guestId).map(guestMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Guest not found.");
        });
    }

    public GuestDTO updateGuest(GuestDTO guestDTO) {
        if (!guestRepository.existsById(guestDTO.getId())) {
            throw new EntityNotFoundException("Guest not found.");
        }
        return guestMapper.map(guestRepository.save(guestMapper.map(guestDTO)));
    }

    public void deleteGuest(Long guestId) {
        Guest guest = guestRepository.findById(guestId).orElseThrow(() -> {
            throw new EntityNotFoundException("Guest not found.");
        });
        guestRepository.delete(guest);
    }

    public List<GuestDTO> listAll() {
        return guestMapper.map(guestRepository.findAll());
    }

    public List<GuestDTO> findByName(String name) {
        return guestMapper.map(guestRepository.findByName(name));
    }
}
