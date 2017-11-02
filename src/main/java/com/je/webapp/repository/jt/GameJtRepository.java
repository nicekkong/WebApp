package com.je.webapp.repository.jt;

import com.je.webapp.Domain.MemberInfo;
import com.je.webapp.repository.sql.GameQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameJtRepository {

    private Logger logger = LoggerFactory.getLogger(GameJtRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jt;


    public String searchMember(String name) {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", name);
        String groupName ;
        try {

            groupName = jt.queryForObject(GameQuery.SEARCH_MY_MEMBER, param, this::mapRow);
        } catch (EmptyResultDataAccessException nre) {
            groupName = "new";
        }

        return groupName;
    }
    private String mapRow(ResultSet rs, int rownum) throws SQLException {

        return rs.getString("group_name");
    }

    public String searchMyGroup() {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("", "");

        return jt.queryForObject(GameQuery.SEARCH_NEW_GROUP, param, this::myGroupmapRow);
    }
    private String myGroupmapRow(ResultSet rs, int rownum) throws SQLException {

        return rs.getString("group_name");
    }

    public void addMember(String name, String group) throws SQLException {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("group", group);

        jt.update(GameQuery.INSERT_MEMBER, param);
    }

    // SEARCH_MEMBER_INFO
    public List<MemberInfo> searchMemberInfo(String name) {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", name);
        return jt.query(GameQuery.SEARCH_MEMBER_INFO, param, this::allMapRow);
    }
    private MemberInfo allMapRow(ResultSet rs, int row) throws SQLException {

        MemberInfo memberInfo = new MemberInfo();

        memberInfo.setName(rs.getString("name"));
        memberInfo.setGroup(rs.getString("group_name"));
        memberInfo.setGame(rs.getString("game"));
        memberInfo.setScore(rs.getInt("score"));

        return memberInfo;
    }


    public void addScore(String name, String group, String game, int score) throws SQLException {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("group", group)
                .addValue("game", game)
                .addValue("score", score);

        jt.update(GameQuery.INSERT_SCORE, param);
    }

    public List<Map<String, String>> q1() {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("", "");
        return jt.query(GameQuery.Q1, param, this::q1MapRow);
    }
    private Map<String, String> q1MapRow(ResultSet rs, int row) throws SQLException {

        Map<String, String> result = new HashMap<>();

        result.put("c1", rs.getString("c1"));
        result.put("c2", rs.getString("c2"));

        return result;
    }

    public List<Map<String, String>> q2() {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("", "");
        return jt.query(GameQuery.Q2, param, this::q2MapRow);
    }
    private Map<String, String> q2MapRow(ResultSet rs, int row) throws SQLException {

        Map<String, String> result = new HashMap<>();

        result.put("c1", rs.getString("c1"));
        result.put("c2", rs.getString("c2"));

        return result;
    }

    public List<Map<String, String>> q3() {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("", "");
        return jt.query(GameQuery.Q3, param, this::q3MapRow);
    }
    private Map<String, String> q3MapRow(ResultSet rs, int row) throws SQLException {

        Map<String, String> result = new HashMap<>();

        result.put("c1", rs.getString("c1"));
        result.put("c2", rs.getString("c2"));

        return result;
    }

    public List<Map<String, String>> q4() {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("", "");
        return jt.query(GameQuery.Q4, param, this::q4MapRow);
    }
    private Map<String, String> q4MapRow(ResultSet rs, int row) throws SQLException {

        Map<String, String> result = new HashMap<>();

        result.put("c1", rs.getString("c1"));
        result.put("c2", rs.getString("c2"));

        return result;
    }

    public List<Map<String, String>> q5() {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("", "");
        return jt.query(GameQuery.Q5, param, this::q5MapRow);
    }
    private Map<String, String> q5MapRow(ResultSet rs, int row) throws SQLException {

        Map<String, String> result = new HashMap<>();

        result.put("c1", rs.getString("c1"));
        result.put("c2", rs.getString("c2"));

        return result;
    }
}
