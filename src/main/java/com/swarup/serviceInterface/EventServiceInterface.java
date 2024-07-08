package com.swarup.serviceInterface;



import java.util.List;

import com.swarup.entity.EventsEntity;
import com.swarup.entity.Form;

public interface EventServiceInterface {

   // public String loadEvent(String eventCategory);
   public EventsEntity loadEventData(String eventCategory);

public List<Form> getUserEvents(String email);

public void deleteUserBooking(Integer id);

}
