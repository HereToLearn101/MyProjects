/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.HeroVillain;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tedis
 */
public class HeroVillainMapper implements RowMapper<HeroVillain> {

    @Override
    public HeroVillain mapRow(ResultSet rs, int i) throws SQLException {
        HeroVillain hV = new HeroVillain();
        hV.setId(rs.getInt("hv_id"));
        hV.sethVName(rs.getString("hv_name"));
        hV.sethVDescription(rs.getString("hv_description"));
        hV.setIsVillain(rs.getBoolean("isVillain"));
        return hV;
    }
    
}
