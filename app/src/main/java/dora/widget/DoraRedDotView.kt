package dora.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup

class DoraRedDotView @JvmOverloads constructor(context: Context, attrs: AttributeSet?,
                                               defStyleAttr: Int = 0) :
    ViewGroup(context, attrs, defStyleAttr) {

    var redDotColor: Int = Color.RED
    var redDotRadius: Int = 10
    var redDotGravity: Int = 0
    var redDotVerticesOffset: Int = 0
    var showRedDot: Boolean = false
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.layout(l, t, r, b)
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        var cx = 0f
        var cy = 0f
        when (redDotGravity) {
            0 -> {
                cx = redDotRadius.toFloat() + redDotVerticesOffset
                cy = redDotRadius.toFloat() + redDotVerticesOffset
            }
            1 -> {
                cx = measuredWidth - redDotRadius.toFloat() - redDotVerticesOffset
                cy = redDotRadius.toFloat() + redDotVerticesOffset
            }
            2 -> {
                cx = measuredWidth - redDotRadius.toFloat() - redDotVerticesOffset
                cy = measuredHeight - redDotRadius.toFloat() - redDotVerticesOffset
            }
            3 -> {
                cx = redDotRadius.toFloat() + redDotVerticesOffset
                cy = measuredHeight - redDotRadius.toFloat() - redDotVerticesOffset
            }
        }
        if (showRedDot) {
            canvas.drawCircle(cx, cy, redDotRadius.toFloat(), paint)
        }
    }

    init {
        setWillNotDraw(false)
        val a = context.obtainStyledAttributes(attrs, R.styleable.DoraRedDotView)
        redDotColor = a.getColor(R.styleable.DoraRedDotView_dora_redDotColor, redDotColor)
        redDotRadius = a.getDimensionPixelSize(R.styleable.DoraRedDotView_dora_redDotRadius, redDotRadius)
        redDotGravity = a.getInt(R.styleable.DoraRedDotView_dora_redDotGravity, 0)
        redDotVerticesOffset = a.getDimensionPixelSize(R.styleable.DoraRedDotView_dora_redDotVerticesOffset, redDotVerticesOffset)
        showRedDot = a.getBoolean(R.styleable.DoraRedDotView_dora_showRedDot, showRedDot)
        a.recycle()
        paint.color = redDotColor
    }
}