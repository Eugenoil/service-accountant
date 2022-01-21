package org.puprleteam;

import org.puprleteam.dao.TrackingDaoImpl;
import org.puprleteam.interfaces.TrackingDao;
import org.puprleteam.pojos.Tracking;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws SQLException {
        TrackingDao trackingDao = new TrackingDaoImpl();
        Tracking tracking = new Tracking();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        tracking.setEnd_time(timestamp);
        tracking.setStart_time(timestamp);
        tracking.setTask_name("Java learning");
        trackingDao.save(tracking);

    }
}
