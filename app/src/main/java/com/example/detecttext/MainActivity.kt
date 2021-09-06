package com.example.detecttext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentifier

class MainActivity : AppCompatActivity() {
    lateinit var etLangText:EditText
    lateinit var btnCheckNow:Button
    lateinit var  tvResult:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLangText = findViewById(R.id.etLangText)
        btnCheckNow = findViewById(R.id.btnCheckNow)
        tvResult = findViewById(R.id.tvResult)

        btnCheckNow.setOnClickListener{
            val langText:String = etLangText.text.toString()
            if (langText.equals("")) {
                Toast.makeText(this@MainActivity, "Please enter text", Toast.LENGTH_LONG).show()
            }else{
                detectLang(langText)
            }
        }
    }

    private fun detectLang(langText: String) {
        val languageIdentifier:LanguageIdentifier=LanguageIdentification.getClient()

       languageIdentifier.identifyLanguage(langText)
           .addOnSuccessListener{languageCode->
           if(languageCode == "und"){
               tvResult.text="Can't identify language";
           } else {
               tvResult.text="Language: $languageCode";
           }
       }
           .addOnFailureListener{
               tvResult.text= "Exception ${it.message}"
           }
    }
}