package com.example.user

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.user.adapter.RvAdapter
import com.example.user.api.Retrofit
import com.example.user.api.UserService
import com.example.user.databinding.ActivityMainBinding
import com.example.user.repository.UserRepository
import com.example.user.viewmodel.UserViewModel
import com.example.user.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Loading")
        builder.setMessage("Wait")
        val dialog = builder.create()

        val service = Retrofit.getInstance().create(UserService::class.java)
        val repository = UserRepository(service)
        viewModel = ViewModelProvider(this,ViewModelFactory(repository)).get(UserViewModel::class.java)

       viewModel.user.observe(this, Observer {
           adapter = RvAdapter(this,it)
           binding.rvUser.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
           binding.rvUser.adapter = adapter
        })
        viewModel.isLoading.observe(this, Observer {
            if(it){
                dialog.show()
            }else{
                dialog.dismiss()
            }
        })

        viewModel.error.observe(this, Observer {
           it.let {
               Toast.makeText(this, it, Toast.LENGTH_LONG).show()
           }
        })

    }


}