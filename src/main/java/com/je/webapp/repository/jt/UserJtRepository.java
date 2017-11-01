/*****************************************************************************
 * PROJECT NAME   : JEWebApp
 * SUBSYSTEM NAME :
 * FILE NAME      : .java
 * DESCRIPTION    :
 * <p/>
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
 * 1.0           Kong,Byungil     2016. 9. 30.     init
 *****************************************************************************/
package com.je.webapp.repository.jt;

import com.je.webapp.Domain.User;
import com.je.webapp.repository.sql.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJtRepository {

    @Autowired
    NamedParameterJdbcTemplate jt;

    static final Logger logger = LoggerFactory.getLogger(UserJtRepository.class);

    public List<User> seletAllUser() {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("", "");
        return jt.query(UserQuery.SELECT_USER_ALL, param, this::allMapRow);
    }
    private User allMapRow(ResultSet rs, int row) throws SQLException {

        User user = new User();

        user.setUserId(rs.getString("user_id"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setCompany(rs.getString("company"));
        user.setJob(rs.getString("job"));
        user.setBirthday(rs.getString("birthday"));
        user.setCreDate(rs.getTimestamp("cre_date"));
        user.setUpdDate(rs.getTimestamp("upd_date"));

        return user;
    }

    public List<User> loginUser(String userId, String password) {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("password", password);

        return jt.query(UserQuery.SELECT_USER_BY_ID_AND_PWD, param, this::allMapRow);
    }



    public int chekckDupId(String userId) {
        SqlParameterSource param= new MapSqlParameterSource()
                .addValue("userId", userId);

        return jt.queryForObject(UserQuery.SELECT_DUP_ID, param, this::checkDupIdRow);
    }
    private int checkDupIdRow(ResultSet rs, int row) throws SQLException {

        return rs.getInt("cnt");

    }


    public void insertAppUser(String userId, String password, String name, String company,
                                String job, String birthday, String email) {

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("password", password)
                .addValue("name", name)
                .addValue("email", email)
                .addValue("company", company)
                .addValue("job", job)
                .addValue("birthday", birthday);
        jt.update(UserQuery.INSERT_NEW_USER, param);
    }






}
