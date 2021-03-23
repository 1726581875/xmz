package com.smallchili.xmz.model;

/**
 * @author xmz
 * @date: 2020/10/28
 */
public class Table {
    //表名
    private String tableName;
    //表名小驼峰
    private String tableNameHump;
    //表名大驼峰
    private String tableNameBigHump;

    public Table(String tableName,String tableNameHump, String tableNameBigHump) {
        this.tableName = tableName;
        this.tableNameHump = tableNameHump;
        this.tableNameBigHump = tableNameBigHump;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableNameHump() {
        return tableNameHump;
    }

    public void setTableNameHump(String tableNameHump) {
        this.tableNameHump = tableNameHump;
    }

    public String getTableNameBigHump() {
        return tableNameBigHump;
    }

    public void setTableNameBigHump(String tableNameBigHump) {
        this.tableNameBigHump = tableNameBigHump;
    }
}
