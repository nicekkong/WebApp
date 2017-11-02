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

    public static String Q1 = """
        SELECT  member as c1 , group_name as c2
                FROM  cm_group_member
        GROUP BY member
    """

    // 조별 인원
    public static String Q2 = """
        SELECT  group_name as c1 , count(*) as c2
        FROM  cm_group_member
        GROUP BY group_name
    """

    // 조별 총점
    public static String Q3 = """
        SELECT  group_name as c1, SUM(score) as c2
        FROM  cm_member_info
        GROUP BY group_name
    """

    // 개인 총점
    public static String Q4 = """
        SELECT  name as c1, SUM(score) as c2
        FROM  cm_member_info
        GROUP BY name
        ORDER BY SUM(score) desc
    """

    // 개인별 최고 점수
    public static String Q5 = """
        SELECT  name as c1 , MAX(score) as c2
        FROM  cm_member_info
        GROUP BY name
        ORDER BY MAX(score) desc
    """


}
