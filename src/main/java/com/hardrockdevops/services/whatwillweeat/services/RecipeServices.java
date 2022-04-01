package com.hardrockdevops.services.whatwillweeat.services;

import com.hardrockdevops.services.whatwillweeat.model.Recipe;
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

    public Recipe findByRecipeId(long id) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Recipe> orderCriteriaQuery = cb.createQuery(Recipe.class);
        Root<Recipe> root = orderCriteriaQuery.from(Recipe.class);
        Predicate predicate = cb.equal(root.get("id"), id);
        orderCriteriaQuery.select(root).where(predicate);
        Query<Recipe> query = getSession().createQuery(orderCriteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException nre){
            log.error("No recipe exists with id " + id);
        }

        return null;
    }

    public List<Recipe> getAllRecipes() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Recipe> orderCriteriaQuery = cb.createQuery(Recipe.class);
        Query<Recipe> query = getSession().createQuery(orderCriteriaQuery);
        try {
            return query.getResultList();
        }catch (NoResultException nre){
            log.error("No recipes exists.");
        }

        return new ArrayList<>();
    }

    public void update(Recipe recipe) {
        getSession().update(recipe);
    }

    public long create(Recipe recipe) {
        Serializable newId = getSession().save(recipe);
        return getSession().get(Recipe.class, newId).getId();
    }

    public void delete(long id) {
        Recipe recipe = findByRecipeId(id);
        getSession().delete(recipe);
    }
}
