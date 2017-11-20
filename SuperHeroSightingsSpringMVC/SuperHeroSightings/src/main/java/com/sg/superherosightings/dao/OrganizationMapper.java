/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tedis
 */
public class OrganizationMapper implements RowMapper<Organization> {

    @Override
    public Organization mapRow(ResultSet rs, int i) throws SQLException {
        Organization org = new Organization();
        org.setId(rs.getInt("org_id"));
        org.setOrgName(rs.getString("org_name"));
        org.setOrgDescription(rs.getString("org_description"));
        org.setPhone(rs.getString("phone"));
        org.setEmail(rs.getString("email"));
        return org;
    }
    
}
