package com.nothing.lab3.beans;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Bean component which represents data from user
 */
@Entity
@Named
@Table(name = "result")
@SessionScoped
public class DotsCollection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "x", nullable = false)
    private double x;
    @Column(name = "y", nullable = false)
    private double y;
    @Column(name = "r", nullable = false)
    private double r;
    @Column(name = "request_time", nullable = false)
    private String currentTime;
    @Column(name = "execution_time", nullable = false)
    private long executionTime;
    @Column(name = "hit_type", nullable = false)
    private String hitType;

    public DotsCollection() {
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }


    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public String getHitType() {
        return hitType;
    }

    public void setHitType(String hitType) {
        this.hitType = hitType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if(x >= -3 && x <= 5){
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}
