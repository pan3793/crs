package pc.crs.common.jpa

import pc.crs.common.jpa.Criterion.Operator.*
import javax.persistence.criteria.*

class SimpleExpression<T, Y : Comparable<Y>>(val fieldName: String, val value: Y, val operator: Criterion.Operator) : Criterion<T> {

    @Suppress("UNCHECKED_CAST")
    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate {

        val expression: Path<T> =
                if (fieldName.contains(".")) {
                    val names = fieldName.split(".")
                    var ex = root.get<Any>(names[0])
                    for (i in 1 until names.size - 1) {
                        ex = ex!!.get<Any>(names[i])
                    }
                    ex!!.get<T>(names[names.size - 1])
                } else {
                    root.get<T>(fieldName)
                }

        return when (operator) {
            EQ -> builder.equal(expression, value)
            NE -> builder.notEqual(expression, value)
            LIKE -> builder.like(expression as Expression<String>, "%$value%")
            LT -> builder.lessThan<Y>(expression as Expression<out Y>, value)
            GT -> builder.greaterThan<Y>(expression as Expression<out Y>, value)
            LTE -> builder.lessThanOrEqualTo<Y>(expression as Expression<out Y>, value)
            GTE -> builder.greaterThanOrEqualTo<Y>(expression as Expression<out Y>, value)
            INN -> builder.isNotNull(expression)
            IN -> builder.isNull(expression)
            else -> throw RuntimeException()
        }
    }
}