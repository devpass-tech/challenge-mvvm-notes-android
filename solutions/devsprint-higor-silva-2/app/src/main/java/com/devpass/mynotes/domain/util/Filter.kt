package com.devpass.mynotes.domain.util

sealed class Filter {
    object Title : Filter()
    object Date : Filter()
    object Color : Filter()
}
