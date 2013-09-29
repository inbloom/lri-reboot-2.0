// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.inbloom.content.domain.Tag;
import org.inbloom.content.domain.TagDataOnDemand;
import org.springframework.stereotype.Component;

privileged aspect TagDataOnDemand_Roo_DataOnDemand {
    
    declare @type: TagDataOnDemand: @Component;
    
    private Random TagDataOnDemand.rnd = new SecureRandom();
    
    private List<Tag> TagDataOnDemand.data;
    
    public Tag TagDataOnDemand.getNewTransientTag(int index) {
        Tag obj = new Tag();
        setName(obj, index);
        return obj;
    }
    
    public void TagDataOnDemand.setName(Tag obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public Tag TagDataOnDemand.getSpecificTag(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Tag obj = data.get(index);
        Long id = obj.getId();
        return Tag.findTag(id);
    }
    
    public Tag TagDataOnDemand.getRandomTag() {
        init();
        Tag obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Tag.findTag(id);
    }
    
    public boolean TagDataOnDemand.modifyTag(Tag obj) {
        return false;
    }
    
    public void TagDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Tag.findTagEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Tag' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Tag>();
        for (int i = 0; i < 10; i++) {
            Tag obj = getNewTransientTag(i);
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
            data.add(obj);
        }
    }
    
}
