package org.mns.dao;

import org.mns.model.Restaurace;

import java.util.List;
import java.util.Optional;

public interface RestauraceDao {
    //void pridatRestauraci(Restaurace restaurace);
    //void upravitRestauraci(Restaurace restaurace);
    //void smazatRestauraci(int id);

    public Optional<Restaurace> getRestauraceById(int id) throws Exception;
    public List<Restaurace> getVsechnyRestaurace() throws Exception;
    public List<Restaurace> vyhledatRestauraciPodleTextu(String Text) throws Exception;
}