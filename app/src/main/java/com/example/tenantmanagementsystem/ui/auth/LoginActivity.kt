package com.example.tenantmanagementsystem.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystem.databinding.ActivityLoginBinding
import com.example.tenantmanagementsystem.ui.main.MainActivity

/**
 * Simple login screen. Credentials are stored in SharedPreferences for demo purposes.
 * In a real app you would use a proper auth backend.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // If already logged in, go straight to MainActivity
        val prefs = getSharedPreferences("auth", MODE_PRIVATE)
        if (prefs.getBoolean("is_logged_in", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val savedEmail = prefs.getString("email", null)
            val savedPassword = prefs.getString("password", null)

            if (savedEmail == null || savedPassword == null) {
                Toast.makeText(this, "No account found. Please register first.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email == savedEmail && password == savedPassword) {
                prefs.edit().putBoolean("is_logged_in", true).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
