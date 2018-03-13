package pc.crs.common.jpa

import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class Criteria<T> : Specification<T> {

    private val criterions = ArrayList<Criterion<T>>()

    fun add(criterion: Criterion<T>?) {
        criterion?.let { criterions.add(criterion) }
    }

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, builder: CriteriaBuilder): Predicate {
        return builder.and(*criterions.fold(ArrayList<Predicate>()) { predicates, criterion -> predicates.apply { add(criterion.toPredicate(root, query, builder)) } }.toTypedArray())
    }
}