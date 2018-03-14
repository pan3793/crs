package pc.crs.common.jpa

import javax.persistence.criteria.*

abstract class AbstractExpression<T> : Criterion<T> {
    abstract override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate

    fun parseExpression(root: Root<T>, fieldName: String): Path<T> {
        return if (fieldName.contains(".")) {
            val names = fieldName.split(".")
            var ex = root.get<Any>(names[0])
            for (i in 1 until names.size - 1) {
                ex = ex!!.get<Any>(names[i])
            }
            ex!!.get<T>(names[names.size - 1])
        } else {
            root.get<T>(fieldName)
        }
    }
}