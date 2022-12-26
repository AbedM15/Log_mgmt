package com.abc.logManagement.exceptions

class MicroServiceNameIsBlankOrNull(override val message: String?):Exception()


class MicroServiceAlreadyExists(override val message: String?):Exception()

class MicroServiceDoesNotExist(override val message: String?):Exception()



