package tech.libeufin.nexus

import org.apache.commons.compress.utils.IOUtils
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import tech.libeufin.util.CryptoUtil
import javax.sql.rowset.serial.SerialBlob

class AuthenticationTest {
    @Test
    fun dbInvolvingTest() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
        transaction {
            SchemaUtils.create(NexusUsersTable)
            NexusUserEntity.new(id = "username") {
                password = SerialBlob(CryptoUtil.hashStringSHA256("password"))
            }
            // base64 of "username:password" == "dXNlcm5hbWU6cGFzc3dvcmQ="
            val hashedPass= extractUserAndHashedPassword(
                "Basic dXNlcm5hbWU6cGFzc3dvcmQ="
            ).second
            val row = NexusUserEntity.findById("username")
            assert(row?.password == SerialBlob(hashedPass))
        }
    }

    @Test
    fun basicAuthHeaderTest() {
        val hashedPass = extractUserAndHashedPassword(
            "Basic dXNlcm5hbWU6cGFzc3dvcmQ="
        ).second
        assert(CryptoUtil.hashStringSHA256("password").contentEquals(hashedPass))
    }
}