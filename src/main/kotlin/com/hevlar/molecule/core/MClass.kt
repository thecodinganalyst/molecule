package com.hevlar.molecule.core

/**
 * Class has properties
 */
open class MClass: MDefinition(
    listOf(
        MDefinition("properties", Data)
    )
)

/**
 * Property is a definition with {type: MType, required: Flag, default: Text}
 */
open class MProperty: MDefinition(
    listOf(
        MDefinition("type", MType),
        MDefinition("required", Flag),
        MDefinition("default", Text)
    )
){
    companion object: Typeable {
        override val name = "MProperty"
        override val parent = MDefinition()

        override fun test(value: String): Typeable {
            return MProperty().test(value)
        }

        override fun parse(value: String): Any? {
            return MProperty().parse(value)
        }
    }
}
