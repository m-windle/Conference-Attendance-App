package com.m_windle.xyz_conference.database;

/**
 * Created by mr_moshi on 11/13/2015.
 */
public class Presenter {
    private long id;
    private String fname;
    private String lname;
    private String affiliation;
    private String email;
    private String bio;

    public long getId() { return id; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getAffiliation() { return affiliation; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }

    public void setId(long id) { this.id = id; }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
