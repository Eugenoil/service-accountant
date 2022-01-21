package org.puprleteam.service;

import lombok.SneakyThrows;
import org.puprleteam.dao.TrackingDaoImpl;
import org.puprleteam.interfaces.TrackingService;
import org.puprleteam.pojos.Tracking;

import java.io.Serializable;
import java.util.List;

public class TrackingServiceImpl implements TrackingService {

    private TrackingDaoImpl trackingDao = new TrackingDaoImpl();




    @Override
    @SneakyThrows
    public Serializable create(Tracking tracking) {
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
    public void update(Tracking tracking) {
        trackingDao.update(tracking);
    }

    @Override
    @SneakyThrows
    public boolean delete(int id) {
        return trackingDao.deleteTrackById(id);
    }
}
