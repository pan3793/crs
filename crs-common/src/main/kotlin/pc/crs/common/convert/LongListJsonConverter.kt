package pc.crs.common.convert

import javax.persistence.AttributeConverter

class StringListJsonConverter : AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(attribute: List<String>): String {
        return null
    }

    override fun convertToEntityAttribute(dbData: String): List<String> {
        return dbData.split(',').filterNot { it.isBlank() }.map { it.toLong() }
    }
}
