package com.hardrockdevops.services.whatwillweeat.services;

import com.hardrockdevops.services.whatwillweeat.model.Recipes;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@CommonsLog
public class RecipeServices {

    private final SessionFactory sessionFactory;

    public RecipeServices(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Recipes findByRecipeId(long id) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Recipes> orderCriteriaQuery = cb.createQuery(Recipes.class);
        Root<Recipes> root = orderCriteriaQuery.from(Recipes.class);
        Predicate predicate = cb.equal(root.get("id"), id);
        orderCriteriaQuery.select(root).where(predicate);
        Query<Recipes> query = getSession().createQuery(orderCriteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException nre){
            log.error("No recipe exists with id " + id);
        }

        return null;
    }

    public List<Recipes> getAllRecipes() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Recipes> orderCriteriaQuery = cb.createQuery(Recipes.class);
        Query<Recipes> query = getSession().createQuery(orderCriteriaQuery);
        try {
            return query.getResultList();
        }catch (NoResultException nre){
            log.error("No recipes exists.");
        }

        return new ArrayList<>();
    }

    public void update(Recipes recipe) {
        getSession().update(recipe);
    }

    public long create(Recipes recipe) {
        Serializable newId = getSession().save(recipe);
        return getSession().get(Recipes.class, newId).getId();
    }

    public void delete(long id) {
        Recipes recipe = findByRecipeId(id);
        getSession().delete(recipe);
    }
}
