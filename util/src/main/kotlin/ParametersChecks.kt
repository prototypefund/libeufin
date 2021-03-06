package tech.libeufin.util

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode

fun expectInt(param: String): Int {
    return try {
        param.toInt()
    } catch (e: Exception) {
        throw UtilError(HttpStatusCode.BadRequest,"'$param' is not Int")
    }
}

fun expectLong(param: String): Long {
    return try {
        param.toLong()
    } catch (e: Exception) {
        throw UtilError(HttpStatusCode.BadRequest,"'$param' is not Long")
    }
}

fun expectLong(param: String?): Long? {
    if (param != null) {
        return expectLong(param)
    }
    return null
}


fun ApplicationCall.expectUrlParameter(name: String): String {
    return this.request.queryParameters[name]
        ?: throw UtilError(HttpStatusCode.BadRequest, "Parameter '$name' not provided in URI")
}