package com.atyourservice.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atyourservice.search.domain.TopExpert
import com.example.atyourservice.R
import com.example.atyourservice.databinding.ExpertCardBinding


class TopExpertsAdapter : RecyclerView.Adapter<TopExpertsAdapter.TopExpertsHolder>() {
    private val topExperts = ArrayList<TopExpert>()

    fun setExperts(allExperts: List<TopExpert>) {
        topExperts.addAll(allExperts)
    }

    class TopExpertsHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ExpertCardBinding.bind(item)
        fun bind(model: TopExpert) = with(binding) {
            expertCardUserImage.setImageResource(model.idPhoto)
            expertCardUserNameText.text = model.userName
            expertCardJobText.text = model.userJob
            expertCardChargeText.text = model.paymentHour
            expertCardRatingText.text = model.rating
            expertCardReviewsText.text = model.reviews
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopExpertsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.expert_card, parent, false)
        return TopExpertsHolder(view)
    }

    override fun getItemCount(): Int = topExperts.size

    override fun onBindViewHolder(holder: TopExpertsHolder, position: Int) {
        holder.bind(topExperts[position])
    }

}
