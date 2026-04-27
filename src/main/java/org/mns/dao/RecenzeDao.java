package org.mns.dao;

import org.mns.model.Recenze;

public interface RecenzeDao {
     void vytvoritRecenzi(Recenze recenze) throws Exception;
     //void upravitRecenzi(Recenze recenze);
     void smazatRecenzi(int id) throws Exception;
}