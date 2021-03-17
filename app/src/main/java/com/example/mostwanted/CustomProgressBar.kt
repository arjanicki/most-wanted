package com.example.mostwanted

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat


class CustomProgressBar(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var arcColor: Int
    private var duration: Long
    private var arcThickness: Float
    private var mSize = 0

    private val rimPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.BUTT
    }
    private val arcRect = RectF()

    private var sweepOffset = 15F
    private var angleStart = 0F

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomProgressBar,
            0, 0
        ).apply {

            try {
                arcColor = getColor(
                    R.styleable.CustomProgressBar_arc_color,
                    ContextCompat.getColor(context, R.color.custom_progressbar_default)
                )
                arcThickness = getInteger(
                    R.styleable.CustomProgressBar_arc_thickness,
                    resources.getInteger(R.integer.default_thickness)
                ).toFloat()
                val durTemp = getInteger(
                    R.styleable.CustomProgressBar_duration,
                    resources.getInteger(R.integer.default_duration)
                )
                duration =
                    if (durTemp < 500) R.integer.default_duration.toLong() else durTemp.toLong()
            } finally {
                recycle()
            }
        }

        rimPaint.apply {
            color = arcColor
            strokeWidth = arcThickness
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(arcRect, angleStart + sweepOffset, sweepOffset, false, rimPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val xPad = paddingLeft + paddingRight
        val yPad = paddingTop + paddingBottom
        val width = measuredWidth - xPad
        val height = measuredHeight - yPad
        mSize = if (width < height) width else height

        arcRect.set(
            paddingLeft + arcThickness, paddingTop + arcThickness,
            mSize - paddingLeft - arcThickness, mSize - paddingTop - arcThickness
        )

        setMeasuredDimension(mSize + xPad, mSize + yPad)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        arcAnimation()
    }

    private fun arcAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.duration = duration
        animator.interpolator = DecelerateInterpolator(1F)
        animator.addUpdateListener { animation ->
            angleStart = animation.animatedValue as Float
            sweepOffset += 2f
            if (sweepOffset > 360) sweepOffset = 10f
            invalidate()
        }
        animator.start()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                arcAnimation()
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}

        })
    }
}