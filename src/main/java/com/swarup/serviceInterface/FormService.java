package com.swarup.serviceInterface;

import java.util.List;

import com.swarup.entity.Form;

public interface FormService {

    int save(Form form);

	void saveForm(Form formData);

	List<Form> getAllBookings();

	void deleteBooking(Integer id);

    

}
