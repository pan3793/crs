package pc.crs.common.convert

import javax.persistence.AttributeConverter

class LongListJsonConverter : AttributeConverter<List<Long>, String> {
    override fun convertToDatabaseColumn(attribute: List<Long>): String {
        return attribute.joinToString(separator = ",") { it.toString() }
    }

    override fun convertToEntityAttribute(dbData: String): List<Long> {
        return dbData.split(',').filterNot { it.isBlank() }.map { it.trim().toLong() }
    }
}
