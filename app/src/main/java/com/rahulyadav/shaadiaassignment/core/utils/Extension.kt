package com.rahulyadav.shaadiaassignment.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rahulyadav.shaadiaassignment.R
import android.view.View
import android.widget.TextView
import com.rahulyadav.shaadiaassignment.domain.entity.MatchState

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.shadi_place_holder)
                .error(R.drawable.ic_error)
        )
        .into(this)
}




fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun TextView.setMatchStateTextAndBackground(state: String?) {
    when (state) {
        MatchState.ACCEPTED.value -> {
            text = MatchState.ACCEPTED.value
            setBackgroundResource(R.drawable.curved_green_rectangle)
        }

        MatchState.DECLINED.value -> {
            text = MatchState.DECLINED.value
            setBackgroundResource(R.drawable.curved_red_rectangle)
        }
        else -> {
            text = ""
            background = null
        }
    }
}


fun TextView.updateMatchStateVisibility(state: String?): Boolean {
    return if (state == MatchState.ACCEPTED.value || state == MatchState.DECLINED.value) {
        show()
        true
    } else {
        gone()
        false
    }
}


