// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.inbloom.content.domain.Alignment;
import org.inbloom.content.domain.AlignmentDataOnDemand;
import org.inbloom.content.domain.AlignmentIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AlignmentIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AlignmentIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AlignmentIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: AlignmentIntegrationTest: @Transactional;
    
    @Autowired
    AlignmentDataOnDemand AlignmentIntegrationTest.dod;
    
    @Test
    public void AlignmentIntegrationTest.testCountAlignments() {
        Assert.assertNotNull("Data on demand for 'Alignment' failed to initialize correctly", dod.getRandomAlignment());
        long count = Alignment.countAlignments();
        Assert.assertTrue("Counter for 'Alignment' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AlignmentIntegrationTest.testFindAlignment() {
        Alignment obj = dod.getRandomAlignment();
        Assert.assertNotNull("Data on demand for 'Alignment' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Alignment' failed to provide an identifier", id);
        obj = Alignment.findAlignment(id);
        Assert.assertNotNull("Find method for 'Alignment' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Alignment' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AlignmentIntegrationTest.testFindAllAlignments() {
        Assert.assertNotNull("Data on demand for 'Alignment' failed to initialize correctly", dod.getRandomAlignment());
        long count = Alignment.countAlignments();
        Assert.assertTrue("Too expensive to perform a find all test for 'Alignment', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Alignment> result = Alignment.findAllAlignments();
        Assert.assertNotNull("Find all method for 'Alignment' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Alignment' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AlignmentIntegrationTest.testFindAlignmentEntries() {
        Assert.assertNotNull("Data on demand for 'Alignment' failed to initialize correctly", dod.getRandomAlignment());
        long count = Alignment.countAlignments();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Alignment> result = Alignment.findAlignmentEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Alignment' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Alignment' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AlignmentIntegrationTest.testFlush() {
        Alignment obj = dod.getRandomAlignment();
        Assert.assertNotNull("Data on demand for 'Alignment' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Alignment' failed to provide an identifier", id);
        obj = Alignment.findAlignment(id);
        Assert.assertNotNull("Find method for 'Alignment' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAlignment(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Alignment' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AlignmentIntegrationTest.testMergeUpdate() {
        Alignment obj = dod.getRandomAlignment();
        Assert.assertNotNull("Data on demand for 'Alignment' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Alignment' failed to provide an identifier", id);
        obj = Alignment.findAlignment(id);
        boolean modified =  dod.modifyAlignment(obj);
        Integer currentVersion = obj.getVersion();
        Alignment merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Alignment' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AlignmentIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Alignment' failed to initialize correctly", dod.getRandomAlignment());
        Alignment obj = dod.getNewTransientAlignment(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Alignment' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Alignment' identifier to be null", obj.getId());
        try {
            obj.persist();
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        obj.flush();
        Assert.assertNotNull("Expected 'Alignment' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AlignmentIntegrationTest.testRemove() {
        Alignment obj = dod.getRandomAlignment();
        Assert.assertNotNull("Data on demand for 'Alignment' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Alignment' failed to provide an identifier", id);
        obj = Alignment.findAlignment(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Alignment' with identifier '" + id + "'", Alignment.findAlignment(id));
    }
    
}
