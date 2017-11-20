/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tedis
 */
public class PowerMapper implements RowMapper<Power> {

    @Override
    public Power mapRow(ResultSet rs, int i) throws SQLException {
        Power power = new Power();
        power.setId(rs.getInt("power_id"));
        power.setPowerName(rs.getString("power_name"));
        return power;
    }
    
}
