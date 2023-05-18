package com.tempoagora.data.model.extensions

interface Mapper<S, T> {
    fun map(source: S): T
}