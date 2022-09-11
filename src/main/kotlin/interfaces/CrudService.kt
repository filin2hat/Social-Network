package interfaces

import classes.Post

interface CrudService<T> {
    fun add(elem: T?): T

    fun update(elem: T): Boolean

    fun delete(elem: T): Boolean

    fun print(elem: T)
}