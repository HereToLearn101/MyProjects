/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tedis
 */
public class SightingMapper implements RowMapper<Sighting> {

    @Override
    public Sighting mapRow(ResultSet rs, int i) throws SQLException {
        Sighting sighting = new Sighting();
        sighting.setId(rs.getInt("sighting_id"));
        sighting.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
        return sighting;
    }
    
}
