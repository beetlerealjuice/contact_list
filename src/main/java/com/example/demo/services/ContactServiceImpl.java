package com.example.demo.services;

import com.example.demo.entities.Contact;
import com.example.demo.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {

   private final ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        log.debug("Call findAll in ContactRepository");

        return contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        log.debug("Call findById in ContactRepository");

        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Call save in ContactRepository");

        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Call update in ContactRepository");

        return contactRepository.update(contact);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById in ContactRepository");

        contactRepository.deleteById(id);
    }
}
