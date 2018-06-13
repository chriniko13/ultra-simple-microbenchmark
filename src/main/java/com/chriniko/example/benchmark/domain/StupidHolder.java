package com.chriniko.example.benchmark.domain;

import java.util.Objects;

public class StupidHolder {

    private String id;
    private String name;

    public StupidHolder(String id) {
        this.id = id;
    }

    public StupidHolder(String id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StupidHolder that = (StupidHolder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StupidHolder{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
