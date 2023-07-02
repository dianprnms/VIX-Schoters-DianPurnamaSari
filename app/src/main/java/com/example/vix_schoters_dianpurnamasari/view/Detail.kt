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
import com.example.vix_schoters_dianpurnamasari.databinding.FragmentSourceBinding
import com.example.vix_schoters_dianpurnamasari.model.Article
import com.example.vix_schoters_dianpurnamasari.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detail : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel

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

        val articleId = arguments?.getString(ARG_ARTICLE_ID)
        if (articleId != null) {
            detailViewModel.loadArticle(articleId)
        }

        detailViewModel.article.observe(viewLifecycleOwner, Observer { article ->
            if (article != null) {
                bindData(article)
            }
        })
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

        fun newInstance(articleId: String): Detail {
            val fragment = Detail()
            val args = Bundle()
            args.putString(ARG_ARTICLE_ID, articleId)
            fragment.arguments = args
            return fragment
        }
    }
}
