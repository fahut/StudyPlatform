package com.example.jonas.studyplatform;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Jonas on 30/10/2017.
 */

@IgnoreExtraProperties
public class User {
    String username;
    String name;
    String education;
    String strong1;
    String strong2;
    String weak1;
    String weak2;
    String uid;

    DatabaseReference myRef;


    public User() {

    }

    public User(String username, String name, String education, String strong1, String strong2, String weak1, String weak2, String uid) {
        this.username = username;
        this.name = name;
        this.education = education;
        this.strong1 = strong1;
        this.strong2 = strong2;
        this.weak1 = weak1;
        this.weak2 = weak2;
        this.uid = uid;
    }

    public User(String username, String name, String education, String strong1, String strong2, String weak1, String weak2) {
        this.username = username;
        this.name = name;
        this.education = education;
        this.strong1 = strong1;
        this.strong2 = strong2;
        this.weak1 = weak1;
        this.weak2 = weak2;
    }


    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }
    public void setEducation(String education)
    {
        this.education = education;
    }
    public String getEducation()
    {
        return this.education;
    }
    public void setStrong1(String strong1)
    {
        this.strong1 = strong1;
    }
    public String getStrong1()
    {
        return this.strong1;
    }
    public void setStrong2(String strong2)
    {
        this.strong2 = strong2;
    }
    public String getStrong2()
    {
        return this.strong2;
    }
    public void setWeak1(String weak1)
    {
        this.weak1 = weak1;
    }
    public String getWeak1()
    {
        return this.weak1;
    }
    public void setWeak2(String weak2)
    {
        this.weak2 = weak2;
    }
    public String getWeak2()
    {
        return this.weak2;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getUid()
    {
        return this.uid;
    }


    @Override
    public String toString()
    {
        return name + ", "  + ": Can teach: " + strong1 + ", " + strong2;
    }
}
