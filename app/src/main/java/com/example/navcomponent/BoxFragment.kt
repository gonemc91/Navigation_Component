package com.example.navcomponent

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.example.navcomponent.databinding.FragmentBoxBinding
import kotlin.random.Random

/**
 * The second fragment launched from the [RootFragment]
 * Arguments: color (int) which should be specified by the arguments key [ARG_COLOR].
 *
 * May return results via Fragment Results API with request code = [REQUEST_CODE] and Bundle
 * contained random generated number in [EXTRA_RANDOM_NUMBER] (int)
 */


class BoxFragment : Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    private val args: BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

// arguments are located in requireArguments bundle as usual
        val color = args.color
        binding.root.setBackgroundColor(color)


        binding.goBackButton.setOnClickListener{
            // just go back to the previous screen, also navigateUp() can be used
            findNavController().popBackStack()

        }
        binding.openSecretButton.setOnClickListener{
            //launched next screen without any arguments
            findNavController().navigate(BoxFragmentDirections.actionBoxFragmentToSecretFragment())

        }
        binding.generateNumberButton.setOnClickListener{
            val number = Random.nextInt(100)
            // send the result
           findNavController().previousBackStackEntry?.savedStateHandle?.set(EXTRA_RANDOM_NUMBER, number)
            // go back to the previous screen, also navigateUp() can be used
            findNavController().popBackStack()
        }
    }




    companion object {
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"

    }
}