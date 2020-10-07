package com.example.sadanime.modulo.principal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.sadanime.databinding.ActivityPrincipalBinding
import com.example.sadanime.helper.Status
import com.example.sadanime.modulo.login.view.LoginActivity
import com.example.sadanime.modulo.principal.model.pojo.AnimesItem
import com.example.sadanime.root.preferences
import com.example.sadanime.helper.application.Constants.FIREBASE_AUTH
import com.example.sadanime.modulo.principal.viewModel.PrincipalListener
import com.example.sadanime.modulo.principal.viewModel.PrincipalViewModel

class PrincipalActivity : AppCompatActivity(){

    private val TAG = this::class.java.name

    private lateinit var _viewModel    : PrincipalViewModel
    private lateinit var binding       : ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(rootPrincipal)
            _viewModel = ViewModelProvider(this@PrincipalActivity).get()
            viewmodel = _viewModel
            lifecycleOwner = this@PrincipalActivity
            btnSingout.setOnClickListener {
                FIREBASE_AUTH.signOut()
                preferences.clear()
                val intent = Intent(this@PrincipalActivity,  LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        resLastAnimes()

    }

    private fun resLastAnimes() {
        _viewModel.getListAnimeEstreno().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { users ->
                            Log.e(TAG, "resLastAnimes: $users" )
                        }
                    }
                    Status.ERROR -> {
                        makeText(this, it.message, LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        makeText(
                            this,
                            "CARGANDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                            LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        )

    }

}
