// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.inbloom.content.domain.AlignmentType;
import org.inbloom.content.domain.AlignmentTypeDataOnDemand;
import org.inbloom.content.domain.AlignmentTypeIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AlignmentTypeIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AlignmentTypeIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AlignmentTypeIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: AlignmentTypeIntegrationTest: @Transactional;
    
    @Autowired
    AlignmentTypeDataOnDemand AlignmentTypeIntegrationTest.dod;
    
    @Test
    public void AlignmentTypeIntegrationTest.testCountAlignmentTypes() {
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to initialize correctly", dod.getRandomAlignmentType());
        long count = AlignmentType.countAlignmentTypes();
        Assert.assertTrue("Counter for 'AlignmentType' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AlignmentTypeIntegrationTest.testFindAlignmentType() {
        AlignmentType obj = dod.getRandomAlignmentType();
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to provide an identifier", id);
        obj = AlignmentType.findAlignmentType(id);
        Assert.assertNotNull("Find method for 'AlignmentType' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'AlignmentType' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AlignmentTypeIntegrationTest.testFindAllAlignmentTypes() {
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to initialize correctly", dod.getRandomAlignmentType());
        long count = AlignmentType.countAlignmentTypes();
        Assert.assertTrue("Too expensive to perform a find all test for 'AlignmentType', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<AlignmentType> result = AlignmentType.findAllAlignmentTypes();
        Assert.assertNotNull("Find all method for 'AlignmentType' illegally returned null", result);
        Assert.assertTrue("Find all method for 'AlignmentType' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AlignmentTypeIntegrationTest.testFindAlignmentTypeEntries() {
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to initialize correctly", dod.getRandomAlignmentType());
        long count = AlignmentType.countAlignmentTypes();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<AlignmentType> result = AlignmentType.findAlignmentTypeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'AlignmentType' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'AlignmentType' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AlignmentTypeIntegrationTest.testFlush() {
        AlignmentType obj = dod.getRandomAlignmentType();
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to provide an identifier", id);
        obj = AlignmentType.findAlignmentType(id);
        Assert.assertNotNull("Find method for 'AlignmentType' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAlignmentType(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'AlignmentType' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AlignmentTypeIntegrationTest.testMergeUpdate() {
        AlignmentType obj = dod.getRandomAlignmentType();
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to provide an identifier", id);
        obj = AlignmentType.findAlignmentType(id);
        boolean modified =  dod.modifyAlignmentType(obj);
        Integer currentVersion = obj.getVersion();
        AlignmentType merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'AlignmentType' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AlignmentTypeIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to initialize correctly", dod.getRandomAlignmentType());
        AlignmentType obj = dod.getNewTransientAlignmentType(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'AlignmentType' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'AlignmentType' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AlignmentTypeIntegrationTest.testRemove() {
        AlignmentType obj = dod.getRandomAlignmentType();
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'AlignmentType' failed to provide an identifier", id);
        obj = AlignmentType.findAlignmentType(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'AlignmentType' with identifier '" + id + "'", AlignmentType.findAlignmentType(id));
    }
    
}
