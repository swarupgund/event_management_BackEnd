package com.swarup.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swarup.entity.Form;
import com.swarup.repository.FormRepo;
import com.swarup.serviceInterface.FormService;

import jakarta.transaction.Transactional;

@Service
public class FormImplementation implements FormService {
    @Autowired
    FormRepo repo;

    @Override
    public int save(Form form) {
        return repo.save(form).getId();
    }


    @Override
    @Transactional
    public void saveForm(Form form) {
    	repo.save(form);
    }

	@Override
	public List<Form> getAllBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBooking(Integer id) {
		// TODO Auto-generated method stub
		
	}

   

}
