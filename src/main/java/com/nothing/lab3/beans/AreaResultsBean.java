package com.nothing.lab3.beans;

import com.nothing.lab3.db.HsqlQueries;
import com.nothing.lab3.utils.AreaChecker;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

/**
 * Class for managing data from bean, adding, clearing and fetching data from database
 */
@Named
@ApplicationScoped
public class AreaResultsBean implements Serializable {
    @Inject
    private DotsCollection dotsCollection;
    private LinkedList<DotsCollection> curDot;
    private SimpleDateFormat simpleDateFormat;
    private DecimalFormat decimalFormat;

    @PostConstruct
    private void initialize() {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        decimalFormat = new DecimalFormat("#.#####");
        curDot = new LinkedList<>();
        try {
            curDot = new LinkedList<>(HsqlQueries.getAllResults());
        } catch (SQLException ex) {
            System.out.println("Something went wrong while fetching the data");
        }
    }

    /**
     * Adds new result to local collection and to database
     */
    public void addNewResult() {
        long startExec = System.nanoTime();
        DotsCollection dot = new DotsCollection();
        dot.setHitType(AreaChecker.checkArea(dotsCollection.getX(), dotsCollection.getY(), dotsCollection.getR()));
        System.out.println(dot.getHitType());
        long endExec = System.nanoTime();
        long executionTime = endExec - startExec;
        dot.setExecutionTime(executionTime);
        String requestTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        dot.setCurrentTime(requestTime);
        dot.setX(Double.parseDouble(decimalFormat.format(dotsCollection.getX()).replace(",", ".")));
        dot.setY(Double.parseDouble(decimalFormat.format(dotsCollection.getY()).replace(",", ".")));
        dot.setR(Double.parseDouble(decimalFormat.format(dotsCollection.getR()).replace(",", ".")));
        if (curDot != null && curDot.add(dot)) {
            try {
                HsqlQueries.addResult(dot);
            } catch (SQLException e) {
                System.out.println("Something went wrong while adding new result");
            }
        }
    }

    /**
     * Clear local collection and table im database
     */
    public void clearResult() {
        if (curDot != null) {
            curDot.clear();
        }
        try {
            HsqlQueries.clearResults();
        } catch (SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public DotsCollection getDotsCollectionBean() {
        return dotsCollection;
    }

    public void setDotsCollectionBean(DotsCollection dotsCollection) {
        this.dotsCollection = dotsCollection;
    }

    public LinkedList<DotsCollection> getCurDot() {
        return curDot;
    }

    public void setCurResult(LinkedList<DotsCollection> curDot) {
        this.curDot = curDot;
    }
}
