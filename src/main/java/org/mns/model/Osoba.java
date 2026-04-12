package org.mns.model;

public abstract class Osoba {
    protected String jmeno;
    protected String prijmeni;
    protected String email;
    protected String telefoniCislo;

    public Osoba(String jmeno, String prijmeni, String email, String telefoniCislo) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.telefoniCislo = telefoniCislo;
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

    public String getTelefoniCislo() {
        return telefoniCislo;
    }

    public void setTelefoniCislo(String telefoniCislo) {
        this.telefoniCislo = telefoniCislo;
    }
}
