package kodecamp.android.na_my_work.ui.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kodecamp.android.na_my_work.R
import kodecamp.android.na_my_work.databinding.FragmentProfileSetupBinding

class ProfileSetupFragment : Fragment() {
    private var _binding: FragmentProfileSetupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTabLayout()
        setUpViewPager()
    }

    private fun setUpTabLayout() {
        val ashTintDrawable = ContextCompat.getDrawable(
            requireContext(), R.drawable.tab_item_position_background
        )!!.mutate()
        DrawableCompat.setTint(
            ashTintDrawable, ContextCompat.getColor(requireContext(), R.color.light_ash)
        )

        val greenTintDrawable = ContextCompat.getDrawable(
            requireContext(), R.drawable.tab_item_position_background
        )!!.mutate()
        DrawableCompat.setTint(
            greenTintDrawable, ContextCompat.getColor(requireContext(), R.color.green)
        )

        binding.tabLayout.apply {
            /*for (view in this.touchables) {
                view.isEnabled = false
            }*/

            getTabAt(0)?.customView.apply {
                this?.findViewById<TextView>(R.id.form_indicator_text)?.apply {
                    text = getString(R.string.bio_data)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }
                this?.findViewById<TextView>(R.id.form_indicator)?.apply {
                    text = getString(R.string._1)
                    background = greenTintDrawable
                }
            }

            getTabAt(1)?.customView.apply {
                this?.findViewById<TextView>(R.id.form_indicator_text)?.text =
                    getString(R.string.work_experience)
                this?.findViewById<TextView>(R.id.form_indicator)?.text = getString(R.string._2)
            }

            getTabAt(2)?.customView.apply {
                this?.findViewById<TextView>(R.id.form_indicator_text)?.text =
                    getString(R.string.media_portfolio)
                this?.findViewById<TextView>(R.id.form_indicator)?.text = getString(R.string._3)
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.customView?.apply {
                        findViewById<TextView>(R.id.form_indicator)?.background = greenTintDrawable
                        findViewById<TextView>(R.id.form_indicator_text)?.setTextColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.black
                            )
                        )
                    }

                    if (tab != null) {
                        binding.viewpager.setCurrentItem(tab.position, true)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView?.apply {
                        findViewById<TextView>(R.id.form_indicator)?.background = ashTintDrawable
                        findViewById<TextView>(R.id.form_indicator_text)?.setTextColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.light_ash
                            )
                        )
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun setUpViewPager() {
        val myViewPagerAdapter = MyViewPagerAdapter(
            fragmentActivity = requireActivity()
        )
        myViewPagerAdapter.addFragment(ProfileBioDataFragment())
        myViewPagerAdapter.addFragment(WorkExperienceFragment())
        myViewPagerAdapter.addFragment(MediaFragment())
        binding.viewpager.adapter = myViewPagerAdapter

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }

    class MyViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val fragmentList = mutableListOf<Fragment>()
        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }

        override fun getItemCount(): Int = fragmentList.size
        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}