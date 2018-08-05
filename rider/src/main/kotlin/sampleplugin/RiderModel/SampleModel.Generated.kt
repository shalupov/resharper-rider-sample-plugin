@file:Suppress("PackageDirectoryMismatch", "UnusedImport", "unused", "LocalVariableName")
package com.jetbrains.rider.model

import com.jetbrains.rider.framework.*
import com.jetbrains.rider.framework.base.*
import com.jetbrains.rider.framework.impl.*

import com.jetbrains.rider.util.lifetime.*
import com.jetbrains.rider.util.reactive.*
import com.jetbrains.rider.util.string.*
import com.jetbrains.rider.util.trace
import kotlin.reflect.KClass

import java.io.*
import java.util.*
import java.net.*



class SampleModel private constructor(
    private val _myString : RdOptionalProperty<String>,
    private val _myBool : RdOptionalProperty<Boolean>,
    private val _myEnum : RdProperty<MyEnum?>,
    private val _data : RdMap<String, String>,
    private val _myStructure : RdSignal<MyStructure>
) : RdExtBase() {
    //companion
    
    companion object : ISerializersOwner {
        
        override fun registerSerializersCore(serializers : ISerializers) {
            serializers.register(MyEnum.marshaller)
            serializers.register(MyStructure)
        }
        
        
        
        private val __MyEnumNullableSerializer = MyEnum.marshaller.nullable()
    }
    override val serializersOwner : ISerializersOwner get() = SampleModel
    override val serializationHash : Long get() = 2011091388562592936L
    
    //fields
    val myString : IOptProperty<String> get() = _myString
    val myBool : IOptProperty<Boolean> get() = _myBool
    val myEnum : IProperty<MyEnum?> get() = _myEnum
    val data : IMutableViewableMap<String, String> get() = _data
    val myStructure : ISignal<MyStructure> get() = _myStructure
    
    //initializer
    init {
        _myString.optimizeNested = true
        _myBool.optimizeNested = true
        _myEnum.optimizeNested = true
        _data.optimizeNested = true
    }
    
    init {
        bindableChildren.add("myString" to _myString)
        bindableChildren.add("myBool" to _myBool)
        bindableChildren.add("myEnum" to _myEnum)
        bindableChildren.add("data" to _data)
        bindableChildren.add("myStructure" to _myStructure)
    }
    
    //secondary constructor
    internal constructor(
    ) : this (
        RdOptionalProperty<String>(FrameworkMarshallers.String),
        RdOptionalProperty<Boolean>(FrameworkMarshallers.Bool),
        RdProperty<MyEnum?>(null, __MyEnumNullableSerializer),
        RdMap<String, String>(FrameworkMarshallers.String, FrameworkMarshallers.String),
        RdSignal<MyStructure>(MyStructure)
    )
    
    //equals trait
    //hash code trait
    //pretty print
    override fun print(printer: PrettyPrinter) {
        printer.println("SampleModel (")
        printer.indent {
            print("myString = "); _myString.print(printer); println()
            print("myBool = "); _myBool.print(printer); println()
            print("myEnum = "); _myEnum.print(printer); println()
            print("data = "); _data.print(printer); println()
            print("myStructure = "); _myStructure.print(printer); println()
        }
        printer.print(")")
    }
}
val Solution.sampleModel get() = getOrCreateExtension("sampleModel", ::SampleModel)



enum class MyEnum {
    FirstValue,
    SecondValue;
    
    companion object { val marshaller = FrameworkMarshallers.enum<MyEnum>() }
}


data class MyStructure (
    val projectFile : String,
    val target : String
) : IPrintable {
    //companion
    
    companion object : IMarshaller<MyStructure> {
        override val _type: KClass<MyStructure> = MyStructure::class
        
        @Suppress("UNCHECKED_CAST")
        override fun read(ctx: SerializationCtx, buffer: AbstractBuffer): MyStructure {
            val projectFile = buffer.readString()
            val target = buffer.readString()
            return MyStructure(projectFile, target)
        }
        
        override fun write(ctx: SerializationCtx, buffer: AbstractBuffer, value: MyStructure) {
            buffer.writeString(value.projectFile)
            buffer.writeString(value.target)
        }
        
    }
    //fields
    //initializer
    //secondary constructor
    //equals trait
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        
        other as MyStructure
        
        if (projectFile != other.projectFile) return false
        if (target != other.target) return false
        
        return true
    }
    //hash code trait
    override fun hashCode(): Int {
        var __r = 0
        __r = __r*31 + projectFile.hashCode()
        __r = __r*31 + target.hashCode()
        return __r
    }
    //pretty print
    override fun print(printer: PrettyPrinter) {
        printer.println("MyStructure (")
        printer.indent {
            print("projectFile = "); projectFile.print(printer); println()
            print("target = "); target.print(printer); println()
        }
        printer.print(")")
    }
}
