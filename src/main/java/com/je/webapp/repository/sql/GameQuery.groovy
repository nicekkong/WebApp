package com.je.webapp.repository.sql

class GameQuery {

    public static String SEARCH_MY_MEMBER = """
        SELECT  group_name
          FROM  cm_group_member
         WHERE  member = :name  
    """;

    public static String SEARCH_NEW_GROUP = """
        SELECT  group_name
          FROM  (
        SELECT  b.group_name, count(a.group_name) as cnt
          FROM  cm_group_member a right join
                cm_group b
         on  a.group_name = b.group_name
         group by b.group_name
         ) A
        order by cnt
        limit 1 
    """

    public static String INSERT_MEMBER = """
        INSERT INTO cm_group_member (group_name, member) VALUES (:group, :name)
    """

    public static String SEARCH_MEMBER_INFO = """
        SELECT  name, group_name, game, score
          FROM  cm_member_info
         WHERE  name = :name
    """

    public static String INSERT_SCORE = """
        INSERT INTO cm_member_info (name, group_name, game, score) 
                    VALUES (:name, :group, :game, :score)

    """
}
