package kodecamp.android.na_my_work.ui.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentHomeBinding
import kodecamp.android.na_my_work.ui.adapter.CategoriesAdapter
import kodecamp.android.na_my_work.ui.adapter.UserAdapter
import kodecamp.android.na_my_work.ui.model.Category
import kodecamp.android.na_my_work.ui.model.User

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var userAdapter: UserAdapter
    private lateinit var categoryAdapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.seeAllText2.setOnClickListener {
            findNavController().navigate(R.id.categoriesFragment2)
        }

        setUpProfileRecyclerView()
        setUpCategoryRecyclerView()
    }


    private fun setUpProfileRecyclerView() {

        val data = listOf(
            User("Christophy", "Android Engineer", "Lagos, Nigeria", getString(R.string.description), R.drawable.profile_image ),
            User("Lara", "Android Engineer", "Lagos, Nigeria", getString(R.string.description), R.drawable.profile_image ),
            User("Edidem", "Android Engineer", "Lagos, Nigeria", getString(R.string.description), R.drawable.profile_image )

        )
        val recyclerView = binding.profileRecyclerView
        userAdapter = UserAdapter(data)

        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun setUpCategoryRecyclerView() {
        val data = listOf(
            Category(R.drawable.art_service, "Arts and Crafts"),
            Category(R.drawable.engineer_service, "Engineering and Construction"),
            Category(R.drawable.art_service, "Arts and Crafts"),
        )
        val recyclerView = binding.profileRecyclerView2
        categoryAdapter = CategoriesAdapter(data)

        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
    }

}