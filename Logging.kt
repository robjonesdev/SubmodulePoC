package com.robjonesdev.logging

import android.util.Log

class Logging {

    enum class LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    private var isLoggingEnabled: Boolean = true
    private var logLevel: LogLevel = LogLevel.DEBUG

    /**
     * Enable or disable logging globally.
     */
    fun setLoggingEnabled(enabled: Boolean) {
        isLoggingEnabled = enabled
    }

    /**
     * Set the log level. Logs below this level will not be logged.
     */
    fun setLogLevel(level: LogLevel) {
        logLevel = level
    }

    /**
     * Log a message with a specific tag and level.
     */
    fun logMessage(tag: String, msg: String, level: LogLevel = LogLevel.DEBUG) {
        if (!isLoggingEnabled || !shouldLog(level)) return

        when (level) {
            LogLevel.DEBUG -> Log.d(tag, msg)
            LogLevel.INFO -> Log.i(tag, msg)
            LogLevel.WARN -> Log.w(tag, msg)
            LogLevel.ERROR -> Log.e(tag, msg)
        }
    }

    /**
     * Determine whether a message should be logged based on the current log level.
     */
    private fun shouldLog(level: LogLevel): Boolean {
        return level.ordinal >= logLevel.ordinal
    }

    /**
     * Log a message with a throwable (exception).
     */
    fun logMessage(tag: String, msg: String, throwable: Throwable, level: LogLevel = LogLevel.ERROR) {
        if (!isLoggingEnabled || !shouldLog(level)) return

        when (level) {
            LogLevel.DEBUG -> Log.d(tag, msg, throwable)
            LogLevel.INFO -> Log.i(tag, msg, throwable)
            LogLevel.WARN -> Log.w(tag, msg, throwable)
            LogLevel.ERROR -> Log.e(tag, msg, throwable)
        }
    }
}
