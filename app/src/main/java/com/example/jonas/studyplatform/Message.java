package com.example.jonas.studyplatform;

/**
 * Created by Jonas on 16/11/2017.
 */

public class Message {
    public String sender;
    public String post;
    public String uid;

    public Message() {

    }

    public Message(String sender, String post, String uid) {
        this.sender = sender;
        this.post = post;
        this.uid = uid;
    }

    public Message(String sender, String post) {
        this.sender = sender;
        this.post = post;
    }

    public String getPost()
    {
        return post;
    }

    public String getSender()
    {
        return sender;
    }

    public String getUid()
    {
        return uid;
    }

    public  void setUid(String uid)
    {
        this.uid = uid;
    }

    public  String toString(){
        return sender + ":" + post + ":" + uid;
    }
}


