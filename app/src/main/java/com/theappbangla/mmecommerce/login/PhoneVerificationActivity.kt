package com.theappbangla.mmecommerce.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.theappbangla.mmecommerce.R
import com.theappbangla.data.auth.FirebasePhoneAuth
import kotlinx.android.synthetic.main.activity_phone_verification.*

class PhoneVerificationActivity : AppCompatActivity(), FirebasePhoneAuth.PhoneAuthCallback {

    companion object {
        const val EXTRA_PHONE = "EXTRA_PHONE"
    }

    lateinit var firebasePhoneAuth : FirebasePhoneAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)

        val phone = intent.getStringExtra(EXTRA_PHONE)
        tv_phone.text = phone

        firebasePhoneAuth = FirebasePhoneAuth(this, "", phone, this)
        firebasePhoneAuth.sendCode()

    }


    override fun onCodeSent(verificationId: String?) {
        showToast("code sent")
    }

    override fun onSuccess(uid: String?) {
        updateUi(true)
        showToast("logged in!")
    }

    override fun onInvalidRequest() {
        updateUi(true)
        Log.d("Invalid Request", "")
        showToast("onInvalidRequest")
    }

    override fun onTooManyRequests() {
        updateUi(true)
        showToast("Too Many Requests")
    }

    override fun onInvalidVerificationCode() {
        updateUi(true)
        showToast("Invalid Verification Code")
    }

    override fun onUnknownError() {
        updateUi(true)
        showToast("Unknown Error")
    }

    fun onClick(view: View) {

        when(view.id) {
            R.id.btn_verify -> {
                if (!btn_verify.isEnabled) return
                updateUi(false)
                firebasePhoneAuth.verifyCode(et_verification_code.text.toString())
            }

            R.id.tvResendCode -> {

            }

            else -> {
                showToast("not implemented")
            }
        }
    }

    private fun showToast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun updateUi(flag: Boolean) {
        btn_verify.isEnabled = flag
        if (flag)
            btn_verify.text = "Verify"
        else
            btn_verify.text = "Verifying"
    }

}
