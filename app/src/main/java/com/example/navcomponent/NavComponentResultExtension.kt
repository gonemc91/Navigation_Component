package com.example.navcomponent

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


/**
 * Send some results to the previous fragment.
 */


typealias ResultListener<T> = (T) -> Unit

fun<T> Fragment.publishResults(key: String, result: T){
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}


/**
 * Listen for screen results.  Results are automatically cleared when the listener receives them.
 *
 */
fun<T> Fragment.listenResult(key: String, listener: ResultListener<T>){
    val liveData =  findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
    liveData?.observe(viewLifecycleOwner){result->
        if(result != null){
            listener(result)
            liveData.value = null
        }

    }

}

