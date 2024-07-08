package com.swarup.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swarup.entity.EventsEntity;
import com.swarup.entity.Form;
import com.swarup.repository.EventsRepo;
import com.swarup.repository.FormRepo;
import com.swarup.serviceInterface.EventServiceInterface;

@Service
public class EventServiceImplementation implements EventServiceInterface {

    @Autowired
    private EventsRepo repo;  

    @Override
    public EventsEntity loadEventData(String eventCategory) {
        Optional<EventsEntity> data = repo.findByEventCategory(eventCategory);
        if (data.isPresent()) {
            return data.get();
        } else {
            return null;
        }
    }
@Autowired
private FormRepo fRepo;
    @Override
    public List<Form> getUserEvents(String email) {
       List <Form> list =fRepo.findEventByEmail(email);
        
        return list;
    }
    @Override
    public void deleteUserBooking(Integer id) {
        
        fRepo.cancelEventById(id);
    }

   
    
}
