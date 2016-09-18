package com.je.webapp.repository.sql

class SampleQuery {

    public static String SAMPLE_SELECT_ONE = """
        SELECT name FROM tbl_user WHERE id = :id
    """;

    public static String SAMPLE_SELECT_LIST = """
        SELECT name FROM tbl_user
    """;

    public static String SAMPLE_INSERT = """
        INSERT INTO tbl_user (name) values (:name)
    """;

    public static String SAMPLE_DELETE = """
        DELETE FROM tbl_user WHERE = :name
    """;
}
