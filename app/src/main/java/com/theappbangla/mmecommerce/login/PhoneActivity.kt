package com.theappbangla.mmecommerce.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.theappbangla.mmecommerce.R
import kotlinx.android.synthetic.main.activity_phone.*

class PhoneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        btn_send_code.setOnClickListener {
            val intent = Intent(this, PhoneVerificationActivity::class.java)
            intent.putExtra(PhoneVerificationActivity.EXTRA_PHONE, tv_phone.text.toString())
            startActivity(intent)
            finish()
        }
    }
}
