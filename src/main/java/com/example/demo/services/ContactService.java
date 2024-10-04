package com.example.demo.services;

import com.example.demo.entities.Contact;
import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(Long id);
}
