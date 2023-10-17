package kodecamp.android.na_my_work.ui.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentCategoriesBinding
import kodecamp.android.na_my_work.ui.adapter.CategoriesAdapter
import kodecamp.android.na_my_work.ui.model.Category

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun setUpRecyclerView() {
        val data = listOf(
            Category(R.drawable.arts_and_crafts, "Arts & Crafts"),
            Category(R.drawable.technology, "Technology"),
            Category(R.drawable.engineering_and_construction, "Engineering & Construction"),
            Category(R.drawable.healthcare_services, "Healthcare Services")
        )
        val recyclerView = binding.categoriesRecyclerview
        categoryAdapter = CategoriesAdapter(
            data,
            CategoriesAdapter.VIEW_TYPE_TYPE2,
            R.id.action_categoriesFragment2_to_categoryInfoFragment
        )

        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}