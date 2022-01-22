package org.puprleteam.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.puprleteam.data.SessionFactoryHolder;
import org.puprleteam.interfaces.TrackingDao;
import org.puprleteam.interfaces.Trackings;
import org.puprleteam.pojos.Tracking;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class TrackingDaoImpl implements TrackingDao {

    private final SessionFactory sessionFactory;

    public TrackingDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public TrackingDaoImpl() {
        this.sessionFactory = SessionFactoryHolder.getSessionFactory();
    }

    @Override
    public Tracking getTrackById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Tracking tracking = session.get(Tracking.class, id);
        tr.commit();
        session.close();
        return tracking;
    }

    @Override
    public List<Tracking> findAll() {
        List<Tracking> trackings = (List<Tracking>) sessionFactory
                .openSession()
                .createQuery("From Tracking ").list();
        return trackings;
    }

    @Override
    public Serializable save(Trackings track) {
        Session session = sessionFactory.openSession();
        Serializable id = null;
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.saveOrUpdate(track);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return id;
    }

    @Override
    public void update(Tracking track) {
        Session session = sessionFactory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(track);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public boolean deleteTrackById(Serializable id) {
        Session session = sessionFactory.openSession();
        Tracking tracking = session.get(Tracking.class, id);
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.delete(tracking);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tracking != null;
    }
}
