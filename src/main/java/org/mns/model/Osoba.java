package org.mns.model;

public abstract class Osoba {
    protected int id;
    protected String jmeno;
    protected String prijmeni;
    protected String email;
    protected String telefonniCislo;

    protected String heslo;

    public Osoba(int id, String jmeno, String prijmeni, String email, String telefonniCislo, String heslo) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.telefonniCislo = telefonniCislo;
        this.heslo = heslo;
    }

    public Osoba(int id, String jmeno, String prijmeni, String email, String telefonniCislo) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.telefonniCislo = telefonniCislo;
    }

    public Osoba() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonniCislo() {
        return telefonniCislo;
    }

    public void setTelefonniCislo(String telefonniCislo) {
        this.telefonniCislo = telefonniCislo;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }
}
