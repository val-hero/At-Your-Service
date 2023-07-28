package com.atyourservice.utils

import com.atyourservice.search.domain.Category
import com.atyourservice.search.domain.TopExpert
import com.example.atyourservice.R

class GetMock {
    companion object {
        fun mockItemCategory(): ArrayList<Category> {
            return arrayListOf(
                Category(R.drawable.category_all, "All"),
                Category(R.drawable.category_beauty, "Beauty"),
                Category(R.drawable.category_cleaning, "Cleaning"),
                Category(R.drawable.category_it, "IT"),
                Category(R.drawable.category_security, "Security")
            )
        }

        fun mockItemExperts(): ArrayList<TopExpert> {
            val experts = ArrayList<TopExpert>()
            for (i in 1..15) {
                experts.add(
                    TopExpert(
                        R.drawable.mock_profile,
                        "Jack Daniels",
                        "Android Developer",
                        "\$ 50 / hour",
                        "4.9",
                        "(111)"
                    )
                )
            }
            return experts
        }
    }
}