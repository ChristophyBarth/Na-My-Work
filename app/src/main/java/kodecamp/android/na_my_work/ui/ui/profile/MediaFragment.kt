package kodecamp.android.na_my_work.ui.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentMediaBinding
import kodecamp.android.na_my_work.ui.adapter.SkillAdapter
import kodecamp.android.na_my_work.ui.data.DummyData
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.ui.home.HomeViewModel
import kodecamp.android.na_my_work.ui.utils.Resource

class MediaFragment : Fragment() {

    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<HomeViewModel>()

    private lateinit var chipGroup: ChipGroup
    private lateinit var skillAdapter: SkillAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateState.observe(viewLifecycleOwner) { content ->
            content.getContentIfNotHandled().let { response ->
                when (response) {
                    is Resource.Success -> {
                        Snackbar.make(binding.root, response.data!!, Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_profileSetupFragment2_to_userProfileFragment)
                    }

                    is Resource.Error -> {
                        Snackbar.make(binding.root, response.message!!, Snackbar.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {}

                    else -> {}
                }
            }
        }

        chipGroup = view.findViewById(R.id.chipGroup)
        skillAdapter = SkillAdapter(requireContext(), DummyData().skills())
        skillAdapter.setOnItemClickListener(object : SkillAdapter.OnItemClickListener {
            override fun onItemClick(skill: String) {
                addSkillToChipGroup(skill)
            }
        })

        val recyclerView = view.findViewById<RecyclerView>(R.id.chipRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = skillAdapter

        binding.saveButton.setOnClickListener {
            saveChanges()
        }
    }
    private fun saveChanges() {
        if (!areAllFieldsFilled()) {
            Snackbar.make(binding.root, "Please fill all fields before saving.", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (chipGroup.childCount == 0) {
            Snackbar.make(binding.root, "Please add at least one skill.", Snackbar.LENGTH_SHORT).show()
            return
        }

        val updatedUser = createUserEntityWithUpdatedSkills()
        viewModel.updateUserInformation(updatedUser)
    }

    private fun addSkillToChipGroup(skill: String) {
        if (isSkillNotAdded(skill)) {
            val chip = Chip(chipGroup.context)
            chip.text = skill
            chip.isCloseIconVisible = true
            chip.setOnCloseIconClickListener {
                chipGroup.removeView(chip)
            }
            chipGroup.addView(chip)
        }
    }
    private fun isSkillNotAdded(skill: String): Boolean {
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            if (chip.text.toString() == skill) {
                return false
            }
        }
        return true
    }

    private fun areAllFieldsFilled(): Boolean {
        val portfolioLink = binding.portfolioEditText.text.toString()
        return portfolioLink.isNotEmpty()
    }

    private fun createUserEntityWithUpdatedSkills(): UserEntity {
        val updatedUser = UserEntity()
        updatedUser.softSkills = getSkillsFromChipGroup()
        updatedUser.link = binding.portfolioEditText.text.toString()
        return updatedUser
    }

    private fun getSkillsFromChipGroup(): MutableList<String> {
        val skills = mutableListOf<String>()
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            skills.add(chip.text.toString())
        }
        return skills
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
