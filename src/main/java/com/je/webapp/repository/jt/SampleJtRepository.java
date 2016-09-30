package com.je.webapp.repository.jt;

import com.je.webapp.repository.sql.SampleQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SampleJtRepository {

    private Logger logger = LoggerFactory.getLogger(SampleJtRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jt;

    public String searchUser(String id) {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        return jt.queryForObject(SampleQuery.SAMPLE_SELECT_ONE, param, this::mapRow);
    }
    private String mapRow(ResultSet rs, int rownum) throws SQLException {

        return rs.getString("name");
    }

    public List<String> searchUserList() {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("", "");

        return jt.query(SampleQuery.SAMPLE_SELECT_LIST, param, this::mapRow);
    }
    private List<String> mapRowList(ResultSet rs, int rownum) throws SQLException {

        List<String> resultList = new ArrayList<>();

        resultList.add(rs.getString("name"));

        return resultList;
    }

    public void addUser(String name) throws SQLException {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", name);

        jt.update(SampleQuery.SAMPLE_INSERT, param);
    }






}
