package com.tempoagora.data.mapper.extensions

interface Mapper<S, T> {
    fun map(source: S): T
}