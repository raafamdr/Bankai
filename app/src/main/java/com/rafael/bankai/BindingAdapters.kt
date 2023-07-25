package com.rafael.bankai

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafael.bankai.network.CharactersData
import com.rafael.bankai.ui.BankaiApiStatus
import com.rafael.bankai.ui.CharacterListAdapter

@BindingAdapter("listCharacters")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<com.rafael.bankai.network.Character>?) {
    val adapter = recyclerView.adapter as CharacterListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("starRating")
fun bindRating(ratingBar: RatingBar, rating: Float) {
    // Convert the rating from '10' scale to '5' scale
    val rating5 = rating / 2.0f
    ratingBar.rating = rating5
}

@BindingAdapter("bankaiApiStatus")
fun bindStatus(statusImageView: ImageView, status: BankaiApiStatus) {
    when (status) {
        BankaiApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        BankaiApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        BankaiApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("textStatus")
fun bindQuote(statusTextView: TextView, status: BankaiApiStatus) {
    when (status) {
        BankaiApiStatus.LOADING -> {
            statusTextView.visibility = View.INVISIBLE
        }
        BankaiApiStatus.ERROR -> {
            statusTextView.visibility = View.INVISIBLE
        }
        BankaiApiStatus.DONE -> {
            statusTextView.visibility = View.VISIBLE
        }
    }
}