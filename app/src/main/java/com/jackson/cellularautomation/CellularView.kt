package com.jackson.cellularautomation

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.jackson.celluar_generator.CellularGenerator

class CellularView : View {

    var viewWidth: Int = 0
    var viewHeight: Int = 0
    var paint: Paint? = null
    var firstDraw = true
    var lastDrawMs: Long = -1
    var intervalMs: Long = -1
    var pixelSize: Int = 1
    var generator: CellularGenerator? = null
    var bitmap: Bitmap? = null
    var canvas: Canvas? = null


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        paint = Paint()
        paint?.color = Color.BLACK
    }

    fun start(ruleset: Int, intervalMs: Long, pixelSize: Int) {
        this.intervalMs = intervalMs
        this.pixelSize = pixelSize
        generator = CellularGenerator(ruleset, (right - left) / pixelSize)
        generations.clear()
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (changed) {
            generator = CellularGenerator(30, (right - left) / pixelSize)
            lastDrawMs = System.currentTimeMillis()
            bitmap = Bitmap.createBitmap(viewWidth!!.toInt(), viewHeight!!.toInt(), Bitmap.Config.ARGB_8888)
            canvas = Canvas(bitmap)

        }
    }


    private val generations = mutableListOf(listOf<Boolean>())

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
//            addGeneration(nextGen)
            drawGenerations(canvas)
//            generations.add(nextGen)
//            canvas.drawBitmap(bitmap, 0f, 0f, paint)
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
//                canvas.drawRect(it.toFloat() * pixelSize, index.toFloat() * pixelSize, it.toFloat() * pixelSize + pixelSize, index.toFloat() * pixelSize + pixelSize, paint)
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


    private fun addGeneration(nextGen: List<Boolean>) {
        canvas?.translate(0f, -1f)
        (0 until nextGen.size).forEach {
            if (nextGen[it]) {
                canvas?.drawPoint(it.toFloat(), viewHeight - 100f, paint)
            }
            lastDrawMs = System.currentTimeMillis()
        }
    }

    private fun readyToDrawNextGeneration() = System.currentTimeMillis() > (lastDrawMs + intervalMs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = measuredWidth
        viewHeight = measuredHeight
    }
}