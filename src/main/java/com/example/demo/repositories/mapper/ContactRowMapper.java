package com.example.demo.repositories.mapper;

import com.example.demo.entities.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();
        Contact contact = new Contact();
        contact.setId(rs.getLong(metaData.getColumnLabel(1)));
        contact.setFirstName(rs.getString(metaData.getColumnLabel(2)));
        contact.setLastName(rs.getString(metaData.getColumnLabel(3)));
        contact.setEmail(rs.getString(metaData.getColumnLabel(4)));
        contact.setPhone(rs.getString(metaData.getColumnLabel(5)));

        return contact;
    }
}
