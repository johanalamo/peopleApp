package com.example.johan.person.layoutmanager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.support.v7.widget.LinearLayoutManager

open class AnimatedLinearLayoutManager : LinearLayoutManager {
	constructor(context: Context?) : super(context)
	constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

	override fun addView(child: View, index: Int) {
		super.addView(child, index)
        val h = 90f
        child.rotation = if (index == 0) -h else h
        child.alpha = 0.3f
        child.animate().rotation(0f).alpha(1f)
            .setInterpolator(AnticipateOvershootInterpolator(2f))
            .setDuration(800L)
	}
}
