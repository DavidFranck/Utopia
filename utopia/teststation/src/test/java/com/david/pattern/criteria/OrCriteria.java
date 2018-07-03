package com.david.pattern.criteria;

import java.util.List;

public class OrCriteria implements PersonCriteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
        List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);

        otherCriteriaItems.forEach(p -> {
            if (!firstCriteriaItems.contains(p)) firstCriteriaItems.add(p);
        });

        return firstCriteriaItems;
    }
}
