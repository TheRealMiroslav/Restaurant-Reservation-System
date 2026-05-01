package org.mns.dao;

import java.sql.Timestamp;

public interface TableDao {
    boolean isTableAvailable(int tableId, Timestamp startTime, Timestamp endTime) throws Exception;
}