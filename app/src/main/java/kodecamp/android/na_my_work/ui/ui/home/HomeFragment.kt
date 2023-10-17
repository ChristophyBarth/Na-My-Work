package kodecamp.android.na_my_work.ui.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentHomeBinding
import kodecamp.android.na_my_work.ui.adapter.CategoriesAdapter
import kodecamp.android.na_my_work.ui.adapter.UserAdapter
import kodecamp.android.na_my_work.ui.data.DummyData
import kodecamp.android.na_my_work.ui.model.Category
import kodecamp.android.na_my_work.ui.utils.Object

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var userAdapter: UserAdapter
    private lateinit var categoryAdapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileName.text =
            getString(R.string.hi_username, Object.user?.fullName!!.substringBefore(" "))
        binding.profileImage.setImageURI(Object.user?.profilePicture)
        binding.seeAllText2.setOnClickListener {
            findNavController().navigate(R.id.categoriesFragment2)
        }

        setUpProfileRecyclerView()
        setUpCategoryRecyclerView()
    }


    private fun setUpProfileRecyclerView() {

        val data = DummyData().fakeProfiles().shuffled().take(2)

        val recyclerView = binding.profileRecyclerView
        userAdapter = UserAdapter(
            requireContext(),
            data,
            UserAdapter.VIEW_TYPE_TYPE1,
            R.id.action_homeFragment2_to_userProfileFragment
        )

        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun setUpCategoryRecyclerView() {
        val data = listOf(
            Category(R.drawable.arts_and_crafts, "Arts & Crafts"),
            Category(R.drawable.technology, "Technology"),
            Category(R.drawable.engineering_and_construction, "Engineering & Construction"),
            Category(R.drawable.healthcare_services, "Healthcare Services")
        )
        val recyclerView = binding.profileRecyclerView2
        categoryAdapter = CategoriesAdapter(
            data,
            CategoriesAdapter.VIEW_TYPE_TYPE1,
            R.id.action_homeFragment2_to_categoryInfoFragment
        )

        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}