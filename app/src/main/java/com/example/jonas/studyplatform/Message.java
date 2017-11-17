package com.example.jonas.studyplatform;

/**
 * Created by Jonas on 16/11/2017.
 */

public class Message {
    public String sender;
    public String post;

    public Message() {

    }

    public Message(String sender, String post) {
        this.sender = sender;
        this.post = post;

    }

    public String getPost()
    {
        return post;
    }

    public  String toString(){
        return sender + ": " + post;
    }
}


