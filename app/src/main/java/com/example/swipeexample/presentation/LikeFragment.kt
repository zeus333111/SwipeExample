package com.example.swipeexample.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.swipeexample.R
import com.example.swipeexample.databinding.LikeFragmentBinding
import com.example.swipeexample.presentation.viewmodel.LikeViewModel

class LikeFragment: Fragment() {

    private lateinit var binding: LikeFragmentBinding
    private val viewModel: LikeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LikeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
    }

    private fun setupObservers() {
        viewModel.firstCard.observe(viewLifecycleOwner) {
            binding.tvCardOne.text = it
        }
        viewModel.secondCard.observe(viewLifecycleOwner) {
            binding.tvCardTwo.text = it
        }

        viewModel.getCards()
    }

    private fun setupViews() {
        binding.motionLayout.setTransitionListener(object: TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {}

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {}

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when(currentId) {
                    R.id.offScreenLike -> {
                        viewModel.nextCard()
                        Toast.makeText(requireContext(), "Like", Toast.LENGTH_SHORT).show()
                    }
                    R.id.offScreenUnlike-> {
                        viewModel. nextCard()
                        Toast.makeText(requireContext(), "UnLike", Toast.LENGTH_SHORT).show()
                    }
                }
                binding.motionLayout.jumpToState(R.id.start_scene)
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {}
        })
    }
}