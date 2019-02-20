package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Whisky> findWhiskyByYear(int year) {
        List<Whisky> results = null;

        Criteria cr = null;
        try{
            Session session = entityManager.unwrap(Session.class);
            cr = session.createCriteria(Whisky.class);
            cr.add(Restrictions.eq("year", year));
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        results = cr.list();
        return results;
    }

    @Override
    public List<Whisky> getAllWhiskyFromDistilleryByAge(String name, int age) {
        List<Whisky> results = null;

        Criteria cr = null;
        try{
            Session session = entityManager.unwrap(Session.class);
            cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery", "distillery");
            cr.add(Restrictions.eq("distillery.name", name));
            cr.add(Restrictions.eq("age", age));

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        results = cr.list();
        return results;
    }

    @Override
    public List<Whisky> getAllWhiskyByRegion(String region) {
        List<Whisky> results = null;

        Criteria cr = null;
        try{
            Session session = entityManager.unwrap(Session.class);
            cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery", "distillery");
            cr.add(Restrictions.eq("distillery.region", region));
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        results = cr.list();
        return results;
    }


}
