package com.smallchili.xmz.model;

import com.smallchili.xmz.util.XmlUtil;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xmz
 * @date: 2020/10/29
 */
public class Author {

    private String author;

    private String date;


    public Author(String author, String date){
        this.author = author;
        this.date = date;
    }

    public static Author build(){
        return new Author(XmlUtil.getRootElement().getName()
                ,new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Author{" +
                "author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
