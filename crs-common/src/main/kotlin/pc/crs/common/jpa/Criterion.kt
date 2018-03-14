package pc.crs.common.jpa

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

interface Criterion<T> {
    enum class Operator {
        EQ, NEQ, LIKE, GT, LT, GTE, LTE, AND, OR, IN, NIN
    }

    fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate
}