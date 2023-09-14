package me.brisson.algorithm_visualizer.algorithms.sort.utils

import android.util.Log
import me.brisson.algorithm_visualizer.algorithms.utils.ChartTracer

abstract class Sort {
    abstract val algorithmName: String
    abstract val chartTracer: ChartTracer

    abstract suspend fun sort(
        arr: IntArray,
        onFinish: () -> Unit
    )

    companion object {
        private val TAG = Sort::class.java.simpleName

        /**
         * Instantiate an implementation of this abstract class
         *
         * @param className eg. algorithmClass::class.java.name
         */
        fun instantiateClass(className: String): Sort? {
            return try {
                val clazz = Class.forName(className)
                val instance = clazz.getDeclaredConstructor().newInstance()
                Log.d(TAG, "instantiateClass: ${instance::class.java.simpleName}")

                if (instance is Sort) {
                    instance
                } else null
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                null
            } catch (e: InstantiationException) {
                e.printStackTrace()
                null
            }
        }
    }

}
