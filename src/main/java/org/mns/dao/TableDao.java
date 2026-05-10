package org.mns.dao;

import java.sql.Timestamp;
import org.mns.model.Table;

public interface TableDao {
    boolean isTableAvailable(int tableId, Timestamp startTime, Timestamp endTime) throws Exception;

    Table getById(int id) throws Exception;
}