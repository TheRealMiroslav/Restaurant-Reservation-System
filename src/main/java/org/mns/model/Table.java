package org.mns.model;

/**
 * Entita reprezentující stůl v restauraci.
 */
public class Table {
    protected int id;
    protected String code;
    protected int capacity;

    public Table(int id, String code, int capacity) {
        this.id = id;
        this.code = code;
        this.capacity = capacity;
    }

    public Table(String code, int capacity) {
        this.code = code;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
