package com.devpass.mynotes.domain.util

import androidx.lifecycle.MutableLiveData

class SelfCleaningLiveData<T> : MutableLiveData<T>(){
    override fun onInactive() {
        super.onInactive()
        value = null
    }

}