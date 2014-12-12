/*
 * The MIT License
 *
 * Copyright 2014 Ondřej Fibich.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kopaid.example.wildfly.postgis;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB DAO for {@link Location} entity.
 *
 * @author Ondřej Fibich <ondrej.fibich@gmail.com>
 */
@LocalBean
@Stateless
public class LocationEjbDao {

    /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Creates location.
     *
     * @param location
     */
    public void create(Location location) {
        em.persist(location);
    }

    /**
     * Finds location with given ID.
     *
     * @param id
     * @return location entity
     */
    public Location find(Long id) {
        return em.find(Location.class, id);
    }

    /**
     * Count all locations.
     *
     * @return count
     */
    public Long countAll() {
        return em.createQuery("SELECT COUNT(l) FROM Location l", Long.class)
                .getSingleResult();
    }

}
