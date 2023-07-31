package com.atyourservice.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atyourservice.search.domain.model.TopExpert
import com.example.atyourservice.R
import com.example.atyourservice.databinding.SpecialistCardBinding


class TopExpertsAdapter : RecyclerView.Adapter<TopExpertsAdapter.TopExpertsHolder>() {
    private val topExperts = ArrayList<TopExpert>()

    fun setExperts(allExperts: List<TopExpert>) {
        topExperts.addAll(allExperts)
    }

    class TopExpertsHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = SpecialistCardBinding.bind(item)
        fun bind(model: TopExpert) = with(binding) {
            imUserPhoto.setImageResource(model.idPhoto)
            textUserName.text = model.userName
            tvJob.text = model.userJob
            tvCharge.text = model.paymentHour
            tvRating.text = model.rating
            tvReviews.text = model.reviews
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopExpertsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.specialist_card, parent, false)
        return TopExpertsHolder(view)
    }

    override fun getItemCount(): Int = topExperts.size

    override fun onBindViewHolder(holder: TopExpertsHolder, position: Int) {
        holder.bind(topExperts[position])
    }

}
