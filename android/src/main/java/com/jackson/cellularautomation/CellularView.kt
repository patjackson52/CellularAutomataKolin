package com.jackson.cellularautomation

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jackson.celluar_generator.CellularGenerator

/**
 * Displays generations of Cellular Automata, starting at the bottom of the view,
 * and each generation pushing the others up.
 *
 * Usage:
 *  val cellularView = findViewById(R.id.cellular_view)
 *  cellularView.start(ruleSet = 30, intervalMs = 0, pixelSize = 10)
 */
class CellularView : View {

    private var viewWidth: Int = 0
    private var viewHeight: Int = 0
    private var paint: Paint? = null
    private var firstDraw = true
    private var lastDrawMs: Long = -1
    private var intervalMs: Long = -1
    private var pixelSize: Int = 1
    private var generator: CellularGenerator? = null
    private val generations = mutableListOf(booleanArrayOf())


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }


    /**
     * @param ruleset Wolfram ruleset classification (1 - 256)
     */
    fun start(ruleset: Int, intervalMs: Long, pixelSize: Int) {
        if (ruleset !in 1..256) {
            throw IllegalArgumentException("Ruleset must be in 1 - 256")
        }
        if (pixelSize < 1) {
            throw IllegalArgumentException("pixelSize must be > 0")
        }
        this.intervalMs = intervalMs
        this.pixelSize = pixelSize
        generator = CellularGenerator(ruleset, (right - left) / pixelSize)
        generations.clear()
    }

    private fun initialize() {
        paint = Paint()
        paint?.color = Color.BLACK
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (changed) {
            generator = CellularGenerator(30, (right - left) / pixelSize)
            lastDrawMs = System.currentTimeMillis()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (firstDraw) {
            val rect = Rect(0, 0, viewWidth, viewHeight)
            paint?.color = Color.WHITE
            canvas.drawRect(rect, paint)
            firstDraw = false
        }
        if (readyToDrawNextGeneration()) {
            val nextGen = generator!!.next()
            generations.add(nextGen)
            drawGenerations(canvas)
        }
        postDelayed({ invalidate() }, intervalMs)
    }


    private fun drawGenerations(canvas: Canvas) {
//        val startTime = System.currentTimeMillis()
        paint?.color = Color.BLACK
        (0 until viewHeight / pixelSize).forEach {
            val index = it
            if (index + generations.size <= viewHeight / pixelSize) {
                paint?.color = Color.WHITE
                //do nothing
            } else {
                (0 until viewWidth / pixelSize).forEach {
                    if (generations[(generations.size - (viewHeight / pixelSize - index))][it]) {
                        paint?.color = Color.BLACK
                    } else {
                        paint?.color = Color.WHITE
                    }
                    canvas.drawRect(it.toFloat() * pixelSize, index.toFloat() * pixelSize, it.toFloat() * pixelSize + pixelSize, index.toFloat() * pixelSize + pixelSize, paint)
                }
            }
        }
//        Log.d("drawGenerations", "Time: ${System.currentTimeMillis() - startTime}")
    }


    private fun readyToDrawNextGeneration() = System.currentTimeMillis() > (lastDrawMs + intervalMs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = measuredWidth
        viewHeight = measuredHeight
    }
}