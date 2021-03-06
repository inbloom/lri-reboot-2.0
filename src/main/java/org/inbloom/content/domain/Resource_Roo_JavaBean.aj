// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import java.util.List;
import java.util.Set;
import org.inbloom.content.domain.Activity;
import org.inbloom.content.domain.AgeRange;
import org.inbloom.content.domain.Alignment;
import org.inbloom.content.domain.Audience;
import org.inbloom.content.domain.Interactivity;
import org.inbloom.content.domain.Lang;
import org.inbloom.content.domain.LearningResource;
import org.inbloom.content.domain.Resource;
import org.inbloom.content.domain.ResourcePartyPartyType;
import org.inbloom.content.domain.Tag;
import org.inbloom.content.domain.Use;

privileged aspect Resource_Roo_JavaBean {
    
    public String Resource.getName() {
        return this.name;
    }
    
    public void Resource.setName(String name) {
        this.name = name;
    }
    
    public String Resource.getExternalGUID() {
        return this.externalGUID;
    }
    
    public void Resource.setExternalGUID(String externalGUID) {
        this.externalGUID = externalGUID;
    }
    
    public String Resource.getURL() {
        return this.URL;
    }
    
    public void Resource.setURL(String URL) {
        this.URL = URL;
    }
    
    public String Resource.getDescription() {
        return this.description;
    }
    
    public void Resource.setDescription(String description) {
        this.description = description;
    }
    
    public String Resource.getCopyrightYear() {
        return this.copyrightYear;
    }
    
    public void Resource.setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }
    
    public String Resource.getUseRightsURL() {
        return this.useRightsURL;
    }
    
    public void Resource.setUseRightsURL(String useRightsURL) {
        this.useRightsURL = useRightsURL;
    }
    
    public String Resource.getIsBasedOnURL() {
        return this.isBasedOnURL;
    }
    
    public void Resource.setIsBasedOnURL(String isBasedOnURL) {
        this.isBasedOnURL = isBasedOnURL;
    }
    
    public String Resource.getTimeRequired() {
        return this.timeRequired;
    }
    
    public void Resource.setTimeRequired(String timeRequired) {
        this.timeRequired = timeRequired;
    }
    
    public Lang Resource.getLang() {
        return this.lang;
    }
    
    public void Resource.setLang(Lang lang) {
        this.lang = lang;
    }
    
    public List<Interactivity> Resource.getInteractivity() {
        return this.interactivity;
    }
    
    public void Resource.setInteractivity(List<Interactivity> interactivity) {
        this.interactivity = interactivity;
    }
    
    public List<LearningResource> Resource.getLearningResource() {
        return this.learningResource;
    }
    
    public void Resource.setLearningResource(List<LearningResource> learningResource) {
        this.learningResource = learningResource;
    }
    
    public List<Audience> Resource.getAudience() {
        return this.audience;
    }
    
    public void Resource.setAudience(List<Audience> audience) {
        this.audience = audience;
    }
    
    public List<Tag> Resource.getTag() {
        return this.tag;
    }
    
    public void Resource.setTag(List<Tag> tag) {
        this.tag = tag;
    }
    
    public List<Use> Resource.getUse() {
        return this.use;
    }
    
    public void Resource.setUse(List<Use> use) {
        this.use = use;
    }
    
    public List<AgeRange> Resource.getAgeRange() {
        return this.ageRange;
    }
    
    public void Resource.setAgeRange(List<AgeRange> ageRange) {
        this.ageRange = ageRange;
    }
    
    public String Resource.getSourceText() {
        return this.sourceText;
    }
    
    public void Resource.setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }
    
    public Set<Alignment> Resource.getAlignment() {
        return this.alignment;
    }
    
    public void Resource.setAlignment(Set<Alignment> alignment) {
        this.alignment = alignment;
    }
    
    public Set<ResourcePartyPartyType> Resource.getResourcePartyPartyType() {
        return this.resourcePartyPartyType;
    }
    
    public void Resource.setResourcePartyPartyType(Set<ResourcePartyPartyType> resourcePartyPartyType) {
        this.resourcePartyPartyType = resourcePartyPartyType;
    }
    
    public Set<Activity> Resource.getActivity() {
        return this.activity;
    }
    
    public void Resource.setActivity(Set<Activity> activity) {
        this.activity = activity;
    }
    
}
