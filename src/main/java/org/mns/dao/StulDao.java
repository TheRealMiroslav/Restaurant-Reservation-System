package org.mns.dao;

public interface StulDao {
    boolean jeStulDostupny(int stulId, String casZacatek, String casKonec) throws Exception;
}