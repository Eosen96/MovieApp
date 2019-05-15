package com.example1.black.my_application;

public class People {

    int id;
    double popularity;
    String name;
    String imagepath;
    String baseurl = "http://image.tmdb.org/t/p/w200";

    public People(String name, int id,double popularity, String imagepath){
        this.id = id;
        this.popularity =popularity;
        this.name = name;
        this.imagepath = baseurl + imagepath;


    }


    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", popularity=" + popularity +
                ", name='" + name + '\'' +
                ", imagepath='" + imagepath + '\'' +
                '}';
    }
}
