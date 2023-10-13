package kodecamp.android.na_my_work.ui.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.CategoryItemBinding
import kodecamp.android.na_my_work.databinding.FragmentCategoriesBinding
import kodecamp.android.na_my_work.ui.adapter.CategoriesAdapter
import kodecamp.android.na_my_work.ui.model.Category

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding  get() = _binding!!
    private lateinit var categoryAdapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }


    private fun setUpRecyclerView() {
        val data = listOf(
            Category(R.drawable.arts, "Arts & Crafts"),
            Category(R.drawable.catering_services, "Catering Services"),
            Category(R.drawable.cleaning, "Cleaning"),
            Category(R.drawable.education, "Education"),
            Category(R.drawable.entertainment, "Entertainment"),
        )
        val recyclerView = binding.categoriesRecyclerview
        categoryAdapter = CategoriesAdapter(data)

        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}