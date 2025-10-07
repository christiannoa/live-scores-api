package com.chris.livescores.domain;

public class Team {
    private String id;
    private String name;
    private String city;
    private String league;

    public Team() {
    }

    public Team(String id, String name, String city, String league) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.league = league;
    }

    // getters + setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

}
