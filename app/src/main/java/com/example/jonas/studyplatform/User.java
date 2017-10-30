package com.example.jonas.studyplatform;

/**
 * Created by Jonas on 30/10/2017.
 */

public class User {
    int id;
    String username;
    String email;
    String name;
    String password;

    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return this.id;
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
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }
}
