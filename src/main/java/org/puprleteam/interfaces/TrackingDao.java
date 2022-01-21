package org.puprleteam.interfaces;

import org.puprleteam.pojos.Tracking;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface TrackingDao {

    Tracking getTrackById(Long id) throws SQLException;

    List<Tracking> findAll() throws SQLException;

    Serializable save(Tracking track) throws  SQLException;

    void update(Tracking track) throws SQLException;

    boolean deleteTrackById(Serializable id) throws SQLException;
}
