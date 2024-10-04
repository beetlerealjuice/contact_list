package com.example.demo.controllers;

import com.example.demo.entities.Contact;
import com.example.demo.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("contacts", contactService.findAll());
        return "index";
    }

    @GetMapping({"/contact/create", "/contact/edit/{id}"})
    public String showForm(@PathVariable(required = false) Long id, Model model) {
        Contact contact;
        if (id != null) {
            contact = contactService.findById(id);
            if (contact == null) {
                // Обработка ошибки: контакт не найден
                return "redirect:/";
            }
        } else {
            contact = new Contact();
        }
        model.addAttribute("contact", contact);
        return "contact-form"; // Имя вашей HTML-формы
    }

    @PostMapping("/contact/save")
    public String saveContact(@ModelAttribute Contact contact) {
        if (contact.getId() == null) {
            contactService.save(contact);
        } else {
            // Редактирование существующего контакта
            Contact existingContact = contactService.findById(contact.getId());
            if (existingContact != null) {
                contactService.update(contact);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);

        return "redirect:/";
    }
}
