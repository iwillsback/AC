package com.example.ac

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name = intent.getStringExtra("name")
        val trait = intent.getStringExtra("trait")
        val traits = intent.getStringArrayListExtra("traits")

        val resultText: TextView = findViewById(R.id.resultText)
        val heroImage: ImageView = findViewById(R.id.heroImage)
        val shareButton: Button = findViewById(R.id.shareButton)
        val linkButton: Button = findViewById(R.id.linkButton)

        val hero = when {
            trait!!.contains("Inteligente") -> "Homem de Ferro"
            traits!!.contains("Força") -> "Hulk"
            traits.contains("Coragem") -> "Capitão América"
            else -> "Viúva Negra"
        }

        resultText.text = "$name, você é como o $hero!"

        // Exibir imagem (coloque imagens em res/drawable com os nomes corretos)
        when (hero) {
            "Homem de Ferro" -> heroImage.setImageResource(R.drawable.ironman)
            "Hulk" -> heroImage.setImageResource(R.drawable.hulk)
            "Capitão América" -> heroImage.setImageResource(R.drawable.cap)
            "Viúva Negra" -> heroImage.setImageResource(R.drawable.blackwidow)
        }

        // Intent para compartilhar
        shareButton.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Eu sou como o $hero! Descubra qual herói você é com o HeroQuiz!")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Compartilhar via"))
        }

        // Intent para abrir link
        linkButton.setOnClickListener {
            val url = when (hero) {
                "Homem de Ferro" -> "https://pt.wikipedia.org/wiki/Homem_de_Ferro"
                "Hulk" -> "https://pt.wikipedia.org/wiki/Hulk"
                "Capitão América" -> "https://pt.wikipedia.org/wiki/Capit%C3%A3o_Am%C3%A9rica"
                "Viúva Negra" -> "https://pt.wikipedia.org/wiki/Vi%C3%BAva_Negra_(personagem)"
                else -> "https://pt.wikipedia.org"
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }
}