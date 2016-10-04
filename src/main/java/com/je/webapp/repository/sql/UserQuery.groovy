package com.je.webapp.repository.sql

/*****************************************************************************
 * PROJECT NAME   : JEWebApp
 * SUBSYSTEM NAME : 
 * FILE NAME      : .java
 * DESCRIPTION    : 
 *
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
 *  1.0           Kong,Byungil     2016. 9. 30.     init
 *****************************************************************************/
class UserQuery {

    final public static String SELECT_USER_ALL = """
        SELECT *
          FROM app_user
    """;

    final public static String SELECT_USER_BY_ID_AND_PWD = """
        SELECT *
          FROM  app_user
         WHERE  user_id = :userId
           AND  password = :password
    """;

}