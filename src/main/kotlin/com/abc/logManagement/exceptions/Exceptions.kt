package com.abc.logManagement.exceptions

class MicroServiceNameIsBlankOrNull(override val message: String?):Exception()


class MicroServiceAlreadyExists(override val message: String?):Exception()

class MicroServiceDoesNotExist(override val message: String?):Exception()

class SupportEngineerRequiredField(override val message: String?) : Exception()

class InvalidEmailAddress(override val message: String?):Exception()

class SupportEngineerAlreadyExists(override val message: String?):Exception()

class SupportEngineerDoesNotExist(override val message: String?):Exception()



