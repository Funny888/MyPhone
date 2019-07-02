package com.example.myphone.models

import android.content.Context
import android.content.Intent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.example.myphone.R

class ResizeView(_context: Context?, _view: View) : View.OnTouchListener {
    var ctx: Context? = _context
    private var view: View = _view
    private var gesture: GestureDetector = GestureDetector(
        ctx,
        GestureListener(view)
    )

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        return gesture.onTouchEvent(event)
    }

    class GestureListener(_view: View) : GestureDetector.SimpleOnGestureListener() {
        var view = _view
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return false
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var height: Int = Math.abs(e1!!.y.toInt() - e2!!.y.toInt())
            view.context.sendBroadcast(Intent(view.context.getString(R.string.intent_filter)).putExtra("max", height))
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            view.context.sendBroadcast(Intent("com.example.myphone.interfaces").putExtra("max", 0))
            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onContextClick(e: MotionEvent?): Boolean {
            return super.onContextClick(e)
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return super.onSingleTapConfirmed(e)
        }

        override fun onShowPress(e: MotionEvent?) {
            super.onShowPress(e)
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return super.onDoubleTapEvent(e)
        }

        override fun onLongPress(e: MotionEvent?) {
            super.onLongPress(e)
        }
    }
}