package com.udemy.springmvc.bbs.vo;

public class Bbs {

    private String subject;
    private String contents;
    private String writer;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Bbs{" +
                "subject='" + subject + '\'' +
                ", contents='" + contents + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }
}
