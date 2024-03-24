package com.route.todolist.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.os.LocaleList
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppLocalesMetadataHolderService
import com.route.todolist.Constant.Constants
import com.route.todolist.R
import com.route.todolist.databinding.FragmentSettingBinding
import java.util.Locale

class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectMode()
        selectLanguage()

    }

    override fun onResume() {
        super.onResume()
        selectMode()
    }

    private fun selectMode() {
        val modes = resources.getStringArray(R.array.modes)
        val adapter = ArrayAdapter(requireActivity(), R.layout.drop_down_item, modes)
        binding.selectMode.setAdapter(adapter)
        binding.selectMode.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    toggleThemeForLight(this)
                }

                1 -> {
                    toggleThemeForNight(this)
                }
            }
        }
    }

    private fun selectLanguage() {
        val language = resources.getStringArray(R.array.languages)
        val adapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, language)
        binding.selectLanguage.setAdapter(adapter)
        binding.selectLanguage.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 ->
                    togglelanguageToEnglish(this)

                1 ->
                    togglelanguageToArabic(this)
            }
        }
    }

    private fun togglelanguageToEnglish(fragment: Fragment) {
        val currentlanguage = setLocal(Constants.ENGLISH)
        val configuration = fragment.requireActivity().resources.configuration
        configuration.setLocale(currentlanguage)
        fragment.requireActivity().resources.updateConfiguration(
            configuration,
            resources.displayMetrics
        )
    }

    private fun setLocal(language: String): Locale {
        val local = Locale(language)
        Locale.setDefault(local)
        requireActivity().recreate()
        return local
    }

    private fun togglelanguageToArabic(fragment: Fragment) {
        val currentlanguage = setLocal(Constants.ARABIC)
        val configuration = fragment.requireActivity().resources.configuration
        configuration.setLocale(currentlanguage)
        fragment.requireActivity().resources.updateConfiguration(
            configuration,
            resources.displayMetrics
        )
    }

    private fun toggleThemeForLight(fragment: Fragment) {
        val currentLightMode = fragment.requireActivity().resources.configuration.uiMode
        val newLightMode = if (currentLightMode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(newLightMode)
    }

    private fun toggleThemeForNight(fragment: Fragment) {
        val currentLightMode = fragment.requireActivity().resources.configuration.uiMode
        val newLightMode = if (currentLightMode == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(newLightMode)
    }


}