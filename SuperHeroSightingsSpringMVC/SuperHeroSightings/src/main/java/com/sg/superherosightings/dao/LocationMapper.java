/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tedis
 */
public class LocationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int i) throws SQLException {
        Location location = new Location();
        location.setId(rs.getInt("location_id"));
        location.setLocationName(rs.getString("location_name"));
        location.setLocationDescription(rs.getString("location_description"));
        location.setAddress(rs.getString("address"));
        location.setLat(rs.getBigDecimal("lat"));
        location.setLng(rs.getBigDecimal("lng"));
        return location;
    }
    
}
