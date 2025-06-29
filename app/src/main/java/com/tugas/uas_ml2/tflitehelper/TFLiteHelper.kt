package com.tugas.uas_ml2.tflitehelper

import android.content.Context
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import org.tensorflow.lite.Interpreter

class TFLiteHelper(context: Context) {
    private var interpreter: Interpreter

    init {
        val model = loadModelFile(context, "weather_temperature_prediction_model.tflite")
        interpreter = Interpreter(model)
    }

    fun predictTemperature(input: FloatArray): Float {
        val inputBuffer = ByteBuffer.allocateDirect(input.size * 4).order(ByteOrder.nativeOrder())
        input.forEach { inputBuffer.putFloat(it) }
        inputBuffer.rewind()

        val outputBuffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder())
        interpreter.run(inputBuffer, outputBuffer)
        outputBuffer.rewind()
        return outputBuffer.float
    }

    fun normalize(input: FloatArray): FloatArray {
        val minVals = floatArrayOf(0f, 0f, 0f)
        val maxVals = floatArrayOf(100f, 40f, 30f)
        return input.mapIndexed { i, v -> (v - minVals[i]) / (maxVals[i] - minVals[i]) }
            .toFloatArray()
    }

    @Throws(IOException::class)
    private fun loadModelFile(context: Context, modelFilename: String): MappedByteBuffer {
        val afd = context.assets.openFd(modelFilename)
        val inputStream = FileInputStream(afd.fileDescriptor)
        val channel = inputStream.channel
        return channel.map(FileChannel.MapMode.READ_ONLY, afd.startOffset, afd.declaredLength)
    }
}
