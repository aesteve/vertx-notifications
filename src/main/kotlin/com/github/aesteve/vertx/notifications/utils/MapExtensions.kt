package com.github.aesteve.vertx.notifications.utils

fun<K, V> Map<K, V>.join(keyValueSep: String, entrySep: String): String {
    if (this.isEmpty()) return "[]"
    val sb = StringBuilder("[")
    this.forEach { entry ->
        sb.append(entry.key.toString() + keyValueSep + entry.value)
        sb.append(entrySep)
    }
    sb.removeSuffix(entrySep)
    sb.append("]")
    return sb.toString()
}

fun<K, V> MutableMap<K, Set<V>>.add(key: K, value: V) {
    val col = this[key].orEmpty() + value
    this[key] = col
}

fun<K, V> MutableMap<K, Set<V>>.remove(key: K, value: V) {
    val col = this[key].orEmpty() - value
    if (col.isEmpty()) {
        remove(key)
    }
}