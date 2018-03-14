package pc.crs.common.jpa

import pc.crs.common.exception.CriterionException
import pc.crs.common.jpa.Criterion.Operator.*
import javax.persistence.criteria.*

class SimpleExpression<T>(private val fieldName: String,
                          private val value: Any,
                          private val operator: Criterion.Operator) : AbstractExpression<T>() {

    @Suppress("UNCHECKED_CAST")
    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate {

        val expression = parseExpression(root, fieldName)

        return when (operator) {
            EQ -> builder.equal(expression, value)
            NEQ -> builder.notEqual(expression, value)
            LIKE -> builder.like(expression as Expression<String>, "%$value%")
            IN -> (value as Iterable<T>).fold(builder.`in`(expression)) { inPredicate, value -> inPredicate.value(value) }
            NIN -> (value as Iterable<T>).fold(builder.`in`(expression)) { inPredicate, value -> inPredicate.value(value) }.not()
            else -> throw CriterionException("${this.javaClass.simpleName}不支持操作符$operator")
        }
    }
}