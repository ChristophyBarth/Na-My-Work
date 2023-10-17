package kodecamp.android.na_my_work.ui.ui.profile

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentUserProfileBinding
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.utils.GsonUtil
import kodecamp.android.na_my_work.ui.utils.Object
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var user: UserEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAsString = arguments?.getString("user")
        if (userAsString != null) {
            user = GsonUtil().getGsonParser().fromJson(userAsString, UserEntity::class.java)
            userInit(user)
        } else {
            user = Object.user!!
            userInit(Object.user!!)
            binding.sendAMessage.text = "Edit Profile"
            binding.sendAMessage.setOnClickListener {
                findNavController().navigate(R.id.action_userProfileFragment_to_profileSetupFragment2)
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun userInit(user: UserEntity) {
        binding.apply {

            if (user.profilePicture != null){
                profileImage.setImageURI(user.profilePicture)
            }

            name.text = user.fullName

            job.apply {
                text = user.title.ifBlank {
                    if (myProfile()) {
                        setTypeface(null, Typeface.ITALIC)
                        "not set yet"
                    } else {
                        visibility = View.GONE
                        ""
                    }
                }
            }

            address.apply {
                if (user.cityOfResidence.isBlank() && user.countryOfResidence.isBlank()){
                    if (myProfile()) {
                        setTypeface(null, Typeface.ITALIC)
                        text = "address not set yet"
                    }else{
                        visibility = View.GONE
                    }
                } else{
                    text = requireContext().getString(
                        R.string.user_adapter_address, user.cityOfResidence, user.countryOfResidence
                    )
                }
            }

            rating.text = requireContext().getString(
                R.string.user_adapter_rating, user.rating.toString(), user.ratingCount.toString()
            )


            description.text = user.bio
            description.apply {
                text = user.bio.ifBlank {
                    if (myProfile()) {
                        setTypeface(null, Typeface.ITALIC)
                        "Write a bio to get more profile visits and increase your chances of getting hired."
                    } else {
                        visibility = View.GONE
                        null
                    }
                }
            }

            fullName.text = user.fullName
            emailAddress.text = user.email

            dateOfBirth.apply {
                text = user.dateOfBirth.ifBlank {
                    if (myProfile()) {
                        setTypeface(null, Typeface.ITALIC)
                        "not set yet"
                    } else {
                        visibility = View.GONE
                        null
                    }
                }
            }

            countryOfResidence.apply {
               text = user.countryOfResidence.ifBlank {
                    if (myProfile()) {
                        setTypeface(null, Typeface.ITALIC)
                        "not set yet"
                    } else {
                        visibility = View.GONE
                        null
                    }
                }
            }

            stateOfResidence.apply {
                if (user.cityOfResidence.isBlank() && user.state.isBlank()){
                    if (myProfile()) {
                        setTypeface(null, Typeface.ITALIC)
                        text = "not set yet"
                    }else{
                        visibility = View.GONE
                    }
                } else{
                    text = requireContext().getString(
                        R.string.user_adapter_address, user.cityOfResidence, user.state
                    )
                }
            }

            phoneNumber.apply {
                text = user.phoneNumber.ifBlank {
                    if (myProfile()) {
                        setTypeface(null, Typeface.ITALIC)
                        "not set yet"
                    } else {
                        visibility = View.GONE
                        null
                    }
                }
            }

            if (user.experience.isNotEmpty()) {
                val firstExperience = user.experience[0]
                workExperienceTitle.text = getString(
                    R.string.experience_work_title_and_company_name,
                    firstExperience.position,
                    firstExperience.companyName
                )
                workExperienceDuration.text = if (firstExperience.tillDate) {
                    getString(R.string.profile_till_present, formatTime(firstExperience.startDate))
                } else {
                    getString(
                        R.string.experience_duration,
                        formatTime(firstExperience.startDate),
                        formatTime(firstExperience.endDate)
                    )
                }
                workExperienceDescription.text = firstExperience.workDescription
            } else {
                experienceTitle.visibility = View.GONE
                workExperienceTitle.visibility = View.GONE
                workExperienceDuration.visibility = View.GONE
                workExperienceDescription.visibility = View.GONE
            }

            if (user.link.isNotBlank()) {
                link.text = user.link
                link.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.link))
                    startActivity(intent)
                }
            } else {
                if (user.userId != Object.user!!.userId) {
                    linkTitle.visibility = View.GONE
                    link.visibility = View.GONE
                } else {
                    link.text = "You have not added a link to yet"
                    link.setTypeface(null, Typeface.ITALIC)
                }
            }

            pdfText.text =
                getString(R.string.username_resume_pdf, user.fullName.substringBefore(" "))
        }
    }

    private fun formatTime(millis: Long): String {
        val sdf = SimpleDateFormat(getString(R.string.mmmm_yyyy), Locale.getDefault())
        val date = Date(millis)
        return sdf.format(date)
    }

    private fun myProfile(): Boolean {
        return user.userId == Object.user!!.userId
    }

    private fun setTextOrHide(textView: TextView, stringData: String){
        textView.apply {
            stringData.ifBlank {
                if (myProfile()) {
                    setTypeface(null, Typeface.ITALIC)
                    "not set yet"
                } else {
                    visibility = View.GONE
                    ""
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}