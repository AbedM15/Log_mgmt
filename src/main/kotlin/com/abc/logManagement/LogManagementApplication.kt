package com.abc.logManagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogManagementApplication

fun main(args: Array<String>) {
	runApplication<LogManagementApplication>(*args)
}
