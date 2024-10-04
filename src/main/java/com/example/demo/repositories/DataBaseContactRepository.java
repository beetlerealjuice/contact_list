package com.example.demo.repositories;

import com.example.demo.entities.Contact;
import com.example.demo.exceptions.ContactNotFoundException;
import com.example.demo.repositories.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DataBaseContactRepository implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        log.debug("DataBaseContactRepository -> findAll");

        String sql = "SELECT * FROM contacts ORDER BY id";
        return jdbcTemplate.query(sql,
                // Маппим таблицу в сущность. rs - resultSet
                (rs, rowNum) -> {
                        Contact contact = new Contact();
                        contact.setId(rs.getLong("id"));
                        contact.setFirstName(rs.getString("first_name"));
                        contact.setLastName(rs.getString("last_name"));
                        contact.setEmail(rs.getString("email"));
                        contact.setPhone(rs.getString("phone"));
                        return contact;
                });
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("DataBaseContactRepository -> findById with ID: {}", id);

        String sql = "SELECT * FROM contacts WHERE id = ?";

        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );

        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("DataBaseContactRepository -> save with Contact: {}", contact);

        //contact.setId(System.currentTimeMillis()); //todo сделать автоинкремент
        String sql = "INSERT INTO contacts (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone());

        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("DataBaseContactRepository -> update with Contact: {}", contact);

        Contact existedContact = findById(contact.getId()).orElse(null);
        if (existedContact != null) {
            String sql = "UPDATE contacts SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(),
                    contact.getEmail(), contact.getPhone(), contact.getId());
            return contact;
        }

        log.warn("Contact with ID {} not found", contact.getId());

        throw new ContactNotFoundException("Contact for update not found! ID: " + contact.getId());
    }

    @Override
    public void deleteById(Long id) {
        log.debug("DataBaseContactRepository -> delete with ID: {}", id);

        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
