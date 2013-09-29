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
import org.inbloom.content.domain.Lang;
import org.inbloom.content.domain.LangDataOnDemand;
import org.springframework.stereotype.Component;

privileged aspect LangDataOnDemand_Roo_DataOnDemand {
    
    declare @type: LangDataOnDemand: @Component;
    
    private Random LangDataOnDemand.rnd = new SecureRandom();
    
    private List<Lang> LangDataOnDemand.data;
    
    public Lang LangDataOnDemand.getNewTransientLang(int index) {
        Lang obj = new Lang();
        setCode(obj, index);
        setName(obj, index);
        return obj;
    }
    
    public void LangDataOnDemand.setCode(Lang obj, int index) {
        String code = "code_" + index;
        obj.setCode(code);
    }
    
    public void LangDataOnDemand.setName(Lang obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public Lang LangDataOnDemand.getSpecificLang(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Lang obj = data.get(index);
        Long id = obj.getId();
        return Lang.findLang(id);
    }
    
    public Lang LangDataOnDemand.getRandomLang() {
        init();
        Lang obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Lang.findLang(id);
    }
    
    public boolean LangDataOnDemand.modifyLang(Lang obj) {
        return false;
    }
    
    public void LangDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Lang.findLangEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Lang' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Lang>();
        for (int i = 0; i < 10; i++) {
            Lang obj = getNewTransientLang(i);
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
