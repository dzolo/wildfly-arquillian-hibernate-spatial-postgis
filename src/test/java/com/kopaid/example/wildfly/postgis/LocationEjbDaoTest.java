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

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.WKTReader;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 * Tests for LocationEjbDao class with Arquillian and embedded WildFly.
 *
 * @author Ondřej Fibich <ondrej.fibich@gmail.com>
 */
@RunWith(Arquillian.class)
public class LocationEjbDaoTest {

    /**
     * Test deployment.
     *
     * @return web archive
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(Location.class.getPackage())
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource("postgis-ds.xml")
            .addAsWebInfResource("jboss-deployment-structure.xml");
    }

    /**
     * Tested instance.
     */
    @EJB private LocationEjbDao dao;

    /**
     * Before test.
     */
    @org.junit.Before
    public void beforeTest() {
        assertNotNull("LocationEjbDao not injected", dao);
    }

    /**
     * Test of class LocationEjbDao.
     */
    @org.junit.Test
    public void testCreate() throws Exception {
        assertEquals(new Long(0L), dao.countAll());
        // create location
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
        WKTReader wktr = new WKTReader(gf);
        dao.create(new Location((Point) wktr.read("POINT(12 10)")));
        // check created
        assertEquals(new Long(1L), dao.countAll());
    }

}