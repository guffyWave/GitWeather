package com.thepeoplemesh.gitissues.common.communication

import java.io.IOException

class NoConnectivityException(message: String?) : IOException(message) {
}