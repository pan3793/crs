package pc.crs.common.jpa

import pc.crs.common.exception.CriterionException
import pc.crs.common.jpa.Criterion.Operator
import pc.crs.common.jpa.Criterion.Operator.AND
import pc.crs.common.jpa.Criterion.Operator.OR
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class LogicalExpression<T>(private val criterions: Array<out Criterion<T>>,
                           private val operator: Operator) : Criterion<T> {

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate {
        val predicates = criterions.map { it.toPredicate(root, query, builder) }
        return when (operator) {
            AND -> builder.and(*predicates.toTypedArray())
            OR -> builder.or(*predicates.toTypedArray())
            else -> throw CriterionException("${this.javaClass.simpleName}不支持操作符$operator")
        }
    }
}