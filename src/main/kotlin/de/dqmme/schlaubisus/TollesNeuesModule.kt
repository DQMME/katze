package de.dqmme.schlaubisus

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.converters.impl.int
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess

class TollesNeuesModule: Extension() {
    override val name: String = "katze"

    private val httpClient = HttpClient()

    override suspend fun setup() {
        publicSlashCommand(::ArgumenteArgumentieren) {
            name = "katze"
            description = "HTTP KATZE jajaja süß"

            action {
                val code = arguments.code
                val response = httpClient.get("https://http.cat/$code")

                if(response.status.isSuccess()) {
                    val byteArray = response.body<ByteArray>()

                    respond {
                        addFile("KATZE.jpg", byteArray.inputStream())
                    }
                } else {
                    respond {
                        content = "Mach richtigen code du versager"
                    }
                }
            }
        }
    }

    override suspend fun unload() {
        httpClient.close()
    }
}

class ArgumenteArgumentieren: Arguments() {
    val code by int {
        name = "code"
        description = "Der http code für die süßen"
    }
}