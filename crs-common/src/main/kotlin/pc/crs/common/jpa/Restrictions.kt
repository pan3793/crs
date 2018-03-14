package pc.crs.common.jpa

import pc.crs.common.jpa.Criterion.Operator.*

fun <T> eq(fieldName: String, value: Any?): SimpleExpression<T>? {
    if (value == null || value is String && value.isBlank())
        return null
    return SimpleExpression(fieldName, value, EQ)
}

fun <T> neq(fieldName: String, value: Any?): SimpleExpression<T>? {
    if (value == null || value is String && value.isBlank())
        return null
    return SimpleExpression(fieldName, value, NEQ)
}

fun <T> like(fieldName: String, value: String?): SimpleExpression<T>? {
    if (value == null || value.isBlank())
        return null
    return SimpleExpression(fieldName, value, LIKE)
}

fun <T, Y : Comparable<Y>> gt(fieldName: String, value: Y?): ComparableExpression<T, Y>? {
    if (value == null)
        return null
    return ComparableExpression(fieldName, value, GT)
}

fun <T, Y : Comparable<Y>> lt(fieldName: String, value: Y?): ComparableExpression<T, Y>? {
    if (value == null)
        return null
    return ComparableExpression(fieldName, value, LT)
}

fun <T, Y : Comparable<Y>> gte(fieldName: String, value: Y?): ComparableExpression<T, Y>? {
    if (value == null)
        return null
    return ComparableExpression(fieldName, value, LTE)
}

fun <T, Y : Comparable<Y>> lte(fieldName: String, value: Y?): ComparableExpression<T, Y>? {
    if (value == null)
        return null
    return ComparableExpression(fieldName, value, GTE)
}

fun <T> and(vararg criterions: Criterion<T>): LogicalExpression<T>? {
    return LogicalExpression(criterions, AND)
}

fun <T> or(vararg criterions: Criterion<T>): LogicalExpression<T>? {
    return LogicalExpression(criterions, OR)
}

fun <T> `in`(fieldName: String, value: Collection<*>?): SimpleExpression<T>? {
    if ((value == null || value.isEmpty())) {
        return null
    }
    return SimpleExpression(fieldName, value, IN)
}

fun <T> nin(fieldName: String, value: Collection<*>?): SimpleExpression<T>? {
    if ((value == null || value.isEmpty())) {
        return null
    }
    return SimpleExpression(fieldName, value, NIN)
}


