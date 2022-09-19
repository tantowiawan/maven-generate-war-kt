package com.test.mvn.config

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.core.io.ResourceLoader
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Paths
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class WebFilter(val resourceLoader: ResourceLoader) : Filter {
    val APP_NAME = "maven-generate-war-kt/"

    private fun webAssetRedirect(req: HttpServletRequest, res: HttpServletResponse) {
        val fileName = reqPathNoBaseContext(req).replace("$APP_NAME","")
        val resource = resourceLoader.getResource("classpath:web/$fileName")

        if (fileName.isNotEmpty() && resource.exists() && resource.isFile) {
            res.sendRedirect("web/$fileName")
        } else {
            res.contentType = MediaType.TEXT_HTML_VALUE
            val ist = resourceLoader.getResource("classpath:web/index.html").inputStream
            val indexHtml = ist.readBytes()
            res.outputStream.write(indexHtml)
            res.outputStream.flush()
            res.outputStream.close()
            ist.close()
        }
    }

    override fun doFilter(sreq: ServletRequest, sres: ServletResponse, fc: FilterChain) {
        val request = sreq as HttpServletRequest
        val reservedPath = arrayOf(
            "/api",
            "/assets",
            "/web"
        )

        if (reservedPath.any { reqStartWith(it, request) }) {
            fc.doFilter(sreq, sres)
        } else {
            webAssetRedirect(sreq, sres as HttpServletResponse)
        }
    }

    fun reqStartWith(path: String, req: HttpServletRequest, ignoreCase: Boolean = true): Boolean {
        val currentPath = "/${reqPathNoBaseContext(req)}"
        return currentPath.startsWith(path, ignoreCase)
    }

    fun reqPathNoBaseContext(req: HttpServletRequest) =
        req.requestURL.toString().replace("$APP_NAME","").split("/").filterIndexed { i, _ -> i > 2 }.joinToString("/")
}