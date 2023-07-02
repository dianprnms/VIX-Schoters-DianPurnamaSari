package com.example.vix_schoters_dianpurnamasari.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.vix_schoters_dianpurnamasari.databinding.FragmentDetailBinding
import com.example.vix_schoters_dianpurnamasari.model.Article
import com.example.vix_schoters_dianpurnamasari.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detail : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var articleId: String? = null
    private var judul: String? = null
    private var gambar: String? = null
    private var deskripsi: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleId = it.getString(ARG_ARTICLE_ID)
            judul = it.getString("judul")
            gambar = it.getString("gambar")
            deskripsi = it.getString("deskripsi")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        articleId?.let { id ->
            detailViewModel.loadArticle(id)
        }

        detailViewModel.article.observe(viewLifecycleOwner, Observer { article ->
            article?.let { bindData(it) }
        })

        judul?.let { judul ->
            binding.titleTextView.text = judul
        }

        gambar?.let { gambar ->
            Glide.with(requireContext())
                .load(gambar)
                .into(binding.imageView)
        }

        deskripsi?.let { deskripsi ->
            binding.descriptionTextView.text = deskripsi
        }

    }

    private fun bindData(article: Article) {
        binding.titleTextView.text = article.title
        binding.descriptionTextView.text = article.description
        Glide.with(requireContext())
            .load(article.urlToImage)
            .into(binding.imageView)
    }

    companion object {
        private const val ARG_ARTICLE_ID = "articleId"

        fun newInstance(articleId: String, judul: String, gambar: String, deskripsi: String): Detail {
            val fragment = Detail()
            val args = Bundle().apply {
                putString(ARG_ARTICLE_ID, articleId)
                putString("judul", judul)
                putString("gambar", gambar)
                putString("deskripsi", deskripsi)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
