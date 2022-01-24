package org.puprleteam.service;

import lombok.SneakyThrows;
import org.puprleteam.dao.TrackingDaoImpl;
import org.puprleteam.interfaces.TrackingService;
import org.puprleteam.interfaces.Trackings;
import org.puprleteam.pojos.Tracking;

import java.io.Serializable;
import java.util.List;

public class TrackingServiceImpl implements TrackingService {

    private final TrackingDaoImpl trackingDao = new TrackingDaoImpl();

    @Override
    @SneakyThrows
    public Serializable create(Trackings tracking) {
        return trackingDao.save(tracking);
    }

    @Override
    @SneakyThrows
    public List<Tracking> readAll() {
        return trackingDao.findAll();
    }

    @Override
    @SneakyThrows
    public Tracking read(Long id) {
        return trackingDao.getTrackById(id);
    }

    @Override
    @SneakyThrows
    public boolean update(Tracking tracking) {
        trackingDao.update(tracking);
        return false;
    }

    @Override
    @SneakyThrows
    public boolean delete(Long id) {
        return trackingDao.deleteTrackById(id);
    }
}
