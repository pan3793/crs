package pc.crs.common.jpa

import pc.crs.common.exception.CriterionException
import pc.crs.common.jpa.Criterion.Operator.*
import javax.persistence.criteria.*

class ComparableExpression<T, Y : Comparable<Y>>(private val fieldName: String,
                                                 private val value: Y,
                                                 private val operator: Criterion.Operator) : AbstractExpression<T>() {

    @Suppress("UNCHECKED_CAST")
    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate {

        val expression = parseExpression(root, fieldName)

        return when (operator) {
            LT -> builder.lessThan<Y>(expression as Expression<out Y>, value)
            GT -> builder.greaterThan<Y>(expression as Expression<out Y>, value)
            LTE -> builder.lessThanOrEqualTo<Y>(expression as Expression<out Y>, value)
            GTE -> builder.greaterThanOrEqualTo<Y>(expression as Expression<out Y>, value)
            else -> throw CriterionException("${this.javaClass.simpleName}不支持操作符$operator")
        }
    }
}