package com.david.pattern.criteria;

import java.util.List;

public interface Criteria<T> {
    List<T> meetCriteria(List<T> persons);
}
