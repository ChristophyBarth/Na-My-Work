package kodecamp.android.na_my_work.ui.ui.profile

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentDeleteBinding
import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.ui.home.HomeViewModel
import kodecamp.android.na_my_work.ui.utils.Object
import kodecamp.android.na_my_work.ui.utils.Resource

class DeleteFragment : Fragment() {
    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textFields = listOf(
            binding.etEmail, binding.etPassword
        )

        textFields.forEach { textField ->
            textField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val email = binding.etEmail.text.toString()
                    val password = binding.etPassword.text.toString()

                    binding.btnDeleteAcct.isEnabled =
                        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }

        binding.btnDeleteAcct.setOnClickListener {
            showDialog()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.deleteState.observe(viewLifecycleOwner) { content ->
            content.getContentIfNotHandled().let { response ->
                when (response) {
                    is Resource.Success -> {
                        requireContext().getSharedPreferences(Object.USER_PREF_NAME, Context.MODE_PRIVATE)
                            .edit().run {
                                remove(Object.USER_PRIMARY_KEY)
                                apply()

                                Object.primaryKey = null
                                Object.user = null
                                findNavController().navigate(R.id.action_deleteFragment_to_loginActivity)
                                activity?.finish()
                            }
                    }

                    is Resource.Error -> {
                        Snackbar.make(binding.root, response.message.toString(), Snackbar.LENGTH_SHORT)
                            .show()
                    }

                    is Resource.Loading -> {}
                    else -> {}
                }
            }

        }
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(getString(R.string.delete_account))
        dialog.setMessage(getString(R.string.are_you_sure_you_want_to_delete_your_account_from_na_my_work))

        val alertDialog = dialog.create()
        alertDialog.setButton(
            DialogInterface.BUTTON_POSITIVE, getString(R.string.no)
        ) { _, _ -> }
        alertDialog.setButton(
            DialogInterface.BUTTON_NEGATIVE, getString(R.string.yes)
        ) { _, _ ->
            val deletingUser = UserEntity(email = binding.etEmail.text.toString(), password = binding.etPassword.text.toString())
            viewModel.deleteUser(deletingUser)
        }

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        val radius = resources.getDimension(R.dimen.dialog_corner_radius)
        val shapeDrawable = ShapeDrawable(
            RoundRectShape(
                floatArrayOf(
                    radius, radius, radius, radius, radius, radius, radius, radius
                ), null, null
            )
        )
        shapeDrawable.paint.color = Color.WHITE
        alertDialog.window?.setBackgroundDrawable(shapeDrawable)

        alertDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}