package com.example.sadanime.modulo.principal.viewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sadanime.helper.Resource
import com.example.sadanime.helper.Status
import com.example.sadanime.modulo.principal.model.PrincipalModel
import com.example.sadanime.modulo.principal.model.pojo.LatestAnimes
import kotlinx.coroutines.Dispatchers

class PrincipalViewModel : ViewModel(){

    private val TAG = this::class.java.name
    private var model = PrincipalModel()

    fun getListAnimeEstreno() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = model.getListAnimeEstreno()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
