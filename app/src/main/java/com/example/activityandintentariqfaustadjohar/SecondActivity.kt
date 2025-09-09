package com.example.activityandintentariqfaustadjohar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
// Remove ViewCompat and WindowInsetsCompat if they are truly unused after changes
// import androidx.core.view.ViewCompat
// import androidx.core.view.WindowInsetsCompat
import com.example.activityandintentariqfaustadjohar.databinding.ActivitySecondBinding // Import the binding class

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var currentName: String? = null // Store the name received from MainActivity

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Memeriksa result code
            if (result.resultCode == Activity.RESULT_OK) {
                // Mengambil data Intent
                val data = result.data
                // Mendapatkan alamat dari data Intent
                // val returnedName = data?.getStringExtra(MainActivity.EXTRA_NAME) // Jika ThirdActivity juga mengembalikan nama
                val address = data?.getStringExtra(EXTRA_ADDRESS)

                // Menetapkan teks di TextView
                // Gunakan currentName yang disimpan dari intent awal dan address dari hasil
                binding.txtName.text = "${currentName ?: "Nama tidak tersedia"} beralamat di ${address ?: "alamat tidak tersedia"}"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge() // Consider if this is needed or if manual inset handling is preferred

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentName = intent.getStringExtra(MainActivity.EXTRA_NAME) // Simpan nama awal

        with(binding) {
            txtName.text = currentName ?: "Nama tidak ada" // Tampilkan nama awal

            btnToThirdActivity.setOnClickListener {
                val intentToThird = Intent(this@SecondActivity, ThirdActivity::class.java).apply {
                    putExtra(MainActivity.EXTRA_NAME, currentName) // Kirim nama awal ke ThirdActivity
                }
                launcher.launch(intentToThird)
            }
        }
    }

    companion object {
        const val EXTRA_ADDRESS = "extra_address"
        // EXTRA_NAME diakses melalui MainActivity.EXTRA_NAME
    }
}
